#ifndef BR_GAME_H
#define BR_GAME_H

#include "../platform/input.h"
#include "audio.h"
#include "bike.h"
#include "camera.h"
#include "level.h"
#include "scene.h"

typedef enum {
    BR_STATE_WAITING_START = 0,
    BR_STATE_RUNNING,
    BR_STATE_FINISHED,
    BR_STATE_DEAD
} br_game_state;

typedef struct {
    br_level_pack pack;
    br_scene      scene;
    br_camera     camera;
    br_bike       bike;

    br_level     *level;
    int           world_index;
    int           level_index;
    br_bike_type  bike_type;

    br_game_state state;
    float         elapsed;        /* race clock, seconds */
    float         crashed_at;
    int           stars;

    br_game_audio audio;

    int           rear_grounded;  /* Java field D */
    int           front_grounded; /* Java field E */
    /* Lagged "is on the ground", 0..1. A landing only sounds when this has had
     * time to fall, which is how the original told a landing from a scrape. */
    float         ground_factor;

    /* Holding brake past a standstill backs the bike up. Latched until the
     * button is released, so it cannot chatter against the speed threshold. */
    float         brake_held;
    int           reversing;

    int          *nearby;         /* scratch for broad-phase results */
    int           nearby_max;
} br_game;

int  br_game_init(br_game *game);
void br_game_free(br_game *game);

int  br_game_load(br_game *game, int world_index, int level_index);
void br_game_restart(br_game *game);
int  br_game_next_level(br_game *game);

void br_game_update(br_game *game, const br_input *in, float dt);

#endif /* BR_GAME_H */
