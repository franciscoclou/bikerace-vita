/* Renders frames on the host and writes them as PNGs, so layout, transforms
 * and draw order can be checked without a Vita.
 *
 *   make -C tests shots
 */

#include <png.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "../src/app.h"
#include "../src/engine/render.h"
#include "stub/vitaGL.h"

void br_test_load_blobs(void);
extern int br_test_log_verbose;

static int write_png(const char *path, const unsigned char *rgba, int w, int h)
{
    FILE *f = fopen(path, "wb");
    png_structp png;
    png_infop info;
    int y;

    if (!f) return -1;
    png = png_create_write_struct(PNG_LIBPNG_VER_STRING, NULL, NULL, NULL);
    info = png_create_info_struct(png);
    png_init_io(png, f);
    png_set_IHDR(png, info, (png_uint_32)w, (png_uint_32)h, 8, PNG_COLOR_TYPE_RGBA,
                 PNG_INTERLACE_NONE, PNG_COMPRESSION_TYPE_DEFAULT, PNG_FILTER_TYPE_DEFAULT);
    png_write_info(png, info);
    for (y = 0; y < h; y++)
        png_write_row(png, (png_bytep)(rgba + (size_t)y * w * 4));
    png_write_end(png, NULL);
    png_destroy_write_struct(&png, &info);
    fclose(f);
    return 0;
}

static void shoot(br_app *app, const char *dir, const char *name, unsigned time_ms)
{
    const unsigned char *fb;
    char path[256];
    int w, h;

    br_app_draw(app, time_ms);
    fb = br_test_fb(&w, &h);
    snprintf(path, sizeof(path), "%s/%s.png", dir, name);
    if (write_png(path, fb, w, h) == 0)
        printf("  wrote %s\n", path);
    else
        printf("  FAILED to write %s\n", path);
}

/* Advance the race with a rider that keeps the bike level. */
static void race_for(br_app *app, float seconds)
{
    br_input in;
    int i;

    memset(&in, 0, sizeof(in));
    in.accelerate = 1;
    app->game.state = BR_STATE_RUNNING;

    for (i = 0; i < (int)(seconds * 60.0f); i++) {
        float want = br_bike_angle_deg(&app->game.bike) / 45.0f;
        in.lean = want > 1.0f ? 1.0f : (want < -1.0f ? -1.0f : want);
        br_app_update(app, &in, 1.0f / 60.0f);
    }
}

static void race(br_app *app, const char *dir, const char *name,
                 int world, int level, float seconds)
{
    app->menu.world = world;
    app->menu.level = level;
    app->screen = BR_APP_MENU;
    app->menu.screen = BR_MENU_LEVELS;
    {
        br_input in;
        memset(&in, 0, sizeof(in));
        in.confirm_pressed = 1;
        br_app_update(app, &in, 1.0f / 60.0f);   /* picks the level, starts it */
    }
    if (seconds > 0.0f)
        race_for(app, seconds);
    app->screen = BR_APP_RACING;
    shoot(app, dir, name, (unsigned)(seconds * 1000.0f));
}

int main(int argc, char **argv)
{
    const char *dir = argc > 1 ? argv[1] : "shots";
    br_app app;

    br_test_log_verbose = 0;
    br_test_load_blobs();
    br_render_init();

    if (br_app_init(&app) < 0) {
        printf("app init failed\n");
        return 1;
    }

    app.screen = BR_APP_START;
    shoot(&app, dir, "start", 0);

    br_settings_open(&app.settings, &app.save.sound_on, &app.save.music_on,
                     &app.save.dirty);
    app.screen = BR_APP_SETTINGS;
    shoot(&app, dir, "settings", 0);
    app.settings.showing_controls = 1;
    shoot(&app, dir, "settings_controls", 0);
    app.settings.showing_controls = 0;

    app.screen = BR_APP_MENU;
    br_menu_open_worlds(&app.menu);
    app.menu.world = 0;
    shoot(&app, dir, "menu_worlds", 0);

    app.menu.world = app.game.pack.world_count;   /* the bike cell */
    shoot(&app, dir, "menu_worlds_bike_cell", 0);
    app.menu.world = 0;

    app.menu.world = 15;                    /* Halloween, to show the grid wrap */
    shoot(&app, dir, "menu_worlds_late", 0);

    br_menu_open_levels(&app.menu, 0);
    app.menu.level = 0;
    shoot(&app, dir, "menu_levels_w01", 0);

    br_menu_open_levels(&app.menu, 14);     /* Special: the biggest tracks */
    app.menu.level = 2;
    shoot(&app, dir, "menu_levels_w15", 0);

    br_menu_open_bikes(&app.menu);
    app.menu.bike = BR_BIKE_ULTRA;
    shoot(&app, dir, "menu_bikes", 0);

    race(&app, dir, "race_w01_l1_start", 0, 0, 0.0f);
    race(&app, dir, "race_w01_l1", 0, 0, 2.0f);
    race(&app, dir, "race_w16_l1", 15, 0, 3.0f);

    /* Pause and the end-of-run panel sit on top of the frozen race. */
    race(&app, dir, "race_w01_l3", 0, 2, 2.5f);
    br_pause_open(&app.pause);
    app.screen = BR_APP_PAUSED;
    shoot(&app, dir, "pause", 2500);

    br_result_open(&app.result, 1, 3, 9.42f, 11.80f, 1);
    app.screen = BR_APP_RESULT;
    shoot(&app, dir, "result_complete", 2500);

    br_result_open(&app.result, 0, 0, 4.10f, 0.0f, 0);
    shoot(&app, dir, "result_crashed", 2500);

    br_app_free(&app);
    return 0;
}
