#include "settings.h"

#include <string.h>

#include "../engine/render.h"
#include "controls.h"
#include "glyphs.h"
#include "theme.h"
#include "uisound.h"

#define PANEL_W 520.0f
#define PANEL_H 416.0f
#define PANEL_Y  54.0f

#define OPTION_W 420.0f
#define OPTION_H  54.0f
#define OPTION_GAP 10.0f
#define OPTION_TOP 130.0f

enum { ROW_SOUND = 0, ROW_MUSIC, ROW_CONTROLS, ROW_UNLOCK, ROW_RESET };

void br_settings_init(br_settings *settings, const br_ui_art *art)
{
    memset(settings, 0, sizeof(*settings));
    settings->art = art;
    br_option_list_init(&settings->list, (BR_UI_W - OPTION_W) * 0.5f, OPTION_TOP,
                        OPTION_W, OPTION_H, OPTION_GAP);
    br_option_list_init(&settings->confirm_list, (BR_UI_W - 320.0f) * 0.5f,
                        262.0f, 320.0f, 54.0f, 12.0f);
}

static void rebuild(br_settings *settings)
{
    int selected = settings->list.selected;

    br_option_list_clear(&settings->list);
    br_option_list_add_switch(&settings->list, "Sound",
                              settings->save->sound_on ? &settings->art->t_toggle_on
                                                       : &settings->art->t_toggle_off);
    br_option_list_add_switch(&settings->list, "Music",
                              settings->save->music_on ? &settings->art->t_toggle_on
                                                       : &settings->art->t_toggle_off);
    br_option_list_add(&settings->list, "Controls", NULL);
    br_option_list_add(&settings->list, "Unlock everything", NULL);
    br_option_list_add(&settings->list, "Reset progress", NULL);
    settings->list.selected = selected;
}

static void ask(br_settings *settings, br_settings_confirm what)
{
    settings->confirming = what;
    br_option_list_clear(&settings->confirm_list);
    /* "No" first, so a stray Cross cannot wipe a save. */
    br_option_list_add(&settings->confirm_list, "No, go back", NULL);
    br_option_list_add(&settings->confirm_list,
                       what == BR_CONFIRM_RESET ? "Yes, erase it"
                                                : "Yes, unlock it all", NULL);
    settings->confirm_list.selected = 0;
    settings->confirm_list.held_y = 0;
    settings->confirm_list.repeat_delay = 0.0f;
    settings->confirm_list.touch_target = BR_OPTION_NONE;
}

void br_settings_open(br_settings *settings, br_save *save)
{
    settings->save = save;
    settings->showing_controls = 0;
    settings->back_touch = 0;
    settings->confirming = BR_CONFIRM_NONE;
    settings->list.selected = 0;
    settings->list.held_y = 0;
    settings->list.repeat_delay = 0.0f;
    settings->list.touch_target = BR_OPTION_NONE;
    rebuild(settings);
}

/* Circle, or a tap that goes down and comes back up on the back button.
 * Whatever state the screen is in, this means "one step out". */
static int back_requested(br_settings *settings, const br_input *in)
{
    if (in->touch_began)
        settings->back_touch = br_ui_back_button_hit(in->touch_ui_x, in->touch_ui_y);
    if (in->touch_ended) {
        int on_it = settings->back_touch &&
                    br_ui_back_button_hit(in->touch_ui_x, in->touch_ui_y);

        settings->back_touch = 0;
        if (on_it)
            return 1;
    }
    return in->back_pressed;
}

br_settings_action br_settings_update(br_settings *settings, const br_input *in,
                                      float dt)
{
    int back = back_requested(settings, in);
    int chosen;

    if (settings->showing_controls) {
        if (back || in->confirm_pressed || in->touch_ended) {
            settings->showing_controls = 0;
            br_ui_click();
        }
        return BR_SETTINGS_NOTHING;
    }

    if (settings->confirming != BR_CONFIRM_NONE) {
        int answer = br_option_list_update(&settings->confirm_list, in, dt);
        int reset = 0;

        if (back) {
            answer = 0;
            br_ui_click();
        }
        if (answer == 1) {
            if (settings->confirming == BR_CONFIRM_RESET) {
                br_save_reset_progress(settings->save);
                reset = 1;
            } else {
                br_save_unlock_everything(settings->save);
            }
        }
        if (answer >= 0) {
            settings->confirming = BR_CONFIRM_NONE;
            rebuild(settings);
        }
        return reset ? BR_SETTINGS_RESET : BR_SETTINGS_NOTHING;
    }

    chosen = br_option_list_update(&settings->list, in, dt);
    if (back) {
        br_ui_click();
        return BR_SETTINGS_CLOSE;
    }

    switch (chosen) {
    case ROW_SOUND:
        settings->save->sound_on = !settings->save->sound_on;
        settings->save->dirty = 1;
        rebuild(settings);
        break;
    case ROW_MUSIC:
        settings->save->music_on = !settings->save->music_on;
        settings->save->dirty = 1;
        rebuild(settings);
        break;
    case ROW_CONTROLS:
        settings->showing_controls = 1;
        break;
    case ROW_UNLOCK:
        ask(settings, BR_CONFIRM_UNLOCK);
        break;
    case ROW_RESET:
        ask(settings, BR_CONFIRM_RESET);
        break;
    default:
        break;
    }
    return BR_SETTINGS_NOTHING;
}

static void draw_panel(const br_settings *settings, float x, float y,
                       float w, float h)
{
    br_ui_panel(settings->art, x, y, w, h);
}

static void draw_controls(const br_settings *settings, const br_font *display,
                          const br_font *body)
{
    static const br_hint hints[] = { { BR_BUTTON_CIRCLE, "back" } };
    float w = 660.0f;
    float x = (BR_UI_W - w) * 0.5f;
    float y = 34.0f;

    draw_panel(settings, x, y, w, 466.0f);
    br_font_draw_centered(display, "CONTROLS", BR_UI_W * 0.5f, y + 14.0f, 34.0f,
                          &BR_INK);

    br_controls_draw(BR_CONTROLS_ALL, settings->art, body, x + 44.0f, y + 72.0f,
                     33.0f, &BR_INK);

    br_hints_draw(hints, 1, body,
                  (BR_UI_W - br_hints_width(hints, 1, body, 26.0f)) * 0.5f,
                  BR_UI_H - 40.0f, 26.0f, &BR_TEXT);
}

void br_settings_draw(const br_settings *settings, const br_font *display,
                      const br_font *body)
{
    static const br_hint hints[] = {
        { BR_BUTTON_CROSS,  "change" },
        { BR_BUTTON_CIRCLE, "back" },
    };
    float x = (BR_UI_W - PANEL_W) * 0.5f;

    br_ui_begin();
    br_fill_rect(0.0f, 0.0f, BR_UI_W, BR_UI_H, &BR_DIM);

    /* Drawn for every state, and before them: it sits in the bottom-left
     * corner, clear of all three panels, and it is the only way out of this
     * screen for someone using nothing but the touchscreen. */
    br_ui_back_button_draw(settings->art, settings->back_touch);

    if (settings->showing_controls) {
        draw_controls(settings, display, body);
        return;
    }

    if (settings->confirming != BR_CONFIRM_NONE) {
        float cw = 460.0f, cx = (BR_UI_W - cw) * 0.5f;
        int reset = settings->confirming == BR_CONFIRM_RESET;

        draw_panel(settings, cx, 150.0f, cw, 260.0f);
        br_font_draw_centered(display, "ARE YOU SURE?", BR_UI_W * 0.5f, 172.0f,
                              34.0f, &BR_INK);
        /* Two lines: what goes, and what survives. Saying only the first left
         * "unlock everything" claiming it could not be undone when a reset
         * plainly undoes it. */
        br_font_draw_centered(body,
                              reset ? "Every star, best time and ghost goes."
                                    : "Every world and level opens at once.",
                              BR_UI_W * 0.5f, 204.0f, 22.0f, &BR_INK);
        br_font_draw_centered(body,
                              reset ? "Sound, music and your bike stay."
                                    : "Only resetting the progress undoes it.",
                              BR_UI_W * 0.5f, 230.0f, 22.0f, &BR_INK);
        br_option_list_draw(&settings->confirm_list, body, 26.0f,
                            &settings->art->t_level_tile,
                            &settings->art->t_level_tile_active,
                            &BR_ROW, &BR_ROW_SELECTED, &BR_TEXT, &BR_TEXT);
        return;
    }

    draw_panel(settings, x, PANEL_Y, PANEL_W, PANEL_H);
    br_font_draw_centered(display, "SETTINGS", BR_UI_W * 0.5f, PANEL_Y + 24.0f,
                          38.0f, &BR_INK);
    br_option_list_draw(&settings->list, body, 28.0f,
                        &settings->art->t_level_tile,
                        &settings->art->t_level_tile_active,
                        &BR_ROW, &BR_ROW_SELECTED, &BR_TEXT, &BR_TEXT);

    br_hints_draw(hints, 2, body,
                  (BR_UI_W - br_hints_width(hints, 2, body, 26.0f)) * 0.5f,
                  BR_UI_H - 44.0f, 26.0f, &BR_TEXT);
}
