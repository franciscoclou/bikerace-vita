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
#include "../src/game/audio.h"
#include "../src/game/ghost.h"
#include "../src/game/save.h"
#include "../src/ui/font.h"
#include "../src/app.h"
#include "../src/ui/menu.h"
#include "../src/ui/result.h"
#include "../src/ui/settings.h"
#include "../src/platform/mixer.h"
#include "../src/ui/start.h"
#include "../src/game/game.h"
#include "stub/vitaGL.h"

void br_test_load_blobs(void);
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

    game->state = BR_STATE_RUNNING;

    /* One frame with the throttle shut clears the lock a fresh run starts
     * with, the same way letting go of Cross does on the Vita. */
    br_game_update(game, &in, 1.0f / 60.0f);
    in.accelerate = 1;

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

    /* Get moving, letting go for a frame first so the throttle unlatches. */
    br_game_update(game, &in, 1.0f / 60.0f);
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
        br_game_update(game, &in, 1.0f / 60.0f);
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

extern const unsigned char *br_font_display_start;
extern const unsigned char *br_font_display_end;
extern const unsigned char *br_font_body_start;
extern const unsigned char *br_font_body_end;

static void test_fonts(void)
{
    struct { const char *name; const unsigned char **s, **e; } faces[] = {
        { "display", &br_font_display_start, &br_font_display_end },
        { "body",    &br_font_body_start,    &br_font_body_end    },
    };
    int i;

    begin("fonts");

    for (i = 0; i < 2; i++) {
        br_font font;
        float narrow, wide;

        CHECK(br_font_load(&font, *faces[i].s,
                           (unsigned)(*faces[i].e - *faces[i].s)) == 0,
              "%s font failed to load", faces[i].name);
        if (!font.glyphs)
            continue;

        CHECK(font.glyph_count == 95, "%s has %d glyphs, want 95",
              faces[i].name, font.glyph_count);
        CHECK(font.line_height > 8, "%s line height %d looks wrong",
              faces[i].name, font.line_height);

        narrow = br_font_width(&font, "1", 32.0f);
        wide   = br_font_width(&font, "WORLD 12", 32.0f);
        CHECK(narrow > 0.0f, "%s measured '1' as %.2f", faces[i].name, narrow);
        CHECK(wide > narrow * 4.0f, "%s measured 'WORLD 12' as %.2f against "
              "'1' at %.2f", faces[i].name, wide, narrow);
        /* Twice the pixel size is twice the width. */
        CHECK(fabsf(br_font_width(&font, "WORLD 12", 64.0f) - wide * 2.0f) < 0.01f,
              "%s does not scale linearly", faces[i].name);

        br_font_free(&font);
    }
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

static void test_save(br_game *game)
{
    br_save save;

    begin("save round-trips");

    CHECK(br_save_init(&save, game->pack.world_count,
                       game->pack.worlds[0].level_count) == 0, "init failed");
    CHECK(br_save_total_stars(&save) == 0, "a fresh save already has stars");

    br_save_record(&save, 0, 0, 2, 12.5f);
    br_save_record(&save, 3, 4, 3, 20.0f);
    br_save_remember_place(&save, 3, 4);
    CHECK(br_save_total_stars(&save) == 5, "recorded 5 stars, read back %d",
          br_save_total_stars(&save));
    CHECK(br_save_world_stars(&save, 0) == 2, "world 1 has %d stars, want 2",
          br_save_world_stars(&save, 0));

    /* Only the better result sticks. */
    br_save_record(&save, 0, 0, 1, 30.0f);
    CHECK(br_save_level(&save, 0, 0)->stars == 2, "a worse star count overwrote");
    CHECK(br_save_level(&save, 0, 0)->best_time == 12.5f, "a slower time overwrote");
    br_save_record(&save, 0, 0, 3, 9.0f);
    CHECK(br_save_level(&save, 0, 0)->stars == 3, "a better star count was dropped");
    CHECK(br_save_level(&save, 0, 0)->best_time == 9.0f, "a faster time was dropped");

    CHECK(save.dirty, "changes did not mark the save dirty");
    CHECK(br_save_flush(&save) == 0, "flush failed");
    CHECK(!save.dirty, "still dirty after a flush");

    {
        br_save reloaded;
        br_save_init(&reloaded, game->pack.world_count,
                     game->pack.worlds[0].level_count);
        br_save_load(&reloaded);
        CHECK(br_save_total_stars(&reloaded) == 6, "reloaded %d stars, want 6",
              br_save_total_stars(&reloaded));
        CHECK(reloaded.last_world == 3 && reloaded.last_level == 4,
              "reloaded place %d-%d, want 4-5",
              reloaded.last_world + 1, reloaded.last_level + 1);
        CHECK(br_save_level(&reloaded, 0, 0)->best_time == 9.0f,
              "reloaded best time %.2f, want 9.00",
              br_save_level(&reloaded, 0, 0)->best_time);
        br_save_free(&reloaded);
    }

    br_save_free(&save);
    remove("assets_out/save.bin");
}

static void test_gating(br_game *game)
{
    br_save save;
    int per_world = game->pack.worlds[0].level_count;
    int w, l;

    begin("progression gating");

    br_save_init(&save, game->pack.world_count, per_world);

    /* A fresh game opens on 1-1 and nothing else. */
    CHECK(br_save_level_unlocked(&save, 0, 0), "1-1 is not open on a fresh save");
    CHECK(!br_save_level_unlocked(&save, 0, 1), "1-2 is open before 1-1 is done");
    CHECK(!br_save_level_unlocked(&save, 1, 0), "2-1 is open with no stars");

    /* Finishing a level opens the next. */
    br_save_record(&save, 0, 0, 3, 10.0f);
    br_save_unlock_next(&save, 0, 0);
    CHECK(br_save_level_unlocked(&save, 0, 1), "finishing 1-1 did not open 1-2");
    CHECK(!br_save_level_unlocked(&save, 0, 2), "finishing 1-1 opened 1-3 as well");

    /* Finishing the last level of a world rolls into the next world, though
     * the world's own star gate still applies. */
    br_save_unlock_next(&save, 0, per_world - 1);
    CHECK(br_save_level(&save, 1, 0)->unlocked,
          "the last level of world 1 did not open 2-1");
    CHECK(!br_save_level_unlocked(&save, 1, 0),
          "2-1 is playable with %d stars, below the %d it needs",
          br_save_total_stars(&save), br_save_world_requirement(1));

    /* The original's totals, including world 16 costing nothing. */
    CHECK(br_save_world_requirement(0) == 0, "world 1 asks for stars");
    CHECK(br_save_world_requirement(1) == 12, "world 2 wants %d stars, not 12",
          br_save_world_requirement(1));
    CHECK(br_save_world_requirement(11) == 228, "world 12 wants %d, not 228",
          br_save_world_requirement(11));
    /* The original's table has no entry for world 16, which left Halloween
     * free. It sits among the seasonal worlds, so it costs what they cost. */
    CHECK(br_save_world_requirement(15) == 66,
          "world 16 wants %d stars, not the 66 the seasonal worlds want",
          br_save_world_requirement(15));

    /* Earning enough stars opens the world. */
    for (l = 0; l < per_world; l++)
        br_save_record(&save, 0, l, 3, 10.0f);
    CHECK(br_save_total_stars(&save) == per_world * 3, "star total is wrong");
    CHECK(br_save_world_unlocked(&save, 1),
          "world 2 is still shut with %d stars", br_save_total_stars(&save));

    /* Unlocking everything lifts both gates and survives a reload. */
    br_save_unlock_everything(&save);
    for (w = 0; w < game->pack.world_count; w++)
        CHECK(br_save_level_unlocked(&save, w, per_world - 1),
              "world %d's last level is still shut after unlocking everything",
              w + 1);
    br_save_flush(&save);
    {
        br_save reloaded;
        br_save_init(&reloaded, game->pack.world_count, per_world);
        br_save_load(&reloaded);
        CHECK(br_save_level_unlocked(&reloaded, 18, 7),
              "unlocking everything did not survive a reload");
        br_save_free(&reloaded);
    }

    /* Reset clears progress but leaves preferences alone. */
    save.sound_on = 0;
    save.bike_type = 5;
    br_save_reset_progress(&save);
    CHECK(br_save_total_stars(&save) == 0, "reset left %d stars",
          br_save_total_stars(&save));
    CHECK(br_save_level_unlocked(&save, 0, 0), "reset closed 1-1");
    CHECK(!br_save_level_unlocked(&save, 0, 1), "reset left 1-2 open");
    CHECK(!br_save_world_unlocked(&save, 1), "reset left world 2 open");
    CHECK(save.sound_on == 0, "reset changed the sound setting");
    CHECK(save.bike_type == 5, "reset changed the chosen bike");

    br_save_free(&save);
    remove("assets_out/save.bin");
}

static void finish_current_level(br_app *app)
{
    br_input in;
    int i;

    memset(&in, 0, sizeof(in));
    app->game.state = BR_STATE_RUNNING;
    br_app_update(app, &in, 1.0f / 60.0f);   /* unlatch the throttle */
    in.accelerate = 1;

    for (i = 0; i < 90 * 60 && app->screen == BR_APP_RACING; i++) {
        float want = br_bike_angle_deg(&app->game.bike) / 45.0f;
        in.lean = want > 1.0f ? 1.0f : (want < -1.0f ? -1.0f : want);
        br_app_update(app, &in, 1.0f / 60.0f);
    }
}

static void test_gating_is_enforced(void)
{
    br_app app;
    br_input in;

    begin("gating is enforced, not just drawn");

    remove("assets_out/save.bin");
    CHECK(br_app_init(&app) == 0, "app init failed");

    /* Pressing Cross on a locked tile must not start it. Every unit test of
     * the rules passed while this path ignored them entirely. */
    br_menu_open_levels(&app.menu, 0);
    app.menu.level = 5;
    app.screen = BR_APP_MENU;
    memset(&in, 0, sizeof(in));
    in.confirm_pressed = 1;
    br_app_update(&app, &in, 1.0f / 60.0f);
    CHECK(app.screen == BR_APP_MENU,
          "a locked level started when Cross was pressed on it");

    /* A locked world must not open either. */
    br_menu_open_worlds(&app.menu);
    app.menu.world = 4;
    br_app_update(&app, &in, 1.0f / 60.0f);
    CHECK(app.menu.screen == BR_MENU_WORLDS,
          "a locked world opened its level list");

    /* Finishing 1-1 has to open 1-2 -- through the app, not by calling the
     * unlock function directly. */
    br_menu_open_levels(&app.menu, 0);
    app.menu.level = 0;
    memset(&in, 0, sizeof(in));
    in.confirm_pressed = 1;
    br_app_update(&app, &in, 1.0f / 60.0f);
    CHECK(app.screen == BR_APP_RACING, "1-1 did not start");

    finish_current_level(&app);
    CHECK(app.game.state == BR_STATE_FINISHED,
          "the run did not finish; cannot test what finishing unlocks");
    CHECK(br_save_level_unlocked(&app.save, 0, 1),
          "finishing 1-1 did not open 1-2");

    /* Earning a world opens its first level without finishing anything. */
    {
        int w, l;
        for (w = 0; w < 2; w++)
            for (l = 0; l < app.game.pack.worlds[w].level_count; l++)
                br_save_record(&app.save, w, l, 3, 10.0f);
        CHECK(br_save_world_unlocked(&app.save, 2),
              "world 3 shut with %d stars", br_save_total_stars(&app.save));
        CHECK(br_save_level_unlocked(&app.save, 2, 0),
              "world 3 is open but its first level is not");
        CHECK(!br_save_level_unlocked(&app.save, 2, 1),
              "opening world 3 opened more than its first level");
    }

    /* World 16 is gated like the other seasonal worlds. */
    CHECK(br_save_world_requirement(15) == 66,
          "world 16 wants %d stars, not 66", br_save_world_requirement(15));

    br_app_free(&app);
    remove("assets_out/save.bin");
}

static void test_ghost(br_game *game)
{
    br_ghost_recorder rec;
    br_ghost_store *store;
    br_ghost run;
    vec2 head, pos;
    float angle, wheel;
    int i;

    begin("ghost recording and playback");

    /* Record a straight line at a known speed. */
    br_ghost_record_begin(&rec);
    for (i = 0; i < 120; i++) {
        float t = (float)i / 60.0f;
        v2_set(&head, t * 2.0f, 1.0f);
        br_ghost_record_tick(&rec, t, &head, t * 10.0f);
    }
    CHECK(rec.count == 40, "two seconds at %d Hz gave %d samples, want 40",
          BR_GHOST_HZ, rec.count);
    CHECK(!rec.overflowed, "two seconds overflowed the recorder");

    run.samples = rec.samples;
    run.count = rec.count;
    run.bike = BR_BIKE_ULTRA;
    run.time = 2.0f;

    /* Playback lands between samples rather than snapping to them. */
    CHECK(br_ghost_pose_at(&run, 0.5f, &pos, &angle, &wheel),
          "playback ended early");
    CHECK(fabsf(pos.x - 1.0f) < 0.05f, "at 0.5s the ghost is at x=%.3f, want 1.0",
          pos.x);
    CHECK(fabsf(angle - 5.0f) < 0.4f, "at 0.5s the angle is %.2f, want 5.0", angle);
    CHECK(!br_ghost_pose_at(&run, 5.0f, &pos, &angle, &wheel),
          "playback past the end did not report finished");

    /* The wheels roll with the distance travelled, since the run never
     * recorded a wheel angle of its own. */
    {
        vec2 early, late;
        float a_early, a_late, w_early, w_late;

        br_ghost_pose_at(&run, 0.1f, &early, &a_early, &w_early);
        br_ghost_pose_at(&run, 1.5f, &late, &a_late, &w_late);
        CHECK(late.x > early.x, "the test run does not move");
        CHECK(w_late < w_early - 90.0f,
              "the wheels turned %.1f degrees over 1.4s of travel; they should "
              "roll with the distance", w_early - w_late);
    }

    /* A ghost that never moves has still wheels. */
    {
        br_ghost_recorder still;
        br_ghost parked;
        vec2 p;
        float a, w0, w1;

        br_ghost_record_begin(&still);
        v2_set(&head, 3.0f, 1.0f);
        for (i = 0; i < 60; i++)
            br_ghost_record_tick(&still, (float)i / 60.0f, &head, 0.0f);
        parked.samples = still.samples;
        parked.count = still.count;
        parked.bike = BR_BIKE_REGULAR;
        parked.time = 1.0f;

        br_ghost_pose_at(&parked, 0.1f, &p, &a, &w0);
        br_ghost_pose_at(&parked, 0.8f, &p, &a, &w1);
        CHECK(fabsf(w1 - w0) < 0.01f,
              "a parked ghost's wheels turned %.2f degrees", w1 - w0);
    }

    /* A run too long to hold leaves the recorder marked rather than truncating
     * silently. */
    br_ghost_record_begin(&rec);
    for (i = 0; i < BR_GHOST_MAX_SAMPLES + 200; i++)
        br_ghost_record_tick(&rec, (float)i / (float)BR_GHOST_HZ, &head, 0.0f);
    CHECK(rec.overflowed, "a run past the sample limit was not flagged");
    CHECK(rec.count == BR_GHOST_MAX_SAMPLES, "the recorder wrote %d samples, "
          "past its %d limit", rec.count, BR_GHOST_MAX_SAMPLES);

    /* Store: a better run replaces, a worse one does not, and it survives a
     * round trip through the file. */
    remove("assets_out/ghosts.bin");
    store = br_ghost_store_open(game->pack.world_count,
                                game->pack.worlds[0].level_count);
    CHECK(store != NULL, "could not open the ghost store");
    CHECK(br_ghost_get(store, 0, 0) == NULL, "a fresh store already has a ghost");

    br_ghost_put(store, 0, 0, &run);
    CHECK(br_ghost_get(store, 0, 0) != NULL, "the run was not kept");
    CHECK(br_ghost_get(store, 0, 0)->time == 2.0f, "the wrong time was kept");

    run.time = 3.0f;
    br_ghost_put(store, 0, 0, &run);
    CHECK(br_ghost_get(store, 0, 0)->time == 2.0f,
          "a slower run replaced the best one");

    run.time = 1.5f;
    br_ghost_put(store, 0, 0, &run);
    CHECK(br_ghost_get(store, 0, 0)->time == 1.5f, "a faster run was not kept");

    br_ghost_store_flush(store);
    br_ghost_store_close(store);

    store = br_ghost_store_open(game->pack.world_count,
                                game->pack.worlds[0].level_count);
    {
        const br_ghost *loaded = br_ghost_get(store, 0, 0);
        CHECK(loaded != NULL, "the ghost did not survive a reload");
        if (loaded) {
            CHECK(loaded->count == 40, "reloaded %d samples, want 40", loaded->count);
            CHECK(loaded->bike == BR_BIKE_ULTRA, "the recorded bike was lost");
            CHECK(fabsf(loaded->time - 1.5f) < 0.001f, "the recorded time was lost");
            CHECK(fabsf(loaded->samples[20].pos.x - run.samples[20].pos.x) < 0.001f,
                  "the sample positions did not survive");
        }
    }
    br_ghost_store_close(store);
    remove("assets_out/ghosts.bin");
}

static void test_ghost_shows_after_first_finish(void)
{
    br_app app;
    br_input in;

    begin("a ghost shows the first time it exists");

    remove("assets_out/save.bin");
    remove("assets_out/ghosts.bin");
    CHECK(br_app_init(&app) == 0, "app init failed");

    /* Play 1-1 with nothing recorded: no ghost, and nothing hidden either. */
    br_menu_open_levels(&app.menu, 0);
    app.menu.level = 0;
    app.screen = BR_APP_MENU;
    memset(&in, 0, sizeof(in));
    in.confirm_pressed = 1;
    br_app_update(&app, &in, 1.0f / 60.0f);
    CHECK(app.screen == BR_APP_RACING, "1-1 did not start");
    CHECK(app.game.ghost == NULL, "a fresh level already has a ghost");
    CHECK(!app.game.ghost_hidden, "nothing was hidden, yet the flag is set");

    finish_current_level(&app);
    CHECK(app.game.state == BR_STATE_FINISHED, "the run did not finish");

    /* The run just set becomes the ghost, and it must be on: it was never
     * switched off, it simply did not exist before. */
    CHECK(app.game.ghost != NULL, "finishing did not record a ghost");
    CHECK(!app.game.ghost_hidden,
          "the new ghost came up hidden -- 'no ghost yet' was mistaken for "
          "'the player turned it off'");

    /* Repeating keeps it on. */
    memset(&in, 0, sizeof(in));
    in.back_pressed = 1;                    /* Circle repeats from the panel */
    br_app_update(&app, &in, 1.0f / 60.0f);
    CHECK(app.screen == BR_APP_RACING, "repeat did not restart the level");
    CHECK(app.game.ghost != NULL && !app.game.ghost_hidden,
          "the ghost is off after repeating a level you just beat");

    /* Switching it off in pause sticks across a restart of the same level. */
    app.game.ghost_hidden = 1;
    br_game_restart(&app.game);
    CHECK(app.game.ghost_hidden,
          "restarting turned the ghost back on after it was switched off");

    br_app_free(&app);
    remove("assets_out/save.bin");
    remove("assets_out/ghosts.bin");
}

static void test_menu_navigation(br_game *game)
{
    br_menu menu;
    br_input in;
    br_save save;
    int i;

    begin("menu navigation");

    br_save_init(&save, game->pack.world_count, game->pack.worlds[0].level_count);
    br_save_unlock_everything(&save);
    memset(&menu, 0, sizeof(menu));
    memset(&in, 0, sizeof(in));
    br_menu_open_worlds(&menu);

    /* The grid runs one cell past the last world: that cell is the bike.
     * Right from it wraps back to the first world. */
    menu.world = game->pack.world_count;
    in.nav_x = 1;
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack, &save);
    CHECK(menu.world == 0, "right from the bike cell went to %d, want 0", menu.world);

    /* Held direction must repeat rather than run away. */
    menu.world = 0;
    for (i = 0; i < 10; i++)
        br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack, &save);
    CHECK(menu.world >= 0 && menu.world <= game->pack.world_count,
          "holding right left the selection at %d", menu.world);
    CHECK(menu.world <= 2, "holding right for 1/6 s moved %d places, too fast",
          menu.world);

    /* Down and up must stay inside the grid on every world. */
    in.nav_x = 0;
    for (i = 0; i <= game->pack.world_count; i++) {
        int dir;
        for (dir = -1; dir <= 1; dir += 2) {
            menu.world = i;
            menu.held_x = menu.held_y = 99;      /* force a fresh press */
            in.nav_y = dir;
            br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack, &save);
            CHECK(menu.world >= 0 && menu.world <= game->pack.world_count,
                  "moving %s from cell %d left the grid at %d",
                  dir < 0 ? "up" : "down", i, menu.world);
        }
    }

    /* Cross on a world opens its levels; Circle backs out again. */
    in.nav_y = 0;
    menu.held_x = menu.held_y = 0;
    br_menu_open_worlds(&menu);
    menu.world = 5;
    in.confirm_pressed = 1;
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack, &save);
    CHECK(menu.screen == BR_MENU_LEVELS, "Cross did not open the level list");
    CHECK(menu.world == 5, "opening levels changed the world to %d", menu.world);

    in.confirm_pressed = 0;
    in.back_pressed = 1;
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack, &save);
    CHECK(menu.screen == BR_MENU_WORLDS, "Circle did not return to the world list");

    br_save_free(&save);
}

/* Puts a touch at the centre of a tile. */
static void touch_tile(br_input *in, const br_menu *menu, const br_level_pack *pack,
                       int index)
{
    float x, y, w, h;

    if (!br_menu_tile_rect(menu, pack, index, &x, &y, &w, &h)) {
        printf("  FAIL %s: no rect for tile %d\n", g_case, index);
        g_failures++;
        return;
    }
    in->touch_ui_x = x + w * 0.5f;
    in->touch_ui_y = y + h * 0.5f;
}

static void test_menu_touch(br_game *game)
{
    br_menu menu;
    br_input in;
    br_save save;
    float bx, by, bsize;

    begin("menu touch");

    br_save_init(&save, game->pack.world_count, game->pack.worlds[0].level_count);
    br_save_unlock_everything(&save);
    memset(&menu, 0, sizeof(menu));
    memset(&in, 0, sizeof(in));
    br_menu_open_worlds(&menu);

    /* Touching a tile selects it; lifting off the same tile opens it. */
    touch_tile(&in, &menu, &game->pack, 7);
    in.touch_active = 1;
    in.touch_began = 1;
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack, &save);
    CHECK(menu.world == 7, "touching world 8 selected %d", menu.world + 1);
    CHECK(menu.screen == BR_MENU_WORLDS, "touching down already opened the levels");

    in.touch_began = 0;
    in.touch_active = 0;
    in.touch_ended = 1;
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack, &save);
    CHECK(menu.screen == BR_MENU_LEVELS, "lifting off world 8 did not open it");
    CHECK(menu.world == 7, "opening changed the world to %d", menu.world + 1);

    /* Sliding off before lifting must not commit. */
    br_menu_open_worlds(&menu);
    memset(&in, 0, sizeof(in));
    touch_tile(&in, &menu, &game->pack, 2);
    in.touch_active = 1;
    in.touch_began = 1;
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack, &save);
    in.touch_began = 0;
    in.touch_active = 0;
    in.touch_ended = 1;
    touch_tile(&in, &menu, &game->pack, 9);       /* released somewhere else */
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack, &save);
    CHECK(menu.screen == BR_MENU_WORLDS,
          "releasing on a different tile still opened a world");

    /* A tap on empty space does nothing. */
    br_menu_open_worlds(&menu);
    memset(&in, 0, sizeof(in));
    in.touch_ui_x = 4.0f;
    in.touch_ui_y = 4.0f;
    in.touch_active = 1;
    in.touch_began = 1;
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack, &save);
    in.touch_began = 0;
    in.touch_active = 0;
    in.touch_ended = 1;
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack, &save);
    CHECK(menu.screen == BR_MENU_WORLDS, "a tap on empty space opened something");

    /* Tapping a level tile plays it. */
    br_menu_open_levels(&menu, 0);
    memset(&in, 0, sizeof(in));
    touch_tile(&in, &menu, &game->pack, 4);
    in.touch_active = 1;
    in.touch_began = 1;
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack, &save);
    CHECK(menu.level == 4, "touching level 5 selected %d", menu.level + 1);
    in.touch_began = 0;
    in.touch_active = 0;
    in.touch_ended = 1;
    CHECK(br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack, &save) == BR_MENU_PLAY,
          "lifting off a level tile did not start it");

    /* The world list has the same way out, and it leaves the menu entirely. */
    br_menu_open_worlds(&menu);
    memset(&in, 0, sizeof(in));
    br_menu_back_button_rect(&bx, &by, &bsize);
    in.touch_ui_x = bx + bsize * 0.5f;
    in.touch_ui_y = by + bsize * 0.5f;
    in.touch_active = 1;
    in.touch_began = 1;
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack, &save);
    in.touch_began = 0;
    in.touch_active = 0;
    in.touch_ended = 1;
    CHECK(br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack, &save) ==
          BR_MENU_BACK, "the world list's back button did not leave the menu");

    /* The back button returns to the world list. */
    br_menu_open_levels(&menu, 3);
    memset(&in, 0, sizeof(in));
    br_menu_back_button_rect(&bx, &by, &bsize);
    in.touch_ui_x = bx + bsize * 0.5f;
    in.touch_ui_y = by + bsize * 0.5f;
    in.touch_active = 1;
    in.touch_began = 1;
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack, &save);
    in.touch_began = 0;
    in.touch_active = 0;
    in.touch_ended = 1;
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack, &save);
    CHECK(menu.screen == BR_MENU_WORLDS, "the back button did not go back");

    br_save_free(&save);
}

void br_test_audio_reset(void);
const br_sound *br_test_audio_looping(void);
int br_test_audio_active(void);

/* Feeds the engine state machine for `seconds`, holding the given conditions. */
static void run_engine(br_game_audio *audio, int accelerating, float speed,
                       int grounded, float seconds)
{
    int i;
    for (i = 0; i < (int)(seconds * 60.0f); i++) {
        br_game_audio_tick(audio, 1.0f / 60.0f);
        br_game_audio_engine(audio, accelerating, speed, grounded, 1.0f / 60.0f);
    }
}

static void test_engine_notes(void)
{
    br_game_audio audio;
    float climb, drop;

    begin("engine note ladder");

    br_test_audio_reset();
    br_game_audio_init(&audio);
    CHECK(audio.ready, "no sound effects loaded from assets_out/sfx");
    if (!audio.ready) {
        br_game_audio_free(&audio);
        return;
    }

    climb = br_sound_seconds(&audio.sfx[BR_SFX_ENGINE_MEDIUM_HI]);
    drop  = br_sound_seconds(&audio.sfx[BR_SFX_ENGINE_HI_MEDIUM]);
    CHECK(climb > 0.05f && drop > 0.05f,
          "climb %.3fs and drop %.3fs samples look wrong", climb, drop);

    /* Coasting idles on the low note, looping. */
    run_engine(&audio, 0, 0.0f, 1, 0.2f);
    CHECK(audio.engine == BR_ENGINE_STOPPED, "coasting is state %d, want stopped",
          audio.engine);
    CHECK(br_test_audio_looping() == &audio.sfx[BR_SFX_ENGINE_LOW],
          "coasting is not looping the idle note");

    /* Opening the throttle at low speed goes to the slow note first. */
    run_engine(&audio, 1, 1.0f, 1, 0.05f);
    CHECK(audio.engine == BR_ENGINE_SLOW, "throttle from idle is state %d, want slow",
          audio.engine);

    /* Past 4.5 it climbs, and hands over to the fast note when the climb
     * sample is 95% played. */
    run_engine(&audio, 1, 6.0f, 1, 0.05f);
    CHECK(audio.engine == BR_ENGINE_ACCELERATING,
          "speeding up is state %d, want accelerating", audio.engine);
    run_engine(&audio, 1, 6.0f, 1, climb * 0.5f);
    CHECK(audio.engine == BR_ENGINE_ACCELERATING,
          "handed over to fast halfway through the climb sample");
    run_engine(&audio, 1, 6.0f, 1, climb);
    CHECK(audio.engine == BR_ENGINE_FAST, "after the climb it is state %d, want fast",
          audio.engine);
    CHECK(br_test_audio_looping() == &audio.sfx[BR_SFX_ENGINE_HI],
          "fast is not looping the high note");

    /* Slowing down on the ground drops back, then returns to slow. */
    run_engine(&audio, 1, 1.0f, 1, 0.05f);
    CHECK(audio.engine == BR_ENGINE_DECELERATING,
          "slowing down is state %d, want decelerating", audio.engine);
    run_engine(&audio, 1, 1.0f, 1, drop);
    CHECK(audio.engine == BR_ENGINE_SLOW, "after the drop it is state %d, want slow",
          audio.engine);

    /* Leaving the ground counts as opening the throttle. */
    run_engine(&audio, 1, 1.0f, 0, 0.05f);
    CHECK(audio.engine == BR_ENGINE_ACCELERATING,
          "going airborne is state %d, want accelerating", audio.engine);

    br_game_audio_free(&audio);
}

static void test_impact_sound(void)
{
    br_game_audio audio;
    int before;

    begin("landing sound");

    br_test_audio_reset();
    br_game_audio_init(&audio);
    if (!audio.ready) {
        br_game_audio_free(&audio);
        return;
    }

    /* A scrape along the ground is not a landing. */
    before = br_test_audio_active();
    br_game_audio_impact(&audio, 1000.0f, 1.0f);
    CHECK(br_test_audio_active() == before, "a grounded scrape played a landing");

    /* Nor is a soft touch after a jump. */
    br_game_audio_impact(&audio, 50.0f, 0.0f);
    CHECK(br_test_audio_active() == before, "a soft landing played a landing");

    /* A hard one after air time does sound, once. */
    br_game_audio_impact(&audio, 900.0f, 0.0f);
    CHECK(br_test_audio_active() == before + 1, "a hard landing did not sound");
    br_game_audio_impact(&audio, 900.0f, 0.0f);
    CHECK(br_test_audio_active() == before + 1,
          "a second landing sounded before the rate limit expired");

    /* And again once the sample has had time to finish. */
    br_game_audio_tick(&audio, br_sound_seconds(&audio.sfx[BR_SFX_FALL_IMPACT]) + 0.1f);
    br_audio_stop_all();
    br_game_audio_impact(&audio, 900.0f, 0.0f);
    CHECK(br_test_audio_active() == 1, "the rate limit never released");

    br_game_audio_free(&audio);
}

int br_test_audio_initialised(void);

static void test_throttle_lock(br_game *game)
{
    br_input in;
    int i;

    begin("a held throttle does not start the next run");

    memset(&in, 0, sizeof(in));
    br_game_load(game, 0, 0);
    game->state = BR_STATE_RUNNING;

    /* Cross held from the moment the level loads must do nothing: it is the
     * press that dismissed the menu, not a request to move. */
    in.accelerate = 1;
    for (i = 0; i < 60; i++)
        br_game_update(game, &in, 1.0f / 60.0f);
    CHECK(game->bike.head.pos.x < 0.05f,
          "a held Cross drove the bike to x=%.3f", game->bike.head.pos.x);

    /* Letting go and pressing again does move it. */
    in.accelerate = 0;
    br_game_update(game, &in, 1.0f / 60.0f);
    in.accelerate = 1;
    for (i = 0; i < 60; i++)
        br_game_update(game, &in, 1.0f / 60.0f);
    CHECK(game->bike.head.pos.x > 0.5f,
          "a fresh press only reached x=%.3f", game->bike.head.pos.x);

    /* And a restart re-arms the lock. */
    br_game_restart(game);
    game->state = BR_STATE_RUNNING;
    for (i = 0; i < 60; i++)
        br_game_update(game, &in, 1.0f / 60.0f);
    CHECK(game->bike.head.pos.x < 0.05f,
          "the throttle stayed open across a restart: x=%.3f",
          game->bike.head.pos.x);
}

static void test_bike_cell(br_game *game)
{
    br_menu menu;
    br_ui_art art;
    br_input in;
    br_save save;

    begin("the world grid's bike cell");

    br_save_init(&save, game->pack.world_count, game->pack.worlds[0].level_count);
    br_save_unlock_everything(&save);
    memset(&art, 0, sizeof(art));
    br_menu_init(&menu, &art);
    memset(&in, 0, sizeof(in));
    br_menu_open_worlds(&menu);

    /* The cell past the last world opens the bike list rather than a world. */
    menu.world = game->pack.world_count;
    in.confirm_pressed = 1;
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack, &save);
    CHECK(menu.screen == BR_MENU_BIKES, "the bike cell did not open the bike list");
    CHECK(menu.world < game->pack.world_count,
          "leaving the bike cell left world at %d, past the last world",
          menu.world);

    /* And it has a rectangle, so touch can reach it. */
    br_menu_open_worlds(&menu);
    {
        float x, y, w, h;
        CHECK(br_menu_tile_rect(&menu, &game->pack, game->pack.world_count,
                                &x, &y, &w, &h),
              "the bike cell has no rectangle to tap");
    }

    br_menu_free(&menu);
    br_save_free(&save);
}

static void test_settings_toggles(br_game *game)
{
    br_settings settings;
    br_ui_art art;
    br_input in;
    br_save save;

    begin("settings toggles");

    br_save_init(&save, game->pack.world_count, game->pack.worlds[0].level_count);
    memset(&art, 0, sizeof(art));
    br_settings_init(&settings, &art);
    br_settings_open(&settings, &save);
    memset(&in, 0, sizeof(in));

    in.confirm_pressed = 1;                 /* row 0 is Sound */
    br_settings_update(&settings, &in, 1.0f / 60.0f);
    CHECK(save.sound_on == 0, "choosing Sound did not turn it off");
    CHECK(save.dirty, "the change was not flagged for saving");

    br_settings_update(&settings, &in, 1.0f / 60.0f);
    CHECK(save.sound_on == 1, "choosing Sound again did not turn it back on");

    /* Circle closes. */
    in.confirm_pressed = 0;
    in.back_pressed = 1;
    CHECK(br_settings_update(&settings, &in, 1.0f / 60.0f) == BR_SETTINGS_CLOSE,
          "Circle did not close the settings");

    /* Reset and unlock both ask first, and default to the harmless answer. */
    memset(&in, 0, sizeof(in));
    in.confirm_pressed = 1;
    settings.list.selected = 4;                 /* Reset progress */
    br_settings_update(&settings, &in, 1.0f / 60.0f);
    CHECK(settings.confirming == BR_CONFIRM_RESET, "Reset did not ask first");
    CHECK(settings.confirm_list.selected == 0,
          "the confirm defaults to answer %d; it must default to 'no'",
          settings.confirm_list.selected);

    br_save_record(&save, 0, 0, 3, 9.0f);
    br_settings_update(&settings, &in, 1.0f / 60.0f);   /* takes 'no' */
    CHECK(settings.confirming == BR_CONFIRM_NONE, "answering did not close it");
    CHECK(br_save_total_stars(&save) == 3, "'no' erased the save anyway");

    settings.list.selected = 4;
    br_settings_update(&settings, &in, 1.0f / 60.0f);
    settings.confirm_list.selected = 1;                 /* now say yes */
    br_settings_update(&settings, &in, 1.0f / 60.0f);
    CHECK(br_save_total_stars(&save) == 0, "'yes' did not reset the progress");

    settings.list.selected = 3;                         /* Unlock everything */
    br_settings_update(&settings, &in, 1.0f / 60.0f);
    CHECK(settings.confirming == BR_CONFIRM_UNLOCK, "Unlock did not ask first");
    settings.confirm_list.selected = 1;
    br_settings_update(&settings, &in, 1.0f / 60.0f);
    CHECK(br_save_level_unlocked(&save, 18, 7),
          "'yes' did not unlock everything");

    br_save_free(&save);
    remove("assets_out/save.bin");
}

static void test_result_actions(void)
{
    br_result result;
    br_ui_art art;
    br_input in;

    begin("end of run options");

    memset(&art, 0, sizeof(art));
    br_result_init(&result, &art);

    /* Circle repeats, Start steps out to the levels. */
    br_result_open(&result, 1, 1, 3, 9.5f, 12.0f, 1);
    memset(&in, 0, sizeof(in));
    in.back_pressed = 1;
    CHECK(br_result_update(&result, &in, 1.0f / 60.0f) == BR_RESULT_REPEAT,
          "Circle did not repeat the level");

    memset(&in, 0, sizeof(in));
    in.pause_pressed = 1;
    CHECK(br_result_update(&result, &in, 1.0f / 60.0f) == BR_RESULT_MENU,
          "Start did not go back to the levels");

    /* Cross takes the first row: next level after a finish... */
    memset(&in, 0, sizeof(in));
    in.confirm_pressed = 1;
    result.bar.selected = 0;
    CHECK(br_result_update(&result, &in, 1.0f / 60.0f) == BR_RESULT_NEXT,
          "Cross did not move to the next level");

    /* ...and another go after a crash, since there is no next. */
    br_result_open(&result, 0, 0, 0, 4.0f, 0.0f, 0);
    CHECK(result.bar.count == 2,
          "the crash panel offers %d choices; 'try again' and 'repeat' are the "
          "same thing without a next level", result.bar.count);

    /* Finishing a world's last level with no stars for the next world must
     * not offer to carry on into it. */
    br_result_open(&result, 1, 0, 3, 9.0f, 0.0f, 1);
    CHECK(result.bar.count == 2,
          "a finish with no reachable next level still offers %d choices",
          result.bar.count);
    result.bar.selected = 0;
    CHECK(br_result_update(&result, &in, 1.0f / 60.0f) == BR_RESULT_REPEAT,
          "the first choice should be repeat when there is no next level");
    result.bar.selected = 0;
    CHECK(br_result_update(&result, &in, 1.0f / 60.0f) == BR_RESULT_REPEAT,
          "Cross after a crash did not retry");
    result.bar.selected = 1;
    CHECK(br_result_update(&result, &in, 1.0f / 60.0f) == BR_RESULT_MENU,
          "the second crash choice is not the level list");
}

static void test_start_screen(void)
{
    br_start start;
    br_ui_art art;
    br_input in;

    begin("start screen");

    memset(&art, 0, sizeof(art));
    br_start_init(&start, &art);
    br_start_open(&start);
    memset(&in, 0, sizeof(in));

    in.confirm_pressed = 1;
    CHECK(br_start_update(&start, &in, 1.0f / 60.0f) == BR_START_SINGLE_PLAYER,
          "the first row is not Single Player");

    start.list.selected = 1;
    CHECK(br_start_update(&start, &in, 1.0f / 60.0f) == BR_START_SETTINGS,
          "the second row is not Settings");

    start.list.selected = 2;
    CHECK(br_start_update(&start, &in, 1.0f / 60.0f) == BR_START_NOTHING,
          "Exit quit without asking first");
    CHECK(start.confirming_exit, "Exit did not ask first");
    CHECK(start.confirm_list.selected == 0,
          "the confirm defaults to answer %d; it must default to 'no'",
          start.confirm_list.selected);

    /* The default answer keeps the game open. */
    CHECK(br_start_update(&start, &in, 1.0f / 60.0f) == BR_START_NOTHING,
          "the default answer quit the game");
    CHECK(!start.confirming_exit, "answering 'no' did not close the confirm");

    /* Circle backs out of it too. */
    start.list.selected = 2;
    br_start_update(&start, &in, 1.0f / 60.0f);
    CHECK(start.confirming_exit, "Exit did not ask first");
    memset(&in, 0, sizeof(in));
    in.back_pressed = 1;
    CHECK(br_start_update(&start, &in, 1.0f / 60.0f) == BR_START_NOTHING,
          "Circle on the confirm quit the game");
    CHECK(!start.confirming_exit, "Circle did not back out of the confirm");

    /* And saying yes does quit. */
    memset(&in, 0, sizeof(in));
    in.confirm_pressed = 1;
    start.list.selected = 2;
    br_start_update(&start, &in, 1.0f / 60.0f);
    start.confirm_list.selected = 1;
    CHECK(br_start_update(&start, &in, 1.0f / 60.0f) == BR_START_EXIT,
          "'yes, quit' did not quit");
}

static void test_app_opens_audio(void)
{
    br_app app;

    begin("the app opens the audio device");

    br_test_audio_reset();
    CHECK(br_app_init(&app) == 0, "app init failed");
    CHECK(br_test_audio_initialised(),
          "br_audio_init was never called -- the Vita would be silent");
    CHECK(app.screen == BR_APP_START, "the app did not open on the start screen");
    br_app_free(&app);
}

/* A sound of `seconds` at 22050 Hz, filled so every frame is identifiable. */
static void make_tone(br_sound *sound, float seconds)
{
    int i;

    sound->sample_rate = 22050;
    sound->frame_count = (int)(seconds * 22050.0f);
    sound->samples = malloc((size_t)sound->frame_count * sizeof(short));
    for (i = 0; i < sound->frame_count; i++)
        sound->samples[i] = (short)(1000 + (i % 100));
}

static void test_mixer_long_sounds(void)
{
    const int rate = 48000;
    const int grain = 1024;
    short out[2048 * 2];
    br_sound tone;
    br_voice voice;
    int grains, played;

    begin("long sounds play to the end");

    /* Four seconds is past what a single 16.16 position can address, which is
     * what made the win sting and the menu music restart for ever. */
    make_tone(&tone, 4.0f);

    br_mixer_reset();
    voice = br_mixer_play(&tone, 1.0f, 0, rate);
    CHECK(voice != 0, "the mixer refused a four-second sound");

    /* It must finish. Render six seconds' worth and watch it stop. */
    played = 0;
    for (grains = 0; grains < (6 * rate) / grain; grains++) {
        if (!br_mixer_playing(voice))
            break;
        br_mixer_render(out, grain);
        played += grain;
    }
    CHECK(!br_mixer_playing(voice),
          "a four-second sound was still going after six seconds -- it cannot "
          "reach its own end");

    {
        float seconds = (float)played / (float)rate;
        CHECK(seconds > 3.5f && seconds < 4.5f,
              "the four-second sound ran for %.2fs", seconds);
        printf("     4.00s sound stopped after %.2fs\n", seconds);
    }

    /* Looping must wrap rather than stop, and keep producing sound. */
    br_mixer_reset();
    voice = br_mixer_play(&tone, 1.0f, 1, rate);
    for (grains = 0; grains < (10 * rate) / grain; grains++)
        br_mixer_render(out, grain);
    CHECK(br_mixer_playing(voice), "a looping sound stopped on its own");
    {
        int i, silent = 1;
        for (i = 0; i < grain * 2; i++)
            if (out[i] != 0)
                silent = 0;
        CHECK(!silent, "a looping sound went silent after ten seconds");
    }

    free(tone.samples);

    /* And a three-minute track survives past the old ceiling. */
    make_tone(&tone, 200.0f);
    br_mixer_reset();
    voice = br_mixer_play(&tone, 1.0f, 1, rate);
    for (grains = 0; grains < (30 * rate) / grain; grains++)
        br_mixer_render(out, grain);
    CHECK(br_mixer_playing(voice), "a long track stopped after thirty seconds");
    {
        /* Thirty seconds in it must be past the first three seconds, not
         * back at the beginning. */
        int i, matches_start = 1;
        for (i = 0; i < 64; i++)
            if (out[i * 2] != tone.samples[i])
                matches_start = 0;
        CHECK(!matches_start,
              "thirty seconds into a long track it is playing the opening "
              "again -- the position wrapped");
    }
    free(tone.samples);
    br_mixer_reset();
}

static void test_world_names(br_game *game)
{
    int i;

    begin("world names");

    for (i = 0; i < game->pack.world_count; i++) {
        const char *name = br_world_name(i);
        CHECK(name && name[0] && strcmp(name, "World") != 0,
              "world %d has no name", i + 1);
    }
    CHECK(strcmp(br_world_name(0), "Desert") == 0, "world 1 is '%s', want Desert",
          br_world_name(0));
    CHECK(strcmp(br_world_name(15), "Halloween") == 0,
          "world 16 is '%s', want Halloween", br_world_name(15));
    CHECK(strcmp(br_world_name(-1), "World") == 0, "out of range name not handled");
    CHECK(strcmp(br_world_name(999), "World") == 0, "out of range name not handled");
}


/* --------------------------------------------------- durability and sound */

/* The loader treats a file it cannot parse as a first run, so a write caught
 * half-done by a power cut used to erase everything. Flushes now build a .tmp
 * and swap it in, and a load falls back to the .tmp when the swap itself was
 * interrupted. */
static void test_save_survives_interrupted_write(br_game *game)
{
    br_save save;
    FILE *f;

    begin("a save survives an interrupted write");

    remove("assets_out/save.bin");
    remove("assets_out/save.tmp");

    br_save_init(&save, game->pack.world_count, game->pack.worlds[0].level_count);
    br_save_record(&save, 0, 0, 3, 11.0f);
    br_save_record(&save, 1, 2, 2, 22.0f);
    CHECK(br_save_flush(&save) == 0, "flush failed");
    br_save_free(&save);

    f = fopen("assets_out/save.tmp", "rb");
    CHECK(f == NULL, "the flush left its temporary behind");
    if (f)
        fclose(f);

    /* A crash between removing the old file and renaming the new one leaves
     * only the temporary. It is already whole, so it must still load. */
    CHECK(rename("assets_out/save.bin", "assets_out/save.tmp") == 0,
          "could not stage the interrupted write");

    br_save_init(&save, game->pack.world_count, game->pack.worlds[0].level_count);
    br_save_load(&save);
    CHECK(br_save_total_stars(&save) == 5,
          "an interrupted write lost the progress: %d stars survived, want 5",
          br_save_total_stars(&save));
    CHECK(br_save_level(&save, 0, 0)->best_time == 11.0f,
          "the best time did not survive an interrupted write");
    br_save_free(&save);

    /* A half-written temporary must not be mistaken for the real thing. */
    remove("assets_out/save.bin");
    f = fopen("assets_out/save.tmp", "wb");
    CHECK(f != NULL, "could not write a truncated save");
    if (f) {
        fwrite("BRSV", 1, 4, f);
        fclose(f);
    }
    br_save_init(&save, game->pack.world_count, game->pack.worlds[0].level_count);
    br_save_load(&save);
    CHECK(br_save_total_stars(&save) == 0,
          "a truncated file was read as progress");
    br_save_free(&save);

    remove("assets_out/save.bin");
    remove("assets_out/save.tmp");
}

/* A ghost is a best time made visible. Resetting the progress has to take the
 * recorded runs with it, or the old run keeps racing beside a save that no
 * longer remembers the time it set. */
static void test_reset_clears_ghosts(void)
{
    br_app app;
    br_input in;
    br_ghost_recorder rec;
    br_ghost run;
    vec2 head;
    int i;

    begin("resetting the progress clears the ghosts");

    remove("assets_out/save.bin");
    remove("assets_out/save.tmp");
    remove("assets_out/ghosts.bin");
    remove("assets_out/ghosts.tmp");

    CHECK(br_app_init(&app) == 0, "app init failed");

    br_ghost_record_begin(&rec);
    for (i = 0; i < 60; i++) {
        float t = (float)i / 60.0f;
        v2_set(&head, t * 2.0f, 1.0f);
        br_ghost_record_tick(&rec, t, &head, 0.0f);
    }
    run.samples = rec.samples;
    run.count = rec.count;
    run.bike = BR_BIKE_REGULAR;
    run.time = 1.0f;
    br_ghost_put(app.ghosts, 0, 0, &run);
    br_save_record(&app.save, 0, 0, 3, 1.0f);
    CHECK(br_ghost_get(app.ghosts, 0, 0) != NULL, "the test ghost was not kept");

    br_settings_open(&app.settings, &app.save);
    app.screen = BR_APP_SETTINGS;

    memset(&in, 0, sizeof(in));
    in.confirm_pressed = 1;
    app.settings.list.selected = 4;                 /* Reset progress */
    br_app_update(&app, &in, 1.0f / 60.0f);
    CHECK(app.settings.confirming == BR_CONFIRM_RESET, "Reset did not ask first");

    app.settings.confirm_list.selected = 1;         /* yes, erase it */
    br_app_update(&app, &in, 1.0f / 60.0f);

    CHECK(br_save_total_stars(&app.save) == 0, "the stars survived the reset");
    CHECK(br_ghost_get(app.ghosts, 0, 0) == NULL,
          "the ghost survived the reset -- a wiped best time still has a run "
          "racing beside it");
    CHECK(app.game.ghost == NULL, "the race still points at the cleared ghost");
    br_app_free(&app);

    /* And it is gone from the disk, not just from memory. */
    {
        br_ghost_store *store = br_ghost_store_open(19, 8);

        CHECK(store && br_ghost_get(store, 0, 0) == NULL,
              "the cleared ghost came back from ghosts.bin");
        br_ghost_store_close(store);
    }

    remove("assets_out/save.bin");
    remove("assets_out/ghosts.bin");
}

/* Settings is reachable from a start screen that can be driven by touch alone,
 * so it has to be leavable the same way. */
static void test_settings_touch_back(br_game *game)
{
    br_settings settings;
    br_ui_art art;
    br_input in;
    br_save save;
    float bx, by, bsize;

    begin("settings can be left by touch");

    br_save_init(&save, game->pack.world_count, game->pack.worlds[0].level_count);
    memset(&art, 0, sizeof(art));
    br_settings_init(&settings, &art);
    br_settings_open(&settings, &save);
    br_ui_back_button_rect(&bx, &by, &bsize);

    /* Down and up on the button closes the screen. */
    memset(&in, 0, sizeof(in));
    in.touch_ui_x = bx + bsize * 0.5f;
    in.touch_ui_y = by + bsize * 0.5f;
    in.touch_active = 1;
    in.touch_began = 1;
    CHECK(br_settings_update(&settings, &in, 1.0f / 60.0f) == BR_SETTINGS_NOTHING,
          "touching down on back already closed the settings");
    in.touch_began = 0;
    in.touch_active = 0;
    in.touch_ended = 1;
    CHECK(br_settings_update(&settings, &in, 1.0f / 60.0f) == BR_SETTINGS_CLOSE,
          "tapping back did not close the settings");

    /* Sliding off it first does not. */
    br_settings_open(&settings, &save);
    memset(&in, 0, sizeof(in));
    in.touch_ui_x = bx + bsize * 0.5f;
    in.touch_ui_y = by + bsize * 0.5f;
    in.touch_active = 1;
    in.touch_began = 1;
    br_settings_update(&settings, &in, 1.0f / 60.0f);
    in.touch_began = 0;
    in.touch_active = 0;
    in.touch_ended = 1;
    in.touch_ui_x = 480.0f;                         /* released elsewhere */
    in.touch_ui_y = 20.0f;
    CHECK(br_settings_update(&settings, &in, 1.0f / 60.0f) != BR_SETTINGS_CLOSE,
          "releasing away from back still closed the settings");

    /* From the confirm it cancels rather than closing, and erases nothing. */
    br_settings_open(&settings, &save);
    br_save_record(&save, 0, 0, 3, 9.0f);
    memset(&in, 0, sizeof(in));
    in.confirm_pressed = 1;
    settings.list.selected = 4;
    br_settings_update(&settings, &in, 1.0f / 60.0f);
    CHECK(settings.confirming == BR_CONFIRM_RESET, "Reset did not ask first");

    memset(&in, 0, sizeof(in));
    in.touch_ui_x = bx + bsize * 0.5f;
    in.touch_ui_y = by + bsize * 0.5f;
    in.touch_active = 1;
    in.touch_began = 1;
    br_settings_update(&settings, &in, 1.0f / 60.0f);
    in.touch_began = 0;
    in.touch_active = 0;
    in.touch_ended = 1;
    CHECK(br_settings_update(&settings, &in, 1.0f / 60.0f) == BR_SETTINGS_NOTHING,
          "backing out of the confirm closed the whole screen");
    CHECK(settings.confirming == BR_CONFIRM_NONE, "back did not cancel the confirm");
    CHECK(br_save_total_stars(&save) == 3, "backing out of the confirm erased the save");

    br_save_free(&save);
    remove("assets_out/save.bin");
}

/* A v2 file -- written by every build between the settings screen and gating --
 * used to be rejected outright by a version check that listed the versions it
 * read one by one, so the migration that exists for exactly those files never
 * ran on them. */
static void test_old_save_versions_load(br_game *game)
{
    br_save save;
    unsigned char header[36];
    FILE *f;
    int w, l;

    begin("an older save still loads");

    remove("assets_out/save.bin");
    remove("assets_out/save.tmp");

    /* Hand-build a v2 file: the v3 header shape, minus the unlock byte per
     * level, with three stars on 1-1 and on 1-2. */
    memset(header, 0, sizeof(header));
    memcpy(header, "BRSV", 4);
    header[4] = 2;                                  /* version 2 */
    header[8] = (unsigned char)game->pack.world_count;
    header[12] = (unsigned char)game->pack.worlds[0].level_count;
    header[28] = 1;                                 /* sound on */
    header[32] = 0;                                 /* music off */

    f = fopen("assets_out/save.bin", "wb");
    CHECK(f != NULL, "could not write a v2 save");
    if (!f)
        return;
    fwrite(header, 1, sizeof(header), f);
    for (w = 0; w < game->pack.world_count; w++) {
        for (l = 0; l < game->pack.worlds[0].level_count; l++) {
            unsigned char record[8];
            memset(record, 0, sizeof(record));
            if (w == 0 && l < 2)
                record[0] = 3;
            fwrite(record, 1, sizeof(record), f);
        }
    }
    fclose(f);

    br_save_init(&save, game->pack.world_count, game->pack.worlds[0].level_count);
    br_save_load(&save);
    CHECK(br_save_total_stars(&save) == 6,
          "a v2 save was thrown away: %d stars read, want 6",
          br_save_total_stars(&save));
    CHECK(save.music_on == 0, "the v2 music setting was lost");
    /* v2 has no unlock flags, so they come from what was finished. */
    CHECK(br_save_level_unlocked(&save, 0, 2),
          "1-3 did not open after migrating a v2 save that finished 1-2");
    br_save_free(&save);

    remove("assets_out/save.bin");
}

/* Runs are stored by slot index, so a file recorded against a differently
 * shaped pack would put every ghost on the wrong level. */
static void test_ghosts_reject_a_different_pack(br_game *game)
{
    br_ghost_store *store;
    br_ghost_recorder rec;
    br_ghost run;
    unsigned char header[16];
    vec2 head;
    FILE *f;
    int i, per_world = game->pack.worlds[0].level_count;

    begin("ghosts recorded against another pack are dropped");

    remove("assets_out/ghosts.bin");
    remove("assets_out/ghosts.tmp");

    br_ghost_record_begin(&rec);
    for (i = 0; i < 60; i++) {
        float t = (float)i / 60.0f;
        v2_set(&head, t * 2.0f, 1.0f);
        br_ghost_record_tick(&rec, t, &head, 0.0f);
    }
    run.samples = rec.samples;
    run.count = rec.count;
    run.bike = BR_BIKE_REGULAR;
    run.time = 1.0f;

    store = br_ghost_store_open(game->pack.world_count, per_world);
    br_ghost_put(store, 0, 0, &run);
    br_ghost_store_flush(store);
    br_ghost_store_close(store);

    /* It reads back against the pack it was written for. */
    store = br_ghost_store_open(game->pack.world_count, per_world);
    CHECK(br_ghost_get(store, 0, 0) != NULL, "the ghost did not survive a reload");
    br_ghost_store_close(store);

    /* Against a pack of another shape it is refused rather than misplaced. */
    store = br_ghost_store_open(game->pack.world_count, per_world + 1);
    CHECK(store && br_ghost_get(store, 0, 0) == NULL,
          "a ghost recorded for a %d-level world loaded into a %d-level one",
          per_world, per_world + 1);
    br_ghost_store_close(store);

    /* A v1 file has no shape recorded and still loads, since the pack has not
     * changed since it was written. */
    f = fopen("assets_out/ghosts.bin", "rb+");
    CHECK(f != NULL, "could not reopen the ghost file");
    if (f) {
        CHECK(fread(header, 1, sizeof(header), f) == sizeof(header),
              "could not read the ghost header");
        header[4] = 1;                      /* claim version 1 */
        memset(header + 12, 0, 4);          /* which has no shape field */
        fseek(f, 0, SEEK_SET);
        fwrite(header, 1, sizeof(header), f);
        fclose(f);
    }
    store = br_ghost_store_open(game->pack.world_count, per_world);
    CHECK(store && br_ghost_get(store, 0, 0) != NULL,
          "a v1 ghost file no longer loads");
    br_ghost_store_close(store);

    remove("assets_out/ghosts.bin");
}

/* A press the gate refuses shakes the tile. Silence alone reads as the menu
 * having missed the press rather than having refused it. */
static void test_locked_tile_shakes(br_game *game)
{
    br_menu menu;
    br_input in;
    br_save save;

    begin("a refused press shakes the tile");

    br_save_init(&save, game->pack.world_count, game->pack.worlds[0].level_count);
    memset(&menu, 0, sizeof(menu));
    br_menu_open_worlds(&menu);
    menu.world = 1;                     /* world 2 wants 12 stars */

    memset(&in, 0, sizeof(in));
    in.confirm_pressed = 1;
    CHECK(br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack, &save) ==
          BR_MENU_STAY, "a locked world opened");
    CHECK(menu.refused_time > 0.0f, "a refused world did not shake");
    CHECK(menu.refused_index == 1, "the wrong tile shook");

    /* It runs down on its own. */
    memset(&in, 0, sizeof(in));
    br_menu_update(&menu, &in, 0.5f, &game->pack, &save);
    CHECK(menu.refused_time <= 0.0f, "the shake did not stop");

    /* An accepted press does not shake. */
    menu.world = 0;
    memset(&in, 0, sizeof(in));
    in.confirm_pressed = 1;
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack, &save);
    CHECK(menu.screen == BR_MENU_LEVELS, "world 1 did not open");
    CHECK(menu.refused_time <= 0.0f, "an accepted press shook the tile");

    /* And a locked level shakes its own tile. */
    menu.level = 3;
    memset(&in, 0, sizeof(in));
    in.confirm_pressed = 1;
    CHECK(br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack, &save) ==
          BR_MENU_STAY, "a locked level started");
    CHECK(menu.refused_time > 0.0f && menu.refused_index == 3,
          "a refused level did not shake its own tile");

    br_save_free(&save);
}

int main(int argc, char **argv)
{
    br_game game;

    br_test_log_verbose = (argc > 1 && strcmp(argv[1], "-v") == 0);
    br_test_load_blobs();
    br_render_init();

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
    test_fonts();
    test_save(&game);
    test_ghost(&game);
    test_gating(&game);
    test_gating_is_enforced();
    test_ghost_shows_after_first_finish();
    test_menu_navigation(&game);
    test_menu_touch(&game);
    test_world_names(&game);
    test_throttle_lock(&game);
    test_bike_cell(&game);
    test_settings_toggles(&game);
    test_old_save_versions_load(&game);
    test_ghosts_reject_a_different_pack(&game);
    test_locked_tile_shakes(&game);
    test_settings_touch_back(&game);
    test_save_survives_interrupted_write(&game);
    test_result_actions();
    test_start_screen();
    test_mixer_long_sounds();
    test_engine_notes();
    test_impact_sound();
    test_bike_sprite_region();
    test_lean_direction(&game);
    test_reverse(&game);
    test_bike_drives(&game);
    test_finishes_first_level(&game);
    test_no_level_explodes(&game);

    br_game_free(&game);
    test_app_opens_audio();
    test_reset_clears_ghosts();

    printf("\n%s: %d failure(s)\n", g_failures ? "FAILED" : "OK", g_failures);
    return g_failures ? 1 : 0;
}
