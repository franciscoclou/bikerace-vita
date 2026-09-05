#include "level.h"

#include <stdlib.h>
#include <string.h>

#include "../platform/log.h"

/* data/levels.bin is linked into the executable so a code-only patch never
 * needs an asset transfer. See src/game/levels_blob.S. */
#ifdef BR_HOST_TEST
extern const unsigned char *br_levels_blob_start;
extern const unsigned char *br_levels_blob_end;
#else
extern const unsigned char br_levels_blob_start[];
extern const unsigned char br_levels_blob_end[];
#endif

typedef struct {
    const unsigned char *p, *end;
    int ok;
} reader;

static unsigned read_u32(reader *r)
{
    unsigned v;
    if (r->p + 4 > r->end) { r->ok = 0; return 0; }
    v = (unsigned)r->p[0] | ((unsigned)r->p[1] << 8) |
        ((unsigned)r->p[2] << 16) | ((unsigned)r->p[3] << 24);
    r->p += 4;
    return v;
}

static float read_f32(reader *r)
{
    union { unsigned u; float f; } c;
    c.u = read_u32(r);
    return c.f;
}

static void read_vec(reader *r, vec2 *v)
{
    v->x = read_f32(r);
    v->y = read_f32(r);
}

int br_levels_load(br_level_pack *pack)
{
    reader r;
    unsigned version, w;

    memset(pack, 0, sizeof(*pack));
    r.p = br_levels_blob_start;
    r.end = br_levels_blob_end;
    r.ok = 1;

    if (r.end - r.p < 12 || memcmp(r.p, "BRLV", 4) != 0) {
        LOGE("levels: bad magic in embedded blob (%d bytes)", (int)(r.end - r.p));
        return -1;
    }
    r.p += 4;

    version = read_u32(&r);
    if (version != 1) {
        LOGE("levels: unsupported format version %u", version);
        return -1;
    }

    pack->world_count = (int)read_u32(&r);
    pack->worlds = calloc((size_t)pack->world_count, sizeof(br_world));
    if (!pack->worlds) {
        LOGE("levels: out of memory for %d worlds", pack->world_count);
        return -1;
    }

    for (w = 0; w < (unsigned)pack->world_count; w++) {
        br_world *world = &pack->worlds[w];
        int l;

        world->id          = (int)read_u32(&r);
        world->atlas       = (int)read_u32(&r);
        world->level_count = (int)read_u32(&r);
        world->levels = calloc((size_t)world->level_count, sizeof(br_level));
        if (!world->levels) {
            LOGE("levels: out of memory for world %d", world->id);
            return -1;
        }

        for (l = 0; l < world->level_count; l++) {
            br_level *level = &world->levels[l];
            int count, i, s;

            count = (int)read_u32(&r);
            for (s = 0; s < 3; s++)
                level->star_times[s] = read_f32(&r);
            read_vec(&r, &level->start);
            read_vec(&r, &level->finish);

            level->boards.count = count;
            level->boards.boards = malloc(sizeof(br_board) * (size_t)count);
            if (!level->boards.boards) {
                LOGE("levels: out of memory for %d-%d (%d boards)",
                     world->id, l + 1, count);
                return -1;
            }
            br_aabb_set(&level->boards.bounds, 1e30f, 1e30f, -1e30f, -1e30f);

            for (i = 0; i < count; i++) {
                br_board *b = &level->boards.boards[i];
                br_aabb *bb = &level->boards.bounds;
                read_vec(&r, &b->v1);
                read_vec(&r, &b->v2);
                if (b->v1.x < bb->min.x) bb->min.x = b->v1.x;
                if (b->v2.x < bb->min.x) bb->min.x = b->v2.x;
                if (b->v1.x > bb->max.x) bb->max.x = b->v1.x;
                if (b->v2.x > bb->max.x) bb->max.x = b->v2.x;
                if (b->v1.y < bb->min.y) bb->min.y = b->v1.y;
                if (b->v2.y < bb->min.y) bb->min.y = b->v2.y;
                if (b->v1.y > bb->max.y) bb->max.y = b->v1.y;
                if (b->v2.y > bb->max.y) bb->max.y = b->v2.y;
            }
            br_boards_build_index(&level->boards);
        }
    }

    if (!r.ok) {
        LOGE("levels: blob truncated");
        return -1;
    }

    LOGI("levels: %d worlds loaded from %d bytes",
         pack->world_count, (int)(br_levels_blob_end - br_levels_blob_start));
    return 0;
}

void br_levels_free(br_level_pack *pack)
{
    int w, l;
    for (w = 0; w < pack->world_count; w++) {
        for (l = 0; l < pack->worlds[w].level_count; l++)
            br_boards_free(&pack->worlds[w].levels[l].boards);
        free(pack->worlds[w].levels);
    }
    free(pack->worlds);
    memset(pack, 0, sizeof(*pack));
}

br_level *br_levels_get(br_level_pack *pack, int world_index, int level_index)
{
    br_world *world;

    if (world_index < 0 || world_index >= pack->world_count)
        return NULL;
    world = &pack->worlds[world_index];
    if (level_index < 0 || level_index >= world->level_count)
        return NULL;
    return &world->levels[level_index];
}

int br_level_stars(const br_level *level, float seconds)
{
    int i;
    /* star_times is ordered slowest to fastest: beating [0] earns 1 star, [1]
     * two, [2] three. Comparing truncated hundredths, as the original does,
     * makes a time exactly on a threshold count for the better tier. */
    for (i = 0; i < 3; i++) {
        if ((int)(level->star_times[i] * 100.0f) < (int)(seconds * 100.0f))
            return i;
    }
    return 3;
}
