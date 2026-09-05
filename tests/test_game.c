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
#include "../src/game/save.h"
#include "../src/ui/font.h"
#include "../src/ui/menu.h"
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

static void test_menu_navigation(br_game *game)
{
    br_menu menu;
    br_input in;
    int i;

    begin("menu navigation");

    memset(&menu, 0, sizeof(menu));
    memset(&in, 0, sizeof(in));
    br_menu_open_worlds(&menu);

    /* Right from the last world wraps to the first. */
    menu.world = game->pack.world_count - 1;
    in.nav_x = 1;
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack);
    CHECK(menu.world == 0, "right from the last world went to %d, want 0", menu.world);

    /* Held direction must repeat rather than run away. */
    menu.world = 0;
    for (i = 0; i < 10; i++)
        br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack);
    CHECK(menu.world >= 0 && menu.world < game->pack.world_count,
          "holding right left the selection at %d", menu.world);
    CHECK(menu.world <= 2, "holding right for 1/6 s moved %d places, too fast",
          menu.world);

    /* Down and up must stay inside the grid on every world. */
    in.nav_x = 0;
    for (i = 0; i < game->pack.world_count; i++) {
        int dir;
        for (dir = -1; dir <= 1; dir += 2) {
            menu.world = i;
            menu.held_x = menu.held_y = 99;      /* force a fresh press */
            in.nav_y = dir;
            br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack);
            CHECK(menu.world >= 0 && menu.world < game->pack.world_count,
                  "moving %s from world %d left the grid at %d",
                  dir < 0 ? "up" : "down", i + 1, menu.world);
        }
    }

    /* Cross on a world opens its levels; Circle backs out again. */
    in.nav_y = 0;
    menu.held_x = menu.held_y = 0;
    br_menu_open_worlds(&menu);
    menu.world = 5;
    in.confirm_pressed = 1;
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack);
    CHECK(menu.screen == BR_MENU_LEVELS, "Cross did not open the level list");
    CHECK(menu.world == 5, "opening levels changed the world to %d", menu.world);

    in.confirm_pressed = 0;
    in.back_pressed = 1;
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack);
    CHECK(menu.screen == BR_MENU_WORLDS, "Circle did not return to the world list");
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
    float bx, by, bsize;

    begin("menu touch");

    memset(&menu, 0, sizeof(menu));
    memset(&in, 0, sizeof(in));
    br_menu_open_worlds(&menu);

    /* Touching a tile selects it; lifting off the same tile opens it. */
    touch_tile(&in, &menu, &game->pack, 7);
    in.touch_active = 1;
    in.touch_began = 1;
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack);
    CHECK(menu.world == 7, "touching world 8 selected %d", menu.world + 1);
    CHECK(menu.screen == BR_MENU_WORLDS, "touching down already opened the levels");

    in.touch_began = 0;
    in.touch_active = 0;
    in.touch_ended = 1;
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack);
    CHECK(menu.screen == BR_MENU_LEVELS, "lifting off world 8 did not open it");
    CHECK(menu.world == 7, "opening changed the world to %d", menu.world + 1);

    /* Sliding off before lifting must not commit. */
    br_menu_open_worlds(&menu);
    memset(&in, 0, sizeof(in));
    touch_tile(&in, &menu, &game->pack, 2);
    in.touch_active = 1;
    in.touch_began = 1;
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack);
    in.touch_began = 0;
    in.touch_active = 0;
    in.touch_ended = 1;
    touch_tile(&in, &menu, &game->pack, 9);       /* released somewhere else */
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack);
    CHECK(menu.screen == BR_MENU_WORLDS,
          "releasing on a different tile still opened a world");

    /* A tap on empty space does nothing. */
    br_menu_open_worlds(&menu);
    memset(&in, 0, sizeof(in));
    in.touch_ui_x = 4.0f;
    in.touch_ui_y = 4.0f;
    in.touch_active = 1;
    in.touch_began = 1;
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack);
    in.touch_began = 0;
    in.touch_active = 0;
    in.touch_ended = 1;
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack);
    CHECK(menu.screen == BR_MENU_WORLDS, "a tap on empty space opened something");

    /* Tapping a level tile plays it. */
    br_menu_open_levels(&menu, 0);
    memset(&in, 0, sizeof(in));
    touch_tile(&in, &menu, &game->pack, 4);
    in.touch_active = 1;
    in.touch_began = 1;
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack);
    CHECK(menu.level == 4, "touching level 5 selected %d", menu.level + 1);
    in.touch_began = 0;
    in.touch_active = 0;
    in.touch_ended = 1;
    CHECK(br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack) == BR_MENU_PLAY,
          "lifting off a level tile did not start it");

    /* The back button returns to the world list. */
    br_menu_open_levels(&menu, 3);
    memset(&in, 0, sizeof(in));
    br_menu_back_button_rect(&bx, &by, &bsize);
    in.touch_ui_x = bx + bsize * 0.5f;
    in.touch_ui_y = by + bsize * 0.5f;
    in.touch_active = 1;
    in.touch_began = 1;
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack);
    in.touch_began = 0;
    in.touch_active = 0;
    in.touch_ended = 1;
    br_menu_update(&menu, &in, 1.0f / 60.0f, &game->pack);
    CHECK(menu.screen == BR_MENU_WORLDS, "the back button did not go back");
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
    test_menu_navigation(&game);
    test_menu_touch(&game);
    test_world_names(&game);
    test_engine_notes();
    test_impact_sound();
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
