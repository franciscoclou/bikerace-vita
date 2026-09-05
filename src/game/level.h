#ifndef BR_LEVEL_H
#define BR_LEVEL_H

#include "board.h"

/* Levels come from data/levels.bin, produced by tools/leveldump running the
 * game's own LevelFactoryWorld* code. See scripts/dumplevels.sh. */

typedef struct {
    br_boards boards;
    float     star_times[3];   /* seconds for 3, 2, 1 stars */
    vec2      start;
    vec2      finish;
} br_level;

typedef struct {
    int       id;              /* 1..19 */
    int       atlas;           /* index into the atlas table in scene.c */
    br_level *levels;
    int       level_count;
} br_world;

typedef struct {
    br_world *worlds;
    int       world_count;
} br_level_pack;

int  br_levels_load(br_level_pack *pack);
void br_levels_free(br_level_pack *pack);

br_level *br_levels_get(br_level_pack *pack, int world_index, int level_index);

/* Stars earned for a finish time: 3, 2, 1, or 0. */
int  br_level_stars(const br_level *level, float seconds);

#endif /* BR_LEVEL_H */
