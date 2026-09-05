#include "save.h"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "../platform/fs.h"
#include "../platform/log.h"

#define SAVE_MAGIC   "BRSV"
/* v2 added the sound and music toggles. v1 files still load; they simply
 * predate the settings screen, so both default to on. */
#define SAVE_VERSION 2
#define HEADER_V1    28
#define HEADER_V2    36
#define RECORD_SIZE  8

static void save_path(char *out, unsigned size)
{
    snprintf(out, size, "%s/save.bin", br_asset_root());
}

int br_save_init(br_save *save, int world_count, int levels_per_world)
{
    memset(save, 0, sizeof(*save));
    save->world_count = world_count;
    save->levels_per_world = levels_per_world;
    save->sound_on = 1;
    save->music_on = 1;
    save->levels = calloc((size_t)(world_count * levels_per_world),
                          sizeof(br_level_progress));
    if (!save->levels) {
        LOGE("save: out of memory for %d entries", world_count * levels_per_world);
        return -1;
    }
    return 0;
}

void br_save_free(br_save *save)
{
    free(save->levels);
    memset(save, 0, sizeof(*save));
}

br_level_progress *br_save_level(br_save *save, int world, int level)
{
    if (world < 0 || world >= save->world_count ||
        level < 0 || level >= save->levels_per_world)
        return NULL;
    return &save->levels[world * save->levels_per_world + level];
}

void br_save_record(br_save *save, int world, int level, int stars, float time)
{
    br_level_progress *entry = br_save_level(save, world, level);

    if (!entry)
        return;

    if (stars > (int)entry->stars) {
        entry->stars = (unsigned char)stars;
        save->dirty = 1;
    }
    if (time > 0.0f && (entry->best_time <= 0.0f || time < entry->best_time)) {
        entry->best_time = time;
        save->dirty = 1;
    }
}

void br_save_remember_place(br_save *save, int world, int level)
{
    if (save->last_world != world || save->last_level != level) {
        save->last_world = world;
        save->last_level = level;
        save->dirty = 1;
    }
}

int br_save_world_stars(const br_save *save, int world)
{
    int total = 0, i;

    if (world < 0 || world >= save->world_count)
        return 0;
    for (i = 0; i < save->levels_per_world; i++)
        total += save->levels[world * save->levels_per_world + i].stars;
    return total;
}

int br_save_total_stars(const br_save *save)
{
    int total = 0, i;

    for (i = 0; i < save->world_count * save->levels_per_world; i++)
        total += save->levels[i].stars;
    return total;
}

static void put_u32(unsigned char *p, unsigned v)
{
    p[0] = (unsigned char)(v & 0xff);
    p[1] = (unsigned char)((v >> 8) & 0xff);
    p[2] = (unsigned char)((v >> 16) & 0xff);
    p[3] = (unsigned char)((v >> 24) & 0xff);
}

static unsigned get_u32(const unsigned char *p)
{
    return (unsigned)p[0] | ((unsigned)p[1] << 8) |
           ((unsigned)p[2] << 16) | ((unsigned)p[3] << 24);
}

static void put_f32(unsigned char *p, float v)
{
    union { float f; unsigned u; } c;
    c.f = v;
    put_u32(p, c.u);
}

static float get_f32(const unsigned char *p)
{
    union { float f; unsigned u; } c;
    c.u = get_u32(p);
    return c.f;
}

void br_save_load(br_save *save)
{
    char path[256];
    unsigned char header[HEADER_V2];
    FILE *f;
    unsigned version;
    int worlds, per_world, w, l;

    save_path(path, sizeof(path));
    f = fopen(path, "rb");
    if (!f) {
        LOGI("save: no file at %s yet -- starting fresh", path);
        return;
    }

    if (fread(header, 1, HEADER_V1, f) != HEADER_V1 ||
        memcmp(header, SAVE_MAGIC, 4) != 0) {
        LOGW("save: %s is not a save this build understands -- ignoring it", path);
        fclose(f);
        return;
    }

    version = get_u32(header + 4);
    if (version != 1 && version != SAVE_VERSION) {
        LOGW("save: %s is version %u -- ignoring it", path, version);
        fclose(f);
        return;
    }
    if (version >= 2) {
        if (fread(header + HEADER_V1, 1, HEADER_V2 - HEADER_V1, f) !=
            HEADER_V2 - HEADER_V1) {
            LOGW("save: %s header is short -- ignoring it", path);
            fclose(f);
            return;
        }
        save->sound_on = get_u32(header + 28) != 0;
        save->music_on = get_u32(header + 32) != 0;
    }

    worlds    = (int)get_u32(header + 8);
    per_world = (int)get_u32(header + 12);
    save->last_world = (int)get_u32(header + 16);
    save->last_level = (int)get_u32(header + 20);
    save->bike_type  = (int)get_u32(header + 24);

    /* Read what overlaps: a save from a build with fewer worlds still loads. */
    for (w = 0; w < worlds; w++) {
        for (l = 0; l < per_world; l++) {
            unsigned char record[RECORD_SIZE];
            br_level_progress *entry;

            if (fread(record, 1, RECORD_SIZE, f) != RECORD_SIZE) {
                LOGW("save: truncated at %d-%d", w + 1, l + 1);
                goto done;
            }
            entry = br_save_level(save, w, l);
            if (entry) {
                entry->stars = record[0] > 3 ? 3 : record[0];
                entry->best_time = get_f32(record + 4);
            }
        }
    }

done:
    fclose(f);
    if (save->last_world < 0 || save->last_world >= save->world_count)
        save->last_world = 0;
    if (save->last_level < 0 || save->last_level >= save->levels_per_world)
        save->last_level = 0;
    save->dirty = 0;
    LOGI("save: loaded %s (v%u) -- %d stars, resuming at %d-%d, sound %d music %d",
         path, version, br_save_total_stars(save),
         save->last_world + 1, save->last_level + 1, save->sound_on, save->music_on);
}

int br_save_flush(br_save *save)
{
    char path[256];
    unsigned char header[HEADER_V2];
    FILE *f;
    int w, l;

    if (!save->dirty)
        return 0;

    save_path(path, sizeof(path));
    f = fopen(path, "wb");
    if (!f) {
        LOGE("save: cannot write %s", path);
        return -1;
    }

    memcpy(header, SAVE_MAGIC, 4);
    put_u32(header + 4, SAVE_VERSION);
    put_u32(header + 8, (unsigned)save->world_count);
    put_u32(header + 12, (unsigned)save->levels_per_world);
    put_u32(header + 16, (unsigned)save->last_world);
    put_u32(header + 20, (unsigned)save->last_level);
    put_u32(header + 24, (unsigned)save->bike_type);
    put_u32(header + 28, (unsigned)(save->sound_on != 0));
    put_u32(header + 32, (unsigned)(save->music_on != 0));
    if (fwrite(header, 1, sizeof(header), f) != sizeof(header))
        goto fail;

    for (w = 0; w < save->world_count; w++) {
        for (l = 0; l < save->levels_per_world; l++) {
            const br_level_progress *entry = &save->levels[w * save->levels_per_world + l];
            unsigned char record[RECORD_SIZE];

            memset(record, 0, sizeof(record));
            record[0] = entry->stars;
            put_f32(record + 4, entry->best_time);
            if (fwrite(record, 1, RECORD_SIZE, f) != RECORD_SIZE)
                goto fail;
        }
    }

    fclose(f);
    save->dirty = 0;
    LOGI("save: wrote %s (%d stars)", path, br_save_total_stars(save));
    return 0;

fail:
    LOGE("save: short write to %s", path);
    fclose(f);
    return -1;
}
