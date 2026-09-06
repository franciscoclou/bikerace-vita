#include "game.h"

#include <math.h>
#include <stdlib.h>
#include <string.h>

#include "../platform/log.h"

/* The simulation runs at a fixed 3 ms regardless of frame rate; the original
 * subdivided every frame this way and the handling depends on it. */
#define PHYSICS_STEP    0.003f
#define MAX_FRAME_DT    0.05f
#define CRASH_LINGER    0.5f
#define CAMERA_LEAD     0.9f

/* Out-of-bounds margins, from Game.outOfBounds(). */
#define OOB_MARGIN_X    1.0f
#define OOB_MARGIN_Y    0.5f

/* The original read lean from the accelerometer and clamped it to this before
 * handing it to the bike, so the stick has to respect the same range or the
 * bike rotates far harder than it ever could on Android. */
#define LEAN_LIMIT      0.5f

/* Brake held this long with the bike this slow engages reverse. */
#define REVERSE_HOLD    0.25f
#define REVERSE_SPEED   0.5f

int br_game_init(br_game *game)
{
    memset(game, 0, sizeof(*game));

    if (br_levels_load(&game->pack) < 0)
        return -1;
    if (br_scene_init(&game->scene) < 0)
        return -1;

    /* A query can return every board in a level, so size the scratch to the
     * biggest one rather than a guess -- overflowing it would silently drop
     * collisions and drive the bike through the track. */
    {
        int w, l;
        for (w = 0; w < game->pack.world_count; w++)
            for (l = 0; l < game->pack.worlds[w].level_count; l++)
                if (game->pack.worlds[w].levels[l].boards.count > game->nearby_max)
                    game->nearby_max = game->pack.worlds[w].levels[l].boards.count;
    }
    game->nearby = malloc(sizeof(int) * (size_t)game->nearby_max);
    if (!game->nearby) {
        LOGE("game: out of memory for broad-phase scratch (%d)", game->nearby_max);
        return -1;
    }
    LOGI("game: broad-phase scratch sized for %d boards", game->nearby_max);
    game->bike_type = BR_BIKE_REGULAR;
    br_game_audio_init(&game->audio);
    return 0;
}

void br_game_free(br_game *game)
{
    br_game_audio_free(&game->audio);
    free(game->nearby);
    br_scene_free(&game->scene);
    br_levels_free(&game->pack);
    memset(game, 0, sizeof(*game));
}

static void reset_run(br_game *game)
{
    br_level *level = game->level;

    br_bike_init(&game->bike, game->bike_type, &level->start);
    br_bike_set_state(&game->bike, BR_BIKE_IDLE);
    br_camera_reset(&game->camera, &level->boards.bounds, br_render_aspect());

    game->state      = BR_STATE_WAITING_START;
    game->elapsed    = 0.0f;
    game->crashed_at = 0.0f;
    game->stars      = 0;
    game->rear_grounded = game->front_grounded = 0;
    game->ground_factor = 1.0f;
    game->brake_held = 0.0f;
    game->reversing  = 0;
    game->throttle_locked = 1;
    br_ghost_record_begin(&game->recorder);
    br_game_audio_silence(&game->audio);
}

int br_game_load(br_game *game, int world_index, int level_index)
{
    br_level *level = br_levels_get(&game->pack, world_index, level_index);
    br_world *world;

    if (!level) {
        LOGE("game: no level %d-%d", world_index + 1, level_index + 1);
        return -1;
    }
    world = &game->pack.worlds[world_index];

    if (br_scene_use(&game->scene, world->atlas, game->bike_type) < 0)
        return -1;
    if (br_scene_set_level(&game->scene, level) < 0)
        return -1;

    game->level       = level;
    game->world_index = world_index;
    game->level_index = level_index;
    reset_run(game);

    /* World 16 greets you with one of its four noises, as it always did. */
    if (world->id == 16)
        br_game_audio_spooky(&game->audio);

    LOGI("game: loaded %d-%d -- %d boards, bounds x[%.2f..%.2f] y[%.2f..%.2f], "
         "start (%.2f,%.2f) finish (%.2f,%.2f), stars %.1f/%.1f/%.1f",
         world->id, level_index + 1, level->boards.count,
         level->boards.bounds.min.x, level->boards.bounds.max.x,
         level->boards.bounds.min.y, level->boards.bounds.max.y,
         level->start.x, level->start.y, level->finish.x, level->finish.y,
         level->star_times[0], level->star_times[1], level->star_times[2]);
    return 0;
}

void br_game_restart(br_game *game)
{
    LOGI("game: restarting %d-%d", game->world_index + 1, game->level_index + 1);
    reset_run(game);
}

int br_game_next_level(br_game *game)
{
    br_world *world = &game->pack.worlds[game->world_index];
    int world_index = game->world_index;
    int level_index = game->level_index + 1;

    if (level_index >= world->level_count) {
        level_index = 0;
        world_index++;
        if (world_index >= game->pack.world_count)
            world_index = 0;
    }
    return br_game_load(game, world_index, level_index);
}

static int reached_finish(const br_game *game)
{
    const vec2 *head = &game->bike.head.pos;
    return head->x > game->level->finish.x && head->y > game->level->finish.y;
}

static int out_of_bounds(const br_game *game)
{
    const vec2 *head = &game->bike.head.pos;
    const br_aabb *b = &game->level->boards.bounds;
    return head->x < b->min.x - OOB_MARGIN_X ||
           head->x > b->max.x + OOB_MARGIN_X ||
           head->y < b->min.y - OOB_MARGIN_Y;
}

/* One frame of physics, subdivided the way Game.step() did it. */
static void step_physics(br_game *game, float lean, float frame_dt)
{
    br_bike *bike = &game->bike;
    vec2 gravity;
    br_aabb bounds;
    int substeps, count, i, n;
    /* Only the first contact of the frame is sounded, as in the original. */
    int sounded = 0;

    substeps = (int)(frame_dt / PHYSICS_STEP) + 1;
    {
        float even = frame_dt / (float)substeps;
        frame_dt = even < PHYSICS_STEP ? even : PHYSICS_STEP;
    }

    v2_set(&gravity, 0.0f, bike->def->gravity_y);

    game->rear_grounded = game->front_grounded = 0;

    br_bike_bounds(bike, &bounds, 0.2f);
    count = br_boards_query(&game->level->boards, bounds.min.x, bounds.max.x,
                            game->nearby, game->nearby_max);

    for (n = 0; n < substeps; n++) {
        br_bike_apply_gravity(bike, &gravity, frame_dt);
        br_bike_apply_springs(bike, frame_dt);

        for (i = 0; i < count; i++) {
            const br_board *board = &game->level->boards.boards[game->nearby[i]];
            float force;

            force = br_bike_collide_rear(bike, board, frame_dt);
            if (force > 0.0f) {
                game->rear_grounded = 1;
                if (!sounded) {
                    br_game_audio_impact(&game->audio, force, game->ground_factor);
                    sounded = 1;
                }
            }

            force = br_bike_collide_front(bike, board, frame_dt);
            if (force > 0.0f) {
                game->front_grounded = 1;
                if (!sounded) {
                    br_game_audio_impact(&game->audio, force, game->ground_factor);
                    sounded = 1;
                }
            }

            force = br_bike_collide_head(bike, board, frame_dt);
            if (force > 0.0f && !sounded) {
                br_game_audio_impact(&game->audio, force, game->ground_factor);
                sounded = 1;
            }
        }

        br_bike_step(bike, frame_dt);
        if (!br_bike_crashed(bike))
            br_bike_apply_lean(bike, lean, frame_dt, game->front_grounded);
    }

    for (i = 0; i < count; i++) {
        const br_board *board = &game->level->boards.boards[game->nearby[i]];
        if (br_bike_head_hits(bike, board)) {
            LOGI("game: crashed at t=%.2f, head (%.2f,%.2f)",
                 game->elapsed, bike->head.pos.x, bike->head.pos.y);
            br_bike_set_state(bike, BR_BIKE_CRASHED);
            br_game_audio_crash(&game->audio);
            game->crashed_at = game->elapsed;
            break;
        }
    }

    game->ground_factor +=
        frame_dt * (((game->front_grounded || game->rear_grounded) ? 1.0f : 0.0f) -
                    game->ground_factor) * 3.0f;
}

static void update_camera(br_game *game, float dt)
{
    int racing = game->state == BR_STATE_RUNNING;
    float rate   = racing ? (dt < 1.0f / 6.0f ? 6.0f : 1.0f / dt)
                          : (dt < 0.25f       ? 4.0f : 1.0f / dt);
    float settle = racing ? 2.0f : 4.0f;

    br_camera_follow(&game->camera, &game->bike.chassis.com,
                     &game->bike.chassis.com_vel, CAMERA_LEAD, dt,
                     settle, rate, 0, br_render_aspect());
}

void br_game_lock_throttle(br_game *game)
{
    game->throttle_locked = 1;
}

void br_game_update(br_game *game, const br_input *in, float dt)
{
    br_bike_state want;
    int accelerate;

    if (dt > MAX_FRAME_DT)
        dt = MAX_FRAME_DT;

    /* A held throttle only counts once it has been released and pressed
     * again, so dismissing a menu with Cross cannot also launch the bike. */
    accelerate = in->accelerate;
    if (game->throttle_locked) {
        if (accelerate)
            accelerate = 0;
        else
            game->throttle_locked = 0;
    }

    switch (game->state) {
    case BR_STATE_WAITING_START:
        if (accelerate || in->brake) {
            game->state = BR_STATE_RUNNING;
            LOGI("game: race started on %d-%d",
                 game->world_index + 1, game->level_index + 1);
        }
        break;

    case BR_STATE_FINISHED:
    case BR_STATE_DEAD:
        break;

    case BR_STATE_RUNNING:
        break;
    }

    if (game->state == BR_STATE_RUNNING) {
        game->elapsed += dt;

        if (in->brake) {
            game->brake_held += dt;
            if (!game->reversing && game->brake_held >= REVERSE_HOLD &&
                v2_len(&game->bike.chassis.com_vel) < REVERSE_SPEED) {
                game->reversing = 1;
                LOGI("game: reverse engaged at t=%.2f", game->elapsed);
            }
        } else {
            game->brake_held = 0.0f;
            game->reversing  = 0;
        }

        if (accelerate)       want = BR_BIKE_ACCELERATING;
        else if (in->brake)   want = game->reversing ? BR_BIKE_REVERSING
                                                     : BR_BIKE_BRAKING;
        else                  want = BR_BIKE_IDLE;

        if (!br_bike_crashed(&game->bike))
            br_bike_set_state(&game->bike, want);
        else if (game->elapsed - game->crashed_at > CRASH_LINGER)
            game->state = BR_STATE_DEAD;

        {
            /* Positive torque rotates the bike backwards, so leaning forward
             * -- stick right -- has to feed in a negative value. */
            float lean = -in->lean * LEAN_LIMIT;
            if (lean >  LEAN_LIMIT) lean =  LEAN_LIMIT;
            if (lean < -LEAN_LIMIT) lean = -LEAN_LIMIT;
            step_physics(game, lean, dt);
        }

        br_ghost_record_tick(&game->recorder, game->elapsed,
                             &game->bike.head.pos,
                             br_bike_angle_deg(&game->bike));

        br_game_audio_tick(&game->audio, dt);
        br_game_audio_engine(&game->audio,
                             game->bike.state == BR_BIKE_ACCELERATING,
                             v2_len(&game->bike.rear.vel),
                             game->rear_grounded, dt);

        if (reached_finish(game)) {
            game->stars = br_level_stars(game->level, game->elapsed);
            game->state = BR_STATE_FINISHED;
            br_game_audio_silence(&game->audio);
            br_game_audio_win(&game->audio);
            LOGI("game: finished %d-%d in %.2fs -- %d stars",
                 game->world_index + 1, game->level_index + 1,
                 game->elapsed, game->stars);
        } else if (out_of_bounds(game)) {
            LOGI("game: out of bounds at (%.2f,%.2f)",
                 game->bike.head.pos.x, game->bike.head.pos.y);
            br_game_audio_silence(&game->audio);
            game->state = BR_STATE_DEAD;
        }
    }

    update_camera(game, dt);
}
