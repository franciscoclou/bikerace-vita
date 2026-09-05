/* Host-side checks for the ported game logic.
 *
 * These run the real physics, level data and camera on the desktop so the
 * behaviour can be inspected without flashing the Vita. Rendering is stubbed;
 * only the transform maths is checked, not pixels. */

#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "../src/engine/render.h"
#include "../src/game/game.h"
#include "stub/vitaGL.h"

void br_test_load_blob(const char *path);
extern int br_test_log_verbose;

static int  g_failures;
static const char *g_case;

#define CHECK(cond, ...) do {                                    \
    if (!(cond)) {                                               \
        printf("  FAIL %s: ", g_case);                           \
        printf(__VA_ARGS__);                                     \
        printf("\n         (%s, line %d)\n", #cond, __LINE__);   \
        g_failures++;                                            \
    }                                                            \
} while (0)

static void begin(const char *name) { g_case = name; printf("== %s\n", name); }

/* ---------------------------------------------------------------- geometry */

static void test_geometry(void)
{
    vec2 a, b, p, c;

    begin("geometry");

    v2_set(&a, 0, 0); v2_set(&b, 10, 0);

    v2_set(&p, 5, 3);
    c = br_closest_point_on_segment(&p, &a, &b);
    CHECK(fabsf(c.x - 5.0f) < 1e-4f && fabsf(c.y) < 1e-4f,
          "closest point above midpoint was (%.3f,%.3f), want (5,0)", c.x, c.y);

    v2_set(&p, -4, 1);
    c = br_closest_point_on_segment(&p, &a, &b);
    CHECK(fabsf(c.x) < 1e-4f && fabsf(c.y) < 1e-4f,
          "closest point before start was (%.3f,%.3f), want (0,0)", c.x, c.y);

    v2_set(&p, 40, -2);
    c = br_closest_point_on_segment(&p, &a, &b);
    CHECK(fabsf(c.x - 10.0f) < 1e-4f, "closest point past end was (%.3f,%.3f), want (10,0)",
          c.x, c.y);

    {
        vec2 c1, d1;
        v2_set(&c1, 5, -5); v2_set(&d1, 5, 5);
        CHECK(br_segments_intersect(&a, &b, &c1, &d1), "crossing segments reported as disjoint");
        v2_set(&c1, 5, 1); v2_set(&d1, 5, 5);
        CHECK(!br_segments_intersect(&a, &b, &c1, &d1), "disjoint segments reported as crossing");
    }

    /* A point above a left-to-right board must be on the positive side, which
     * is what decides the drive direction. */
    v2_set(&p, 5, 1);
    CHECK(br_side(&a, &b, &p) > 0.0f, "point above board had side %.3f, want > 0",
          br_side(&a, &b, &p));
}

/* ------------------------------------------------------------------ physics */

static void test_spring_rest(void)
{
    br_body a, b;
    br_spring s;
    vec2 pa, pb, zero;
    float dist;
    int i;

    begin("spring settles at its rest length");

    v2_set(&zero, 0, 0);
    v2_set(&pa, 0, 0);
    v2_set(&pb, 3, 0);
    br_body_init(&a, &pa, &zero, 1.0f, 0, 0);
    br_body_init(&b, &pb, &zero, 1.0f, 0, 0);
    br_spring_init(&s, 1600.0f, 40.0f, 1.0f);

    for (i = 0; i < 20000; i++) {
        br_spring_apply(&s, &a, &b, 0.001f);
        br_body_step(&a, 0.001f);
        br_body_step(&b, 0.001f);
    }
    dist = fabsf(b.pos.x - a.pos.x);
    CHECK(fabsf(dist - 1.0f) < 0.01f, "settled at %.4f, want 1.0", dist);
}

static void test_composed_body(void)
{
    br_body bodies[3];
    br_composed_body cb;
    vec2 p, zero;
    int i;

    begin("composed body centre of mass and inertia");

    v2_set(&zero, 0, 0);
    br_composed_init(&cb);
    for (i = 0; i < 3; i++) {
        v2_set(&p, (float)i, 0.0f);
        br_body_init(&bodies[i], &p, &zero, 1.0f, 0, 0);
        br_composed_add(&cb, &bodies[i]);
    }
    br_composed_update(&cb);

    CHECK(fabsf(cb.com.x - 1.0f) < 1e-4f && fabsf(cb.com.y) < 1e-4f,
          "centre of mass (%.4f,%.4f), want (1,0)", cb.com.x, cb.com.y);
    CHECK(fabsf(cb.inertia - 2.0f) < 1e-4f, "inertia %.4f, want 2.0", cb.inertia);

    /* Spinning about the centre must leave the centre where it is. */
    br_composed_apply_spin(&cb, 1.0f);
    br_composed_update(&cb);
    CHECK(fabsf(cb.com_vel.x) < 1e-4f && fabsf(cb.com_vel.y) < 1e-4f,
          "spin moved the centre of mass at (%.4f,%.4f)", cb.com_vel.x, cb.com_vel.y);
}

/* -------------------------------------------------------------- level data */

static void test_levels(br_game *game)
{
    int w, l, total = 0;

    begin("level pack");

    CHECK(game->pack.world_count == 19, "%d worlds, want 19", game->pack.world_count);

    for (w = 0; w < game->pack.world_count; w++) {
        br_world *world = &game->pack.worlds[w];
        CHECK(world->level_count == 8, "world %d has %d levels, want 8",
              world->id, world->level_count);
        CHECK(world->atlas >= 0 && world->atlas < 10,
              "world %d atlas %d out of range", world->id, world->atlas);

        for (l = 0; l < world->level_count; l++) {
            br_level *lv = &world->levels[l];
            float span = br_aabb_width(&lv->boards.bounds);

            CHECK(lv->boards.count > 0, "%d-%d has no boards", world->id, l + 1);
            CHECK(span > 1.0f, "%d-%d spans only %.2f units", world->id, l + 1, span);
            /* Start and finish must sit inside the track, but not in any
             * particular order: several levels run right-to-left or climb, and
             * the imported ones carry an explicit finish from their blob. */
            CHECK(lv->finish.x >= lv->boards.bounds.min.x - 1.0f &&
                  lv->finish.x <= lv->boards.bounds.max.x + 1.0f,
                  "%d-%d finish x %.2f outside bounds [%.2f, %.2f]",
                  world->id, l + 1, lv->finish.x,
                  lv->boards.bounds.min.x, lv->boards.bounds.max.x);
            /* Star times are not always descending -- some imported levels
             * carry an order that makes the middle tier unreachable. That is
             * the shipped data, so only sanity is checked here. */
            CHECK(lv->star_times[0] > 0.0f && lv->star_times[0] < 1000.0f,
                  "%d-%d has an implausible first star time %.1f",
                  world->id, l + 1, lv->star_times[0]);
            total += lv->boards.count;
        }
    }
    CHECK(total == 30284, "%d boards across the pack, want 30284", total);
}

static void test_stars(br_game *game)
{
    br_level *lv = br_levels_get(&game->pack, 0, 0);

    begin("star thresholds");

    /* 1-1 is 20 / 14 / 10 seconds. */
    CHECK(br_level_stars(lv, 25.0f) == 0, "25s gave %d stars, want 0", br_level_stars(lv, 25.0f));
    CHECK(br_level_stars(lv, 16.0f) == 1, "16s gave %d stars, want 1", br_level_stars(lv, 16.0f));
    CHECK(br_level_stars(lv, 12.0f) == 2, "12s gave %d stars, want 2", br_level_stars(lv, 12.0f));
    CHECK(br_level_stars(lv,  8.0f) == 3, "8s gave %d stars, want 3", br_level_stars(lv, 8.0f));
    CHECK(br_level_stars(lv, 10.0f) == 3, "exactly 10s gave %d stars, want 3",
          br_level_stars(lv, 10.0f));
}

static void test_broadphase(br_game *game)
{
    br_level *lv = br_levels_get(&game->pack, 0, 0);
    int *hits = malloc(sizeof(int) * (size_t)lv->boards.count);
    int i, seen = 0;
    char *counted = calloc((size_t)lv->boards.count, 1);

    begin("broad-phase index");

    CHECK(lv->boards.slab_count > 0, "no slabs built");

    /* Every board must live in exactly one slab. */
    for (i = 0; i < lv->boards.slab_count; i++) {
        int j;
        for (j = 0; j < lv->boards.slabs[i].count; j++) {
            int idx = lv->boards.order[lv->boards.slabs[i].first + j];
            CHECK(idx >= 0 && idx < lv->boards.count, "slab holds board index %d", idx);
            CHECK(!counted[idx], "board %d appears in more than one slab", idx);
            counted[idx] = 1;
            seen++;
        }
    }
    CHECK(seen == lv->boards.count, "slabs hold %d of %d boards", seen, lv->boards.count);

    /* Querying the whole level must return everything. */
    i = br_boards_query(&lv->boards, -1e9f, 1e9f, hits, lv->boards.count);
    CHECK(i == lv->boards.count, "full-width query returned %d of %d", i, lv->boards.count);

    free(hits);
    free(counted);
}

/* ------------------------------------------------------------- transforms */

/* Reproduces br_scene_draw's matrix setup and reports where a world point
 * lands in clip space. */
static void world_to_clip(const br_camera *cam, float wx, float wy,
                          float *cx, float *cy)
{
    br_test_gl_reset();
    br_identity();
    br_scale(1.0f / br_render_aspect(), 1.0f);
    br_scale(1.0f / cam->scale, 1.0f / cam->scale);
    br_translate(-cam->pos.x, -cam->pos.y);
    br_test_gl_transform(wx, wy, cx, cy);
}

static void test_camera_transform(br_game *game)
{
    br_camera cam;
    float x, y, aspect = br_render_aspect();

    begin("camera transform");

    memset(&cam, 0, sizeof(cam));
    cam.scale = 2.5f;
    v2_set(&cam.pos, 10.0f, 4.0f);

    world_to_clip(&cam, 10.0f, 4.0f, &x, &y);
    CHECK(fabsf(x) < 1e-5f && fabsf(y) < 1e-5f,
          "camera centre mapped to (%.4f,%.4f), want (0,0)", x, y);

    /* One camera-scale above the centre is the top of the screen. */
    world_to_clip(&cam, 10.0f, 4.0f + cam.scale, &x, &y);
    CHECK(fabsf(y - 1.0f) < 1e-5f, "top of view mapped to y=%.4f, want 1.0", y);

    /* The visible half-width is scale * aspect. */
    world_to_clip(&cam, 10.0f + cam.scale * aspect, 4.0f, &x, &y);
    CHECK(fabsf(x - 1.0f) < 1e-5f, "right of view mapped to x=%.4f, want 1.0", x);

    /* Up in the world must be up on screen. */
    world_to_clip(&cam, 10.0f, 5.0f, &x, &y);
    CHECK(y > 0.0f, "raising world y gave clip y=%.4f, want > 0", y);

    (void)game;
}

static void test_camera_follow(br_game *game)
{
    br_level *lv = br_levels_get(&game->pack, 0, 0);
    br_camera cam;
    vec2 target, velocity;
    int i;

    begin("camera follows the bike");

    br_camera_reset(&cam, &lv->boards.bounds, br_render_aspect());
    v2_set(&target, 20.0f, 1.0f);
    v2_set(&velocity, 6.0f, 0.0f);

    for (i = 0; i < 600; i++)
        br_camera_follow(&cam, &target, &velocity, 0.9f, 1.0f / 60.0f,
                         2.0f, 6.0f, 0, br_render_aspect());

    CHECK(fabsf(cam.pos.x - (target.x + cam.scale * 0.9f)) < 0.05f,
          "settled at x=%.3f, want %.3f", cam.pos.x, target.x + cam.scale * 0.9f);
    CHECK(cam.scale > 0.5f && cam.scale < 2.5f, "scale settled at %.3f", cam.scale);
    CHECK(cam.pos.y >= lv->boards.bounds.min.y + cam.scale - 0.3f,
          "camera dipped below the level floor: y=%.3f", cam.pos.y);
}

/* ------------------------------------------------------------- gameplay run */

typedef struct {
    int   finished, crashed, out_of_bounds;
    float time, distance, top_speed;
    int   frames;
} run_result;

/* Keeps the bike level, which is what the lean control is for. Holding the
 * throttle with no lean flips the bike on the first jump, on Android too. */
static float autopilot_lean(const br_bike *bike)
{
    float want = br_bike_angle_deg(bike) / 45.0f;
    if (want >  1.0f) want =  1.0f;
    if (want < -1.0f) want = -1.0f;
    return want;
}

static run_result play(br_game *game, int world, int level, int stabilise, float limit)
{
    run_result r;
    br_input in;
    float start_x;
    int frame;

    memset(&r, 0, sizeof(r));
    memset(&in, 0, sizeof(in));

    br_game_load(game, world, level);
    start_x = game->bike.head.pos.x;

    in.accelerate = 1;
    game->state = BR_STATE_RUNNING;

    for (frame = 0; frame < (int)(limit * 60.0f); frame++) {
        float speed;

        in.lean = stabilise ? autopilot_lean(&game->bike) : 0.0f;
        br_game_update(game, &in, 1.0f / 60.0f);
        speed = v2_len(&game->bike.chassis.com_vel);
        if (speed > r.top_speed)
            r.top_speed = speed;

        if (game->state == BR_STATE_FINISHED) { r.finished = 1; break; }
        if (game->state == BR_STATE_DEAD)     { r.crashed = 1;  break; }
    }

    r.frames   = frame;
    r.time     = game->elapsed;
    r.distance = game->bike.head.pos.x - start_x;
    return r;
}

static void test_bike_drives(br_game *game)
{
    run_result r;

    begin("bike accelerates and holds the ground");

    r = play(game, 0, 0, 0, 3.0f);

    CHECK(r.distance > 3.0f, "travelled only %.2f units in 3s", r.distance);
    CHECK(r.top_speed > 2.0f, "top speed only %.2f", r.top_speed);
    CHECK(game->bike.head.pos.y > -1.0f, "bike fell through the track to y=%.2f",
          game->bike.head.pos.y);
    CHECK(!r.crashed, "crashed on flat ground within 3s");

    printf("     3s run: %.2f units, top speed %.2f, head y %.2f\n",
           r.distance, r.top_speed, game->bike.head.pos.y);
}

static void test_finishes_first_level(br_game *game)
{
    run_result r;

    begin("level 1-1 can be completed with a level-keeping rider");

    r = play(game, 0, 0, 1, 90.0f);

    CHECK(r.finished, "did not finish in 90s (crashed=%d, reached x=%.2f of %.2f)",
          r.crashed, game->bike.head.pos.x, game->level->finish.x);
    if (r.finished)
        printf("     finished in %.2fs (%d stars, thresholds %.0f/%.0f/%.0f)\n",
               r.time, br_level_stars(game->level, r.time),
               game->level->star_times[0], game->level->star_times[1],
               game->level->star_times[2]);
}

static void test_no_level_explodes(br_game *game)
{
    int w, l, bad = 0;

    begin("every level simulates without blowing up");

    for (w = 0; w < game->pack.world_count; w++) {
        for (l = 0; l < 8; l++) {
            run_result r = play(game, w, l, 1, 4.0f);
            const vec2 *h = &game->bike.head.pos;

            if (isnan(h->x) || isnan(h->y) || fabsf(h->x) > 1e5f || fabsf(h->y) > 1e5f) {
                printf("  FAIL %d-%d: head diverged to (%.3g,%.3g)\n", w + 1, l + 1,
                       h->x, h->y);
                bad++;
            }
            (void)r;
        }
    }
    CHECK(bad == 0, "%d levels diverged", bad);
}

static void test_track_mesh(br_game *game)
{
    br_level *lv = br_levels_get(&game->pack, 0, 0);
    br_image img;
    br_texture tex;
    br_mesh mesh;
    int i, verts = 0;

    begin("track mesh");

    memset(&img, 0, sizeof(img));
    img.width = img.height = 64;
    tex = br_texture_region(&img, 0.0f, 0.0f, 1.0f, 1.0f);

    CHECK(br_track_mesh_build(&mesh, &lv->boards, &tex, 0.05f) == 0, "mesh build failed");
    CHECK(mesh.count > 0, "mesh has no buffers");

    for (i = 0; i < mesh.count; i++)
        verts += mesh.buffers[i].vertex_count;
    CHECK(verts == lv->boards.count * 4, "%d vertices for %d boards, want %d",
          verts, lv->boards.count, lv->boards.count * 4);

    /* The ribbon must hang below the track line, not above it. */
    {
        const br_mesh_buffer *b = &mesh.buffers[0];
        CHECK(b->xy[3] < b->xy[1] + 1e-6f,
              "ribbon offset points up: surface y=%.4f, skirt y=%.4f", b->xy[1], b->xy[3]);
    }

    br_mesh_free(&mesh);
}

static void test_reverse(br_game *game)
{
    br_input in;
    float start_x, after_brake_x;
    int i;

    begin("holding brake past a standstill reverses");

    br_game_load(game, 0, 0);
    memset(&in, 0, sizeof(in));
    game->state = BR_STATE_RUNNING;

    /* Get moving. */
    in.accelerate = 1;
    for (i = 0; i < 90; i++)
        br_game_update(game, &in, 1.0f / 60.0f);
    start_x = game->bike.head.pos.x;
    CHECK(start_x > 1.0f, "did not get moving: x=%.2f", start_x);

    /* Now hold the brake and keep holding it. */
    in.accelerate = 0;
    in.brake = 1;
    for (i = 0; i < 120; i++) {
        br_game_update(game, &in, 1.0f / 60.0f);
        if (game->reversing)
            break;
    }
    CHECK(game->reversing, "brake never engaged reverse in 2s");
    after_brake_x = game->bike.head.pos.x;

    for (i = 0; i < 90; i++)
        br_game_update(game, &in, 1.0f / 60.0f);
    CHECK(game->bike.head.pos.x < after_brake_x - 0.3f,
          "reversing moved from x=%.2f to x=%.2f, expected to go backwards",
          after_brake_x, game->bike.head.pos.x);
    printf("     reversed %.2f units in 1.5s\n", after_brake_x - game->bike.head.pos.x);

    /* Releasing the brake must drop out of reverse. */
    in.brake = 0;
    br_game_update(game, &in, 1.0f / 60.0f);
    CHECK(!game->reversing, "still reversing after the brake was released");
}

static void test_lean_direction(br_game *game)
{
    br_input in;
    float leaned_forward, leaned_back;
    int i;

    begin("stick right leans the bike forward");

    memset(&in, 0, sizeof(in));

    /* Lift the bike clear of the track first: on the ground the nose cannot
     * pitch down, and left over a longer run wraps past 180 degrees. */
    for (i = 0; i < 2; i++) {
        float *out = i == 0 ? &leaned_forward : &leaned_back;
        int f;

        br_game_load(game, 0, 0);
        game->bike.head.pos.y  += 3.0f;
        game->bike.front.pos.y += 3.0f;
        game->bike.rear.pos.y  += 3.0f;
        br_composed_update(&game->bike.chassis);
        game->state = BR_STATE_RUNNING;

        in.lean = i == 0 ? 1.0f : -1.0f;   /* stick right, then stick left */
        for (f = 0; f < 20; f++)
            br_game_update(game, &in, 1.0f / 60.0f);
        *out = br_bike_angle_deg(&game->bike);
    }

    CHECK(leaned_forward < -1.0f, "stick right gave %.1f deg, want the nose down "
          "(negative)", leaned_forward);
    CHECK(leaned_back > 1.0f, "stick left gave %.1f deg, want the nose up "
          "(positive)", leaned_back);
    printf("     over 1/3 s airborne: stick right %.1f deg, stick left %.1f deg\n",
           leaned_forward, leaned_back);
}

static void test_bike_sprite_region(void)
{
    int i;

    begin("bike sprite regions");

    for (i = 0; i < BR_BIKE_TYPE_COUNT; i++) {
        const br_bike_def *def = br_bike_def_for((br_bike_type)i);
        float w = def->sprite_uv[2] - def->sprite_uv[0];
        float h = def->sprite_uv[3] - def->sprite_uv[1];

        CHECK(w > 0.1f && w <= 1.0f, "%s sprite region width %.4f", def->name, w);
        CHECK(h > 0.1f && h <= 1.0f, "%s sprite region height %.4f", def->name, h);
        /* Drawing the whole file instead of the region floats the bike up and
         * to the left, so guard against the region silently becoming 0..1. */
        CHECK(w < 1.0f || h < 1.0f, "%s sprite region covers the whole file", def->name);
    }
}

int main(int argc, char **argv)
{
    br_game game;

    br_test_log_verbose = (argc > 1 && strcmp(argv[1], "-v") == 0);
    br_test_load_blob("data/levels.bin");

    test_geometry();
    test_spring_rest();
    test_composed_body();

    if (br_game_init(&game) < 0) {
        printf("FATAL: game init failed\n");
        return 1;
    }

    test_levels(&game);
    test_stars(&game);
    test_broadphase(&game);
    test_camera_transform(&game);
    test_camera_follow(&game);
    test_track_mesh(&game);
    test_bike_sprite_region();
    test_lean_direction(&game);
    test_reverse(&game);
    test_bike_drives(&game);
    test_finishes_first_level(&game);
    test_no_level_explodes(&game);

    br_game_free(&game);

    printf("\n%s: %d failure(s)\n", g_failures ? "FAILED" : "OK", g_failures);
    return g_failures ? 1 : 0;
}
