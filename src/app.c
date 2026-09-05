#include "app.h"

#include <stdio.h>
#include <string.h>

#include "engine/render.h"
#include "platform/log.h"

/* Linked in by src/game/blobs.S on the Vita; the host tests hand these over as
 * pointers to files they read instead. */
#ifdef BR_HOST_TEST
extern const unsigned char *br_font_display_start;
extern const unsigned char *br_font_display_end;
extern const unsigned char *br_font_body_start;
extern const unsigned char *br_font_body_end;
#else
extern const unsigned char br_font_display_start[];
extern const unsigned char br_font_display_end[];
extern const unsigned char br_font_body_start[];
extern const unsigned char br_font_body_end[];
#endif

int br_app_init(br_app *app)
{
    memset(app, 0, sizeof(*app));

    if (br_font_load(&app->display, br_font_display_start,
                     (unsigned)(br_font_display_end - br_font_display_start)) < 0 ||
        br_font_load(&app->body, br_font_body_start,
                     (unsigned)(br_font_body_end - br_font_body_start)) < 0)
        return -1;

    if (br_game_init(&app->game) < 0)
        return -1;

    if (br_save_init(&app->save, app->game.pack.world_count,
                     app->game.pack.worlds[0].level_count) < 0)
        return -1;
    br_save_load(&app->save);

    br_ui_art_load(&app->art);
    br_menu_init(&app->menu, &app->art);
    br_pause_init(&app->pause, &app->art);

    app->menu.world = app->save.last_world;
    app->menu.level = app->save.last_level;
    br_menu_open_worlds(&app->menu);
    app->screen = BR_APP_MENU;
    br_game_audio_music(&app->game.audio, 1);

    LOGI("app: ready -- %d worlds, %d stars saved",
         app->game.pack.world_count, br_save_total_stars(&app->save));
    return 0;
}

void br_app_free(br_app *app)
{
    br_save_flush(&app->save);
    br_ui_art_free(&app->art);
    br_save_free(&app->save);
    br_game_free(&app->game);
    br_font_free(&app->display);
    br_font_free(&app->body);
}

static void back_to_menu(br_app *app)
{
    br_save_flush(&app->save);
    br_game_audio_silence(&app->game.audio);
    br_game_audio_music(&app->game.audio, 1);
    br_menu_open_levels(&app->menu, app->menu.world);
    app->screen = BR_APP_MENU;
}

static void start_race(br_app *app)
{
    if (br_game_load(&app->game, app->menu.world, app->menu.level) < 0) {
        LOGE("app: could not start %d-%d", app->menu.world + 1, app->menu.level + 1);
        return;
    }
    br_save_remember_place(&app->save, app->menu.world, app->menu.level);
    br_game_audio_music(&app->game.audio, 0);
    app->last_race_state = app->game.state;
    app->screen = BR_APP_RACING;
}

static void update_menu(br_app *app, const br_input *in, float dt)
{
    switch (br_menu_update(&app->menu, in, dt, &app->game.pack)) {
    case BR_MENU_PLAY:
        start_race(app);
        break;
    case BR_MENU_QUIT:
        app->quit = 1;
        break;
    case BR_MENU_STAY:
        break;
    }
}

static void update_race(br_app *app, const br_input *in, float dt)
{
    /* Start pauses; Circle resets, which br_game_update handles; Cross moves
     * on once the run is over. */
    if (in->pause_pressed) {
        br_game_audio_silence(&app->game.audio);
        br_pause_open(&app->pause);
        app->screen = BR_APP_PAUSED;
        return;
    }

    br_game_update(&app->game, in, dt);

    if (app->game.state != app->last_race_state) {
        if (app->game.state == BR_STATE_FINISHED) {
            br_save_record(&app->save, app->game.world_index, app->game.level_index,
                           app->game.stars, app->game.elapsed);
            br_save_flush(&app->save);
        }
        app->last_race_state = app->game.state;
    }

    /* Following the game into the next level keeps the menu in step with it. */
    app->menu.world = app->game.world_index;
    app->menu.level = app->game.level_index;
    br_save_remember_place(&app->save, app->game.world_index, app->game.level_index);
}

static void update_paused(br_app *app, const br_input *in, float dt)
{
    switch (br_pause_update(&app->pause, in, dt)) {
    case BR_PAUSE_RESUME:
        app->screen = BR_APP_RACING;
        break;
    case BR_PAUSE_RESTART:
        br_game_restart(&app->game);
        app->last_race_state = app->game.state;
        app->screen = BR_APP_RACING;
        break;
    case BR_PAUSE_MENU:
        back_to_menu(app);
        break;
    case BR_PAUSE_NOTHING:
        break;
    }
}

void br_app_update(br_app *app, const br_input *in, float dt)
{
    switch (app->screen) {
    case BR_APP_MENU:   update_menu(app, in, dt);   break;
    case BR_APP_RACING: update_race(app, in, dt);   break;
    case BR_APP_PAUSED: update_paused(app, in, dt); break;
    }
}

/* A thin strip of state over the race: clock, stars and what to press next. */
static void draw_race_overlay(br_app *app)
{
    static const br_color TEXT   = { 0.99f, 0.97f, 0.92f, 1.0f };
    /* Black at 45%, which is already premultiplied. */
    static const br_color SHADOW = { 0.0f, 0.0f, 0.0f, 0.45f };
    const br_level *level = app->game.level;
    char text[96];

    br_ui_begin();

    br_fill_rect(0.0f, 0.0f, 960.0f, 46.0f, &SHADOW);

    snprintf(text, sizeof(text), "%d-%d  %s",
             app->game.world_index + 1, app->game.level_index + 1,
             br_world_name(app->game.world_index));
    br_font_draw(&app->body, text, 16.0f, 10.0f, 26.0f, &TEXT);

    snprintf(text, sizeof(text), "%.2f", app->game.elapsed);
    br_font_draw_centered(&app->display, text, 480.0f, 6.0f, 34.0f, &TEXT);

    snprintf(text, sizeof(text), "target %.0f / %.0f / %.0f",
             level->star_times[0], level->star_times[1], level->star_times[2]);
    br_font_draw_right(&app->body, text, 944.0f, 12.0f, 22.0f, &TEXT);

    switch (app->game.state) {
    case BR_STATE_WAITING_START:
        br_font_draw_centered(&app->display, "PRESS CROSS TO START", 480.0f,
                              440.0f, 36.0f, &TEXT);
        break;
    case BR_STATE_FINISHED:
        snprintf(text, sizeof(text), "FINISHED  %.2fs  -  %d STARS",
                 app->game.elapsed, app->game.stars);
        br_font_draw_centered(&app->display, text, 480.0f, 420.0f, 36.0f, &TEXT);
        br_font_draw_centered(&app->body, "Cross  next level      Start  menu",
                              480.0f, 466.0f, 24.0f, &TEXT);
        break;
    case BR_STATE_DEAD:
        br_font_draw_centered(&app->display, "CRASHED", 480.0f, 420.0f, 36.0f, &TEXT);
        br_font_draw_centered(&app->body, "Cross  retry      Start  menu",
                              480.0f, 466.0f, 24.0f, &TEXT);
        break;
    case BR_STATE_RUNNING:
        break;
    }
}

void br_app_draw(br_app *app, unsigned time_ms)
{
    static const br_color CLEAR = { 0.03f, 0.04f, 0.06f, 1.0f };

    br_render_begin(&CLEAR);

    if (app->screen == BR_APP_MENU) {
        br_menu_draw(&app->menu, &app->game.pack, &app->save,
                     &app->display, &app->body);
    } else {
        char where[64];

        br_scene_draw(&app->game.scene, app->game.level, &app->game.camera,
                      &app->game.bike, time_ms);
        draw_race_overlay(app);

        if (app->screen == BR_APP_PAUSED) {
            snprintf(where, sizeof(where), "%d-%d  %s",
                     app->game.world_index + 1, app->game.level_index + 1,
                     br_world_name(app->game.world_index));
            br_pause_draw(&app->pause, &app->display, &app->body, where);
        }
    }

    br_render_end();
}
