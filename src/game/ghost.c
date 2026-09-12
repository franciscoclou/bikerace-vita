#include "ghost.h"

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

#include "../platform/fs.h"
#include "../platform/log.h"

#define GHOST_MAGIC   "BRGH"
/* v2 records the shape of the level pack the runs were recorded against.
 * v1 files still load: they predate the field, and the pack has not changed. */
#define GHOST_VERSION 2

struct br_ghost_store {
    br_ghost *ghosts;          /* world_count * levels_per_world, sparse */
    int       world_count, levels_per_world;
    int       dirty;
};

static void store_path(char *out, unsigned size)
{
    snprintf(out, size, "%s/ghosts.bin", br_asset_root());
}

/* Written whole here first, then swapped in -- see br_fs_replace(). Losing a
 * ghost matters less than losing progress, but the file is the larger of the
 * two and so the likelier one to be caught mid-write. */
static void store_tmp_path(char *out, unsigned size)
{
    snprintf(out, size, "%s/ghosts.tmp", br_asset_root());
}

static br_ghost *slot(const br_ghost_store *store, int world, int level)
{
    if (world < 0 || world >= store->world_count ||
        level < 0 || level >= store->levels_per_world)
        return NULL;
    return &store->ghosts[world * store->levels_per_world + level];
}

/* Path length up to each sample, so a wheel can be rolled the right amount
 * without the run having recorded one. */
static void sum_distances(br_ghost_sample *samples, int count)
{
    float total = 0.0f;
    int i;

    if (count <= 0)
        return;
    samples[0].distance = 0.0f;
    for (i = 1; i < count; i++) {
        float dx = samples[i].pos.x - samples[i - 1].pos.x;
        float dy = samples[i].pos.y - samples[i - 1].pos.y;

        total += sqrtf(dx * dx + dy * dy);
        samples[i].distance = total;
    }
}

/* Rolling contact: the wheel turns by the distance over its radius. Moving
 * forward spins it clockwise, which is negative with y up. */
static float wheel_degrees(float distance)
{
    return -(distance / (BR_BIKE_WHEEL_DIAMETER * 0.5f)) * (180.0f / 3.1415927f);
}

/* ------------------------------------------------------------- recording -- */

void br_ghost_record_begin(br_ghost_recorder *rec)
{
    rec->count = 0;
    rec->next_sample_at = 0.0f;
    rec->overflowed = 0;
}

void br_ghost_record_tick(br_ghost_recorder *rec, float elapsed,
                          const vec2 *head, float angle_deg)
{
    if (elapsed < rec->next_sample_at)
        return;
    if (rec->count >= BR_GHOST_MAX_SAMPLES) {
        rec->overflowed = 1;      /* a run this long simply gets no ghost */
        return;
    }

    rec->samples[rec->count].pos = *head;
    rec->samples[rec->count].angle_deg = angle_deg;
    if (rec->count == 0) {
        rec->samples[0].distance = 0.0f;
    } else {
        const br_ghost_sample *prev = &rec->samples[rec->count - 1];
        float dx = head->x - prev->pos.x;
        float dy = head->y - prev->pos.y;

        rec->samples[rec->count].distance =
            prev->distance + sqrtf(dx * dx + dy * dy);
    }
    rec->count++;
    rec->next_sample_at += 1.0f / (float)BR_GHOST_HZ;
}

int br_ghost_pose_at(const br_ghost *ghost, float time, vec2 *pos,
                     float *angle_deg, float *wheel_deg)
{
    float exact, blend;
    int index;

    if (!ghost || ghost->count <= 0)
        return 0;

    exact = time * (float)BR_GHOST_HZ;
    index = (int)exact;
    if (index >= ghost->count - 1) {
        /* Past the end: hold the finish pose rather than vanishing. */
        *pos = ghost->samples[ghost->count - 1].pos;
        *angle_deg = ghost->samples[ghost->count - 1].angle_deg;
        if (wheel_deg)
            *wheel_deg = wheel_degrees(ghost->samples[ghost->count - 1].distance);
        return 0;
    }
    if (index < 0)
        index = 0;
    blend = exact - (float)index;

    {
        const br_ghost_sample *a = &ghost->samples[index];
        const br_ghost_sample *b = &ghost->samples[index + 1];
        float delta = b->angle_deg - a->angle_deg;

        /* Angles are degrees wrapped to (-360, 360), so a lap of the frame can
         * read as a near-360 jump. Take the short way round. */
        while (delta >  180.0f) delta -= 360.0f;
        while (delta < -180.0f) delta += 360.0f;

        pos->x = a->pos.x + (b->pos.x - a->pos.x) * blend;
        pos->y = a->pos.y + (b->pos.y - a->pos.y) * blend;
        *angle_deg = a->angle_deg + delta * blend;
        if (wheel_deg)
            *wheel_deg = wheel_degrees(a->distance +
                                       (b->distance - a->distance) * blend);
    }
    return 1;
}

/* ----------------------------------------------------------------- store -- */

static unsigned get_u32(const unsigned char *p)
{
    return (unsigned)p[0] | ((unsigned)p[1] << 8) |
           ((unsigned)p[2] << 16) | ((unsigned)p[3] << 24);
}

static void put_u32(unsigned char *p, unsigned v)
{
    p[0] = (unsigned char)(v & 0xff);
    p[1] = (unsigned char)((v >> 8) & 0xff);
    p[2] = (unsigned char)((v >> 16) & 0xff);
    p[3] = (unsigned char)((v >> 24) & 0xff);
}

static float get_f32(const unsigned char *p)
{
    union { float f; unsigned u; } c;
    c.u = get_u32(p);
    return c.f;
}

static void put_f32(unsigned char *p, float v)
{
    union { float f; unsigned u; } c;
    c.f = v;
    put_u32(p, c.u);
}

static void load(br_ghost_store *store)
{
    char path[256], tmp[256];
    unsigned char header[16], record[16];
    FILE *f;
    unsigned records, version, i;

    store_path(path, sizeof(path));
    store_tmp_path(tmp, sizeof(tmp));
    f = br_fs_open_saved(path, tmp);
    if (!f) {
        LOGI("ghost: none recorded yet");
        return;
    }

    if (fread(header, 1, sizeof(header), f) != sizeof(header) ||
        memcmp(header, GHOST_MAGIC, 4) != 0) {
        LOGW("ghost: %s is not a ghost file -- ignoring it", path);
        fclose(f);
        return;
    }
    version = get_u32(header + 4);
    if (version < 1 || version > GHOST_VERSION) {
        LOGW("ghost: %s is version %u, which this build cannot read -- "
             "ignoring it", path, version);
        fclose(f);
        return;
    }
    records = get_u32(header + 8);

    /* A run is stored by slot index, so a pack of a different shape would put
     * every ghost on the wrong level. The file says what it was recorded
     * against; when that disagrees, drop the lot. Ghosts are re-earnable and
     * a ghost on the wrong track is worse than none. */
    if (version >= 2) {
        unsigned shape = get_u32(header + 12);

        if ((int)(shape & 0xffff) != store->levels_per_world ||
            (int)(shape >> 16) != store->world_count) {
            LOGW("ghost: %s holds runs for a %u x %u pack, not %d x %d -- "
                 "ignoring it", path, shape >> 16, shape & 0xffff,
                 store->world_count, store->levels_per_world);
            fclose(f);
            return;
        }
    }

    for (i = 0; i < records; i++) {
        br_ghost *g;
        unsigned world, level, count, bike, n;

        if (fread(record, 1, sizeof(record), f) != sizeof(record))
            break;
        world = get_u32(record + 0);
        level = get_u32(record + 4);
        count = get_u32(record + 8) & 0xffff;
        bike  = get_u32(record + 8) >> 16;

        g = slot(store, (int)world, (int)level);
        if (!g || count == 0 || count > BR_GHOST_MAX_SAMPLES) {
            fseek(f, (long)count * 12, SEEK_CUR);
            continue;
        }

        g->samples = malloc(sizeof(br_ghost_sample) * count);
        if (!g->samples)
            break;
        g->count = (int)count;
        g->bike = (br_bike_type)bike;
        g->time = get_f32(record + 12);

        for (n = 0; n < count; n++) {
            unsigned char s[12];
            if (fread(s, 1, sizeof(s), f) != sizeof(s)) {
                g->count = (int)n;
                break;
            }
            g->samples[n].pos.x = get_f32(s + 0);
            g->samples[n].pos.y = get_f32(s + 4);
            g->samples[n].angle_deg = get_f32(s + 8);
        }
        sum_distances(g->samples, g->count);
    }

    fclose(f);
    LOGI("ghost: loaded %s (%u runs)", path, records);
}

br_ghost_store *br_ghost_store_open(int world_count, int levels_per_world)
{
    br_ghost_store *store = calloc(1, sizeof(*store));

    if (!store)
        return NULL;
    store->world_count = world_count;
    store->levels_per_world = levels_per_world;
    store->ghosts = calloc((size_t)(world_count * levels_per_world),
                           sizeof(br_ghost));
    if (!store->ghosts) {
        free(store);
        return NULL;
    }
    load(store);
    return store;
}

void br_ghost_store_close(br_ghost_store *store)
{
    int i;

    if (!store)
        return;
    for (i = 0; i < store->world_count * store->levels_per_world; i++)
        free(store->ghosts[i].samples);
    free(store->ghosts);
    free(store);
}

void br_ghost_store_clear(br_ghost_store *store)
{
    int i;

    if (!store)
        return;
    for (i = 0; i < store->world_count * store->levels_per_world; i++) {
        free(store->ghosts[i].samples);
        memset(&store->ghosts[i], 0, sizeof(br_ghost));
    }
    /* Always dirty, so the wipe reaches the disk even when nothing was
     * recorded: a reset has to leave nothing behind to load next time. */
    store->dirty = 1;
    LOGI("ghost: every recorded run cleared");
}

const br_ghost *br_ghost_get(const br_ghost_store *store, int world, int level)
{
    const br_ghost *g = store ? slot(store, world, level) : NULL;

    return (g && g->count > 0) ? g : NULL;
}

void br_ghost_put(br_ghost_store *store, int world, int level, const br_ghost *run)
{
    br_ghost *g = store ? slot(store, world, level) : NULL;

    if (!g || !run || run->count <= 0)
        return;
    /* Only a better run replaces what is there. */
    if (g->count > 0 && g->time > 0.0f && run->time >= g->time)
        return;

    /* Empty the slot before reaching for memory. Clearing only the count left
     * a slot that reported "no ghost" but kept its old time, which then beat
     * every later run and so could never be replaced. */
    free(g->samples);
    memset(g, 0, sizeof(*g));

    g->samples = malloc(sizeof(br_ghost_sample) * (size_t)run->count);
    if (!g->samples) {
        LOGE("ghost: out of memory for a %d-sample run", run->count);
        return;
    }
    memcpy(g->samples, run->samples, sizeof(br_ghost_sample) * (size_t)run->count);
    g->count = run->count;
    sum_distances(g->samples, g->count);
    g->bike = run->bike;
    g->time = run->time;
    store->dirty = 1;
}

int br_ghost_store_flush(br_ghost_store *store)
{
    char path[256], tmp[256];
    unsigned char header[16], record[16];
    FILE *f;
    unsigned records = 0;
    int i, n;

    if (!store || !store->dirty)
        return 0;

    for (i = 0; i < store->world_count * store->levels_per_world; i++)
        if (store->ghosts[i].count > 0)
            records++;

    store_path(path, sizeof(path));
    store_tmp_path(tmp, sizeof(tmp));
    f = fopen(tmp, "wb");
    if (!f) {
        LOGE("ghost: cannot write %s", tmp);
        return -1;
    }

    memcpy(header, GHOST_MAGIC, 4);
    put_u32(header + 4, GHOST_VERSION);
    put_u32(header + 8, records);
    put_u32(header + 12, (unsigned)(store->levels_per_world & 0xffff) |
                         ((unsigned)store->world_count << 16));
    if (fwrite(header, 1, sizeof(header), f) != sizeof(header))
        goto fail;

    for (i = 0; i < store->world_count * store->levels_per_world; i++) {
        const br_ghost *g = &store->ghosts[i];

        if (g->count <= 0)
            continue;
        put_u32(record + 0, (unsigned)(i / store->levels_per_world));
        put_u32(record + 4, (unsigned)(i % store->levels_per_world));
        put_u32(record + 8, (unsigned)g->count | ((unsigned)g->bike << 16));
        put_f32(record + 12, g->time);
        if (fwrite(record, 1, sizeof(record), f) != sizeof(record))
            goto fail;

        for (n = 0; n < g->count; n++) {
            unsigned char s[12];
            put_f32(s + 0, g->samples[n].pos.x);
            put_f32(s + 4, g->samples[n].pos.y);
            put_f32(s + 8, g->samples[n].angle_deg);
            if (fwrite(s, 1, sizeof(s), f) != sizeof(s))
                goto fail;
        }
    }

    if (fclose(f) != 0) {
        LOGE("ghost: could not close %s", tmp);
        remove(tmp);
        return -1;
    }
    if (br_fs_replace(tmp, path) != 0)
        return -1;

    store->dirty = 0;
    LOGI("ghost: wrote %s (%u runs)", path, records);
    return 0;

fail:
    LOGE("ghost: short write to %s -- %s is left alone", tmp, path);
    fclose(f);
    remove(tmp);
    return -1;
}
