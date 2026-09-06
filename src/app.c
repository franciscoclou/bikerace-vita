#include "app.h"

#include <stdio.h>
#include <string.h>

#include "engine/render.h"
#include "platform/audio.h"
#include "platform/log.h"
#include "ui/glyphs.h"
#include "ui/theme.h"

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

static void apply_audio_settings(br_app *app)
{
    br_game_audio_set_enabled(&app->game.audio, app->save.sound_on,
                              app->save.music_on);
}

int br_app_init(br_app *app)
{
    memset(app, 0, sizeof(*app));

    if (br_font_load(&app->display, br_font_display_start,
                     (unsigned)(br_font_display_end - br_font_display_start)) < 0 ||
        br_font_load(&app->body, br_font_body_start,
                     (unsigned)(br_font_body_end - br_font_body_start)) < 0)
        return -1;

    if (br_audio_init() < 0)
        LOGW("app: no audio device -- carrying on silently");

    if (br_game_init(&app->game) < 0)
        return -1;

    if (br_save_init(&app->save, app->game.pack.world_count,
                     app->game.pack.worlds[0].level_count) < 0)
        return -1;
    br_save_load(&app->save);

    app->ghosts = br_ghost_store_open(app->game.pack.world_count,
                                      app->game.pack.worlds[0].level_count);

    br_ui_art_load(&app->art);
    br_menu_init(&app->menu, &app->art);
    br_pause_init(&app->pause, &app->art);
    br_result_init(&app->result, &app->art);
    br_start_init(&app->start, &app->art);
    br_settings_init(&app->settings, &app->art);

    app->menu.world = app->save.last_world;
    app->menu.level = app->save.last_level;
    app->menu.bike  = app->save.bike_type;
    app->game.bike_type = (br_bike_type)app->save.bike_type;

    br_start_open(&app->start);
    app->screen = BR_APP_START;
    apply_audio_settings(app);
    br_game_audio_music(&app->game.audio, 1);

    LOGI("app: ready -- %d worlds, %d stars saved",
         app->game.pack.world_count, br_save_total_stars(&app->save));
    return 0;
}

void br_app_free(br_app *app)
{
    br_save_flush(&app->save);
    br_ghost_store_flush(app->ghosts);
    br_ghost_store_close(app->ghosts);
    br_menu_free(&app->menu);
    br_ui_art_free(&app->art);
    br_save_free(&app->save);
    br_game_free(&app->game);
    br_font_free(&app->display);
    br_font_free(&app->body);
    br_audio_shutdown();
}

/* ---------------------------------------------------------- transitions -- */

static void to_menu(br_app *app)
{
    br_save_flush(&app->save);
    br_game_audio_silence(&app->game.audio);
    br_game_audio_music(&app->game.audio, 1);
    app->screen = BR_APP_MENU;
}

static void resume_race(br_app *app)
{
    /* The button that dismissed the overlay must not also open the throttle. */
    br_game_lock_throttle(&app->game);
    app->screen = BR_APP_RACING;
}

/* Points the race at this level's ghost and loads the bike it was set on.
 * Anything that changes the level, or the ghost itself, has to call this: the
 * sprite is loaded once, so a new record set on a different bike would
 * otherwise keep drawing the old one until the level was re-entered. */
static void refresh_ghost(br_app *app, int world, int level)
{
    int keep_enabled = app->game.ghost_enabled;

    app->game.ghost = br_ghost_get(app->ghosts, world, level);
    app->game.ghost_enabled = app->game.ghost != NULL && keep_enabled;
    br_scene_use_ghost(&app->game.scene,
                       app->game.ghost ? (int)app->game.ghost->bike : -1);
}

static void begin_race(br_app *app, int world, int level)
{
    if (app->game.bike_type != (br_bike_type)app->menu.bike) {
        app->game.bike_type = (br_bike_type)app->menu.bike;
        app->save.bike_type = app->menu.bike;
        app->save.dirty = 1;
    }
    if (br_game_load(&app->game, world, level) < 0) {
        LOGE("app: could not start %d-%d", world + 1, level + 1);
        return;
    }
    app->game.ghost_enabled = 1;   /* a fresh level starts showing its ghost */
    br_save_remember_place(&app->save, world, level);

    refresh_ghost(app, world, level);

    br_game_audio_music(&app->game.audio, 0);
    app->last_race_state = app->game.state;
    resume_race(app);
}

/* Where "next" would go, rolling into the next world. */
static void next_level_of(const br_app *app, int *world, int *level)
{
    *world = app->game.world_index;
    *level = app->game.level_index + 1;
    if (*level >= app->game.pack.worlds[*world].level_count) {
        *level = 0;
        (*world)++;
    }
}

static int next_level_reachable(const br_app *app)
{
    int world, level;

    next_level_of(app, &world, &level);
    if (world >= app->game.pack.world_count)
        return 0;
    return br_save_level_unlocked(&app->save, world, level);
}

static void show_result(br_app *app)
{
    const br_level_progress *progress =
        br_save_level(&app->save, app->game.world_index, app->game.level_index);
    float previous_best = progress ? progress->best_time : 0.0f;
    int finished = app->game.state == BR_STATE_FINISHED;
    int record = finished && (previous_best <= 0.0f || app->game.elapsed < previous_best);

    if (finished) {
        br_save_record(&app->save, app->game.world_index, app->game.level_index,
                       app->game.stars, app->game.elapsed);
        br_save_unlock_next(&app->save, app->game.world_index, app->game.level_index);
        br_save_flush(&app->save);

        /* Keep the run only when it beat what was there. A run too long to
         * record simply leaves the old ghost alone. */
        if (!app->game.recorder.overflowed && app->game.recorder.count > 1) {
            br_ghost run;
            run.samples = app->game.recorder.samples;
            run.count = app->game.recorder.count;
            run.bike = app->game.bike_type;
            run.time = app->game.elapsed;
            br_ghost_put(app->ghosts, app->game.world_index,
                         app->game.level_index, &run);
            br_ghost_store_flush(app->ghosts);
            /* The record may have been set on a different bike. */
            refresh_ghost(app, app->game.world_index, app->game.level_index);
        }
    }

    /* Only offer "next" when there is one and it can be played: the level
     * after a world's last is in the next world, which may still want stars. */
    br_result_open(&app->result, finished, next_level_reachable(app),
                   app->game.stars, app->game.elapsed, previous_best, record);
    app->screen = BR_APP_RESULT;
}

/* --------------------------------------------------------------- update -- */

static void update_start(br_app *app, const br_input *in, float dt)
{
    switch (br_start_update(&app->start, in, dt)) {
    case BR_START_SINGLE_PLAYER:
        br_menu_open_worlds(&app->menu);
        app->screen = BR_APP_MENU;
        break;
    case BR_START_SETTINGS:
        br_settings_open(&app->settings, &app->save);
        app->screen = BR_APP_SETTINGS;
        break;
    case BR_START_EXIT:
        app->quit = 1;
        break;
    case BR_START_NOTHING:
        break;
    }
}

static void update_settings(br_app *app, const br_input *in, float dt)
{
    int was_sound = app->save.sound_on, was_music = app->save.music_on;

    if (br_settings_update(&app->settings, in, dt) == BR_SETTINGS_CLOSE) {
        br_save_flush(&app->save);
        br_start_open(&app->start);
        app->screen = BR_APP_START;
    }
    if (app->save.sound_on != was_sound || app->save.music_on != was_music)
        apply_audio_settings(app);
}

static void update_menu(br_app *app, const br_input *in, float dt)
{
    switch (br_menu_update(&app->menu, in, dt, &app->game.pack, &app->save)) {
    case BR_MENU_PLAY:
        begin_race(app, app->menu.world, app->menu.level);
        break;
    case BR_MENU_BACK:
        br_start_open(&app->start);
        app->screen = BR_APP_START;
        break;
    case BR_MENU_STAY:
        break;
    }
}

static void update_race(br_app *app, const br_input *in, float dt)
{
    /* Circle puts the bike back on the line. The race screen owns this, the
     * way pause and the end-of-run panel own their own buttons. */
    if (in->back_pressed) {
        br_game_restart(&app->game);
        app->last_race_state = app->game.state;
        return;
    }

    if (in->pause_pressed) {
        br_game_audio_silence(&app->game.audio);
        br_pause_open(&app->pause, app->game.ghost != NULL,
                      app->game.ghost_enabled);
        app->screen = BR_APP_PAUSED;
        return;
    }

    br_game_update(&app->game, in, dt);

    if (app->game.state != app->last_race_state) {
        app->last_race_state = app->game.state;
        if (app->game.state == BR_STATE_FINISHED || app->game.state == BR_STATE_DEAD) {
            show_result(app);
            return;
        }
    }

    app->menu.world = app->game.world_index;
    app->menu.level = app->game.level_index;
}

static void update_paused(br_app *app, const br_input *in, float dt)
{
    switch (br_pause_update(&app->pause, in, dt)) {
    case BR_PAUSE_RESUME:
        resume_race(app);
        break;
    case BR_PAUSE_RESTART:
        br_game_restart(&app->game);
        app->last_race_state = app->game.state;
        resume_race(app);
        break;
    case BR_PAUSE_MENU:
        br_menu_open_levels(&app->menu, app->game.world_index);
        to_menu(app);
        break;
    case BR_PAUSE_TOGGLE_GHOST:
        app->game.ghost_enabled = !app->game.ghost_enabled;
        br_pause_open(&app->pause, app->game.ghost != NULL,
                      app->game.ghost_enabled);
        break;
    case BR_PAUSE_NOTHING:
        break;
    }
}

static void update_result(br_app *app, const br_input *in, float dt)
{
    switch (br_result_update(&app->result, in, dt)) {
    case BR_RESULT_NEXT:
        if (!next_level_reachable(app)) {
            br_menu_open_levels(&app->menu, app->game.world_index);
            to_menu(app);
            break;
        }
        if (br_game_next_level(&app->game) == 0) {
            refresh_ghost(app, app->game.world_index, app->game.level_index);
            app->game.ghost_enabled = app->game.ghost != NULL;
            app->menu.world = app->game.world_index;
            app->menu.level = app->game.level_index;
            br_save_remember_place(&app->save, app->game.world_index,
                                   app->game.level_index);
            app->last_race_state = app->game.state;
            resume_race(app);
        }
        break;
    case BR_RESULT_REPEAT:
        br_game_restart(&app->game);
        app->last_race_state = app->game.state;
        resume_race(app);
        break;
    case BR_RESULT_MENU:
        br_menu_open_levels(&app->menu, app->game.world_index);
        to_menu(app);
        break;
    case BR_RESULT_NOTHING:
        break;
    }
}

void br_app_update(br_app *app, const br_input *in, float dt)
{
    switch (app->screen) {
    case BR_APP_START:    update_start(app, in, dt);    break;
    case BR_APP_SETTINGS: update_settings(app, in, dt); break;
    case BR_APP_MENU:     update_menu(app, in, dt);     break;
    case BR_APP_RACING:   update_race(app, in, dt);     break;
    case BR_APP_PAUSED:   update_paused(app, in, dt);   break;
    case BR_APP_RESULT:   update_result(app, in, dt);   break;
    }
}

/* ----------------------------------------------------------------- draw -- */

/* Straight onto the game, with a dark ring behind each string: a panel here
 * covers art you have paid for, and the sky in worlds 1 and 7 is bright enough
 * that plain text would vanish into it. */
static void draw_race_overlay(br_app *app)
{
    static const br_color OUTLINE = { 0.0f, 0.0f, 0.0f, 0.72f };
    static const br_hint waiting[] = { { BR_BUTTON_CROSS, "start" } };
    const br_level *level = app->game.level;
    char text[96];

    br_ui_begin();

    snprintf(text, sizeof(text), "%d-%d  %s",
             app->game.world_index + 1, app->game.level_index + 1,
             br_world_name(app->game.world_index));
    br_font_draw_outlined(&app->body, text, 20.0f, 14.0f, 25.0f, -1,
                          &BR_TEXT, &OUTLINE);

    snprintf(text, sizeof(text), "%.2f", app->game.elapsed);
    br_font_draw_outlined(&app->display, text, BR_UI_W * 0.5f, 10.0f, 38.0f, 0,
                          &BR_TEXT, &OUTLINE);

    snprintf(text, sizeof(text), "%.0f / %.0f / %.0f",
             level->star_times[0], level->star_times[1], level->star_times[2]);
    br_font_draw_outlined(&app->body, text, BR_UI_W - 20.0f, 14.0f, 23.0f, 1,
                          &BR_TEXT, &OUTLINE);

    if (app->game.state == BR_STATE_WAITING_START) {
        float w = br_hints_width(waiting, 1, &app->body, 34.0f);
        br_hints_draw(waiting, 1, &app->body, (BR_UI_W - w) * 0.5f,
                      BR_UI_H - 84.0f, 34.0f, &BR_TEXT);
    }
}

static void level_caption(const br_app *app, char *out, unsigned size)
{
    snprintf(out, size, "%d-%d  %s", app->game.world_index + 1,
             app->game.level_index + 1, br_world_name(app->game.world_index));
}

void br_app_draw(br_app *app, unsigned time_ms)
{
    static const br_color CLEAR = { 0.03f, 0.04f, 0.06f, 1.0f };
    char caption[64];

    br_render_begin(&CLEAR);

    switch (app->screen) {
    case BR_APP_START:
        br_start_draw(&app->start, &app->display, &app->body);
        break;

    case BR_APP_SETTINGS:
        br_start_draw_backdrop(&app->start, &app->display, &app->body);
        br_settings_draw(&app->settings, &app->display, &app->body);
        break;

    case BR_APP_MENU:
        br_menu_draw(&app->menu, &app->game.pack, &app->save,
                     &app->display, &app->body);
        break;

    default:
        {
            br_ghost_pose pose;

            memset(&pose, 0, sizeof(pose));
            if (app->game.ghost && app->game.ghost_enabled &&
                app->screen == BR_APP_RACING) {
                pose.active = br_ghost_pose_at(app->game.ghost, app->game.elapsed,
                                               &pose.pos, &pose.angle_deg,
                                               &pose.wheel_deg) ||
                              app->game.elapsed <= app->game.ghost->time;
                pose.bike = app->game.ghost->bike;
            }
            br_scene_draw(&app->game.scene, app->game.level, &app->game.camera,
                          &app->game.bike, &pose, time_ms);
        }
        draw_race_overlay(app);
        level_caption(app, caption, sizeof(caption));
        if (app->screen == BR_APP_PAUSED)
            br_pause_draw(&app->pause, &app->display, &app->body, caption);
        else if (app->screen == BR_APP_RESULT)
            br_result_draw(&app->result, &app->display, &app->body, caption);
        break;
    }

    br_render_end();
}
