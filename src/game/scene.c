#include "scene.h"

#include <math.h>
#include <stdio.h>
#include <string.h>

#include "../platform/fs.h"
#include "../platform/log.h"

/* Atlas regions, from TextureCoordinates.java. The three big ones are the
 * parallax layers; the rest are sprites packed down the right edge. */
#define UV_BACKGROUND_NEAR  0.001953125f, 0.31445312f,  0.8979492f,  0.6269531f
#define UV_BACKGROUND_MID   0.001953125f, 0.0009765625f, 0.8979492f, 0.31347656f
#define UV_SKY              0.0014648438f, 0.6279297f,   0.47021484f, 0.9404297f
#define UV_TRACK            0.001953125f, 0.94384766f,   0.008300781f, 0.99560547f
/* Stored bottom-edge-first, which flips the pole upright when drawn. */
#define UV_POLE             0.9868164f,   0.42529297f,   0.99853516f, 0.32763672f
#define UV_WHEEL            0.95654297f,  0.32763672f,   0.98583984f, 0.3569336f
#define UV_WHEEL_HALLOWEEN  0.95654297f,  0.35888672f,   0.98583984f, 0.3876953f
#define UV_WHEEL_ULTRA      0.95703125f,  0.39160156f,   0.98535156f, 0.41992188f

static const float s_flag_uv[8][4] = {
    { 0.9536133f, 0.08544922f,  0.9995117f, 0.114746094f },
    { 0.9536133f, 0.115722656f, 0.9995117f, 0.14501953f  },
    { 0.9536133f, 0.1459961f,   0.9995117f, 0.17529297f  },
    { 0.9536133f, 0.17626953f,  0.9995117f, 0.2055664f   },
    { 0.9536133f, 0.20654297f,  0.9995117f, 0.23583984f  },
    { 0.9536133f, 0.2368164f,   0.9995117f, 0.26611328f  },
    { 0.9536133f, 0.26708984f,  0.9995117f, 0.29638672f  },
    { 0.9536133f, 0.29736328f,  0.9995117f, 0.32666016f  },
};

/* Each world ships its art twice, at 2048 and at 1024. Both have the same UV
 * layout, and the original only reaches for the large set on a screen wider or
 * taller than 1024 -- which the Vita's 960x544 is not -- so the small one is
 * both the faithful choice and a quarter of the video memory. The large file
 * is the fallback in case only it was copied across. */
static const char *s_atlas_files[][2] = {
    { "bikerace_textura1b.png",            "bikerace_textura1.png"             }, /* desert,  worlds 1, 7  */
    { "bikerace_textura2b.png",            "bikerace_textura2.png"             }, /* arctic,  worlds 2, 8  */
    { "bikerace_textura3b.png",            "bikerace_textura3.png"             }, /* dunes,   worlds 3, 9  */
    { "bikerace_textura4b.png",            "bikerace_textura4.png"             }, /* hills,   worlds 4, 10 */
    { "bikerace_textura5b.png",            "bikerace_textura5.png"             }, /* beach,   worlds 5, 11 */
    { "bikerace_textura6b.png",            "bikerace_textura6.png"             }, /* savanna, worlds 6, 12 */
    { "bikerace_textura_easterb.png",      "bikerace_textura_easter.png"       }, /* world 19 */
    { "bikerace_textura_hallowenb.png",    "bikerace_textura_halloween.png"    }, /* world 16 (the typo is the shipped filename) */
    { "bikerace_textura_holiday1b.png",    "bikerace_textura_holiday1.png"     }, /* worlds 13, 14, 15, 18 */
    { "bikerace_textura_thanksgivingb.png","bikerace_textura_thanksgiving.png" }, /* world 17 */
};
#define ATLAS_COUNT ((int)(sizeof(s_atlas_files) / sizeof(s_atlas_files[0])))

#define TRACK_THICKNESS  0.05f
#define POLE_WIDTH       0.18f
#define FLAG_WIDTH       0.6666667f
#define WHEEL_WIDTH      0.2f
#define FLAG_FRAME_MS    55
#define RIDER_DROP      (-0.11f)
/* Divisors from GameSceneDirector: the further layer moves less. */
#define PARALLAX_MID     16.0f
#define PARALLAX_NEAR    6.0f
/* CyclicSpriteSceneNode's tiling constant. */
#define PARALLAX_TILING  2.8789062f

int br_scene_init(br_scene *scene)
{
    memset(scene, 0, sizeof(*scene));
    scene->atlas_index = -1;
    scene->bike_type = BR_BIKE_TYPE_COUNT;
    return 0;
}

void br_scene_free(br_scene *scene)
{
    if (scene->has_track)
        br_mesh_free(&scene->track_mesh);
    br_image_free(&scene->atlas);
    br_image_free(&scene->bike_image);
    br_image_free(&scene->wheel_image);
    memset(scene, 0, sizeof(*scene));
}

/* Most bikes take their wheel from the world atlas; Santa's is its own file. */
static void bind_wheel(br_scene *scene)
{
    const br_image *a = &scene->atlas;

    br_image_free(&scene->wheel_image);

    switch (br_bike_def_for(scene->bike_type)->wheel) {
    case BR_WHEEL_ULTRA:
        scene->wheel = br_texture_region(a, UV_WHEEL_ULTRA);
        break;
    case BR_WHEEL_HALLOWEEN:
        scene->wheel = br_texture_region(a, UV_WHEEL_HALLOWEEN);
        break;
    case BR_WHEEL_SANTA: {
        char path[256];
        snprintf(path, sizeof(path), "%s/textures/santa_wheel.png", br_asset_root());
        if (br_image_load(&scene->wheel_image, path) == 0) {
            scene->wheel = br_texture_region(&scene->wheel_image, 0.0f, 0.0f, 1.0f, 1.0f);
            break;
        }
        scene->wheel = br_texture_region(a, UV_WHEEL);
        break;
    }
    default:
        scene->wheel = br_texture_region(a, UV_WHEEL);
        break;
    }
}

static void bind_atlas_regions(br_scene *scene)
{
    const br_image *a = &scene->atlas;
    int i;

    scene->sky             = br_texture_region(a, UV_SKY);
    scene->background_mid  = br_texture_region(a, UV_BACKGROUND_MID);
    scene->background_near = br_texture_region(a, UV_BACKGROUND_NEAR);
    scene->track           = br_texture_region(a, UV_TRACK);
    scene->pole            = br_texture_region(a, UV_POLE);

    bind_wheel(scene);

    for (i = 0; i < 8; i++)
        scene->flag[i] = br_texture_region(a, s_flag_uv[i][0], s_flag_uv[i][1],
                                              s_flag_uv[i][2], s_flag_uv[i][3]);
}

int br_scene_use(br_scene *scene, int atlas_index, br_bike_type bike)
{
    char path[256];

    if (atlas_index < 0 || atlas_index >= ATLAS_COUNT) {
        LOGE("scene: atlas index %d out of range", atlas_index);
        return -1;
    }

    if (atlas_index != scene->atlas_index) {
        int variant;

        br_image_free(&scene->atlas);
        for (variant = 0; variant < 2; variant++) {
            snprintf(path, sizeof(path), "%s/textures/%s",
                     br_asset_root(), s_atlas_files[atlas_index][variant]);
            if (br_image_load(&scene->atlas, path) == 0)
                break;
        }
        if (variant == 2) {
            LOGE("scene: no atlas for index %d -- copy the textures to %s/textures",
                 atlas_index, br_asset_root());
            return -1;
        }
        scene->atlas_index = atlas_index;
    }

    if (bike != scene->bike_type) {
        br_image_free(&scene->bike_image);
        snprintf(path, sizeof(path), "%s/textures/%s",
                 br_asset_root(), br_bike_def_for(bike)->sprite);
        if (br_image_load(&scene->bike_image, path) < 0)
            return -1;
        scene->bike_type = bike;
        {
            const float *uv = br_bike_def_for(bike)->sprite_uv;
            scene->bike_sprite = br_texture_region(&scene->bike_image,
                                                   uv[0], uv[1], uv[2], uv[3]);
        }
    }

    bind_atlas_regions(scene);
    return 0;
}

int br_scene_set_level(br_scene *scene, const br_level *level)
{
    if (scene->has_track) {
        br_mesh_free(&scene->track_mesh);
        scene->has_track = 0;
    }
    if (br_track_mesh_build(&scene->track_mesh, &level->boards,
                            &scene->track, TRACK_THICKNESS) < 0)
        return -1;
    scene->has_track = 1;
    LOGI("scene: track mesh built, %d runs from %d boards",
         scene->track_mesh.count, level->boards.count);
    return 0;
}

/* One scrolling parallax layer. `divisor` sets how far behind the camera it
 * sits: larger means slower and smaller. */
static void draw_parallax(const br_scene *scene, const br_texture *tex,
                          float divisor, const br_camera *cam, float level_width)
{
    float depth = ((cam->scale + divisor) - 1.0f) / divisor;
    float tile_h = ((br_texture_h(tex) * 2.0f) / br_texture_w(tex)) * PARALLAX_TILING;
    float tile_w = tile_h * PARALLAX_TILING;
    float floor_y = (cam->scale + cam->limits.min.y) - 0.2f;
    float anchor_y = cam->pos.y < floor_y ? cam->pos.y : floor_y;
    int tiles = (int)ceilf(level_width / tile_w);
    int i;

    br_push();
    /* CyclicSpriteSceneNode scales before it translates, so the offset below is
     * in the scaled frame. */
    br_scale(1.0f / depth, 1.0f / depth);
    br_translate(-cam->pos.x / divisor,
                 (anchor_y / divisor) + (-depth) - (cam->pos.y / divisor));

    for (i = 0; i < tiles; i++) {
        float left = -level_width * 0.5f + (float)i * tile_w;
        br_draw_rect(left, tile_h, left + tile_w, 0.0f, tex, NULL);
    }
    br_pop();
}

static void draw_backgrounds(const br_scene *scene, const br_level *level,
                             const br_camera *cam)
{
    float aspect = br_render_aspect();
    float level_width = br_aabb_width(&level->boards.bounds);

    /* The sky does not move: it just fills the screen. */
    br_push();
    br_draw_sprite(&scene->sky, aspect * 2.0f, 2.0f, NULL);
    br_pop();

    draw_parallax(scene, &scene->background_mid, PARALLAX_MID, cam, level_width);
    draw_parallax(scene, &scene->background_near, PARALLAX_NEAR, cam, level_width);
}

static void draw_finish(const br_scene *scene, const br_level *level, unsigned time_ms)
{
    float pole_h = br_texture_fit_h(&scene->pole, POLE_WIDTH);
    float flag_h = br_texture_fit_h(&scene->flag[0], FLAG_WIDTH);
    const br_texture *frame = &scene->flag[(time_ms / FLAG_FRAME_MS) % 8];

    br_push();
    br_translate(level->finish.x, level->finish.y - pole_h * 0.5f);
    br_draw_sprite(&scene->pole, POLE_WIDTH, pole_h, NULL);

    /* The flag hangs off the top of the pole, and is parented to it. */
    br_translate(FLAG_WIDTH * 0.5f + 0.018f, -flag_h * 0.5f + 0.6f);
    br_draw_sprite(frame, FLAG_WIDTH, flag_h, NULL);
    br_pop();
}

static void draw_bike(const br_scene *scene, const br_bike *bike)
{
    const br_bike_def *def = bike->def;
    float wheel_h = br_texture_fit_h(&scene->wheel, WHEEL_WIDTH);
    float body_w  = def->sprite_width;
    float body_h  = br_texture_fit_h(&scene->bike_sprite, body_w);
    float angle;
    vec2 offset;

    br_push();
    br_translate(bike->front.pos.x, bike->front.pos.y);
    br_rotate_deg(bike->front.angle_deg);
    br_draw_sprite(&scene->wheel, WHEEL_WIDTH, wheel_h, NULL);
    br_pop();

    br_push();
    br_translate(bike->rear.pos.x, bike->rear.pos.y);
    br_rotate_deg(bike->rear.angle_deg);
    br_draw_sprite(&scene->wheel, WHEEL_WIDTH, wheel_h, NULL);
    br_pop();

    if (br_bike_crashed(bike))
        return;

    /* The rider sits below and behind the head body, rotated with the bike. */
    angle = br_bike_angle_deg(bike);
    v2_set(&offset, 0.0f, RIDER_DROP);
    v2_add(&offset, &def->sprite_offset);
    v2_rotate(&offset, (angle / 180.0f) * 3.1415927f);
    v2_add(&offset, &bike->head.pos);

    br_push();
    br_translate(offset.x, offset.y);
    br_rotate_deg(angle);
    br_draw_sprite(&scene->bike_sprite, body_w, body_h, NULL);
    br_pop();
}

void br_scene_draw(br_scene *scene, const br_level *level,
                   const br_camera *cam, const br_bike *bike, unsigned time_ms)
{
    br_identity();

    /* Squeeze x by the aspect ratio so one world unit is square on screen;
     * everything below is drawn inside this frame. */
    br_scale(1.0f / br_render_aspect(), 1.0f);
    draw_backgrounds(scene, level, cam);

    br_scale(1.0f / cam->scale, 1.0f / cam->scale);
    br_translate(-cam->pos.x, -cam->pos.y);

    if (scene->has_track)
        br_draw_mesh(&scene->track_mesh);
    draw_finish(scene, level, time_ms);
    draw_bike(scene, bike);
}
