#include "settings.h"

#include <string.h>

#include "../engine/render.h"
#include "controls.h"
#include "glyphs.h"
#include "theme.h"

#define PANEL_W 520.0f
#define PANEL_H 360.0f
#define PANEL_Y  92.0f

#define OPTION_W 420.0f
#define OPTION_H  56.0f
#define OPTION_GAP 12.0f
#define OPTION_TOP 176.0f

enum { ROW_SOUND = 0, ROW_MUSIC, ROW_CONTROLS, ROW_BACK };

void br_settings_init(br_settings *settings, const br_ui_art *art)
{
    memset(settings, 0, sizeof(*settings));
    settings->art = art;
    br_option_list_init(&settings->list, (BR_UI_W - OPTION_W) * 0.5f, OPTION_TOP,
                        OPTION_W, OPTION_H, OPTION_GAP);
}

static void rebuild(br_settings *settings)
{
    int selected = settings->list.selected;

    br_option_list_clear(&settings->list);
    br_option_list_add(&settings->list, "Sound",
                       *settings->sound_on ? "On" : "Off");
    br_option_list_add(&settings->list, "Music",
                       *settings->music_on ? "On" : "Off");
    br_option_list_add(&settings->list, "Controls", NULL);
    br_option_list_add(&settings->list, "Back", NULL);
    settings->list.selected = selected;
}

void br_settings_open(br_settings *settings, int *sound_on, int *music_on,
                      int *changed)
{
    settings->sound_on = sound_on;
    settings->music_on = music_on;
    settings->changed = changed;
    settings->showing_controls = 0;
    settings->list.selected = 0;
    settings->list.held_y = 0;
    settings->list.repeat_delay = 0.0f;
    settings->list.touch_target = BR_OPTION_NONE;
    rebuild(settings);
}

br_settings_action br_settings_update(br_settings *settings, const br_input *in,
                                      float dt)
{
    int chosen;

    if (settings->showing_controls) {
        if (in->back_pressed || in->confirm_pressed || in->touch_ended)
            settings->showing_controls = 0;
        return BR_SETTINGS_NOTHING;
    }

    chosen = br_option_list_update(&settings->list, in, dt);
    if (in->back_pressed)
        return BR_SETTINGS_CLOSE;

    switch (chosen) {
    case ROW_SOUND:
        *settings->sound_on = !*settings->sound_on;
        *settings->changed = 1;
        rebuild(settings);
        break;
    case ROW_MUSIC:
        *settings->music_on = !*settings->music_on;
        *settings->changed = 1;
        rebuild(settings);
        break;
    case ROW_CONTROLS:
        settings->showing_controls = 1;
        break;
    case ROW_BACK:
        return BR_SETTINGS_CLOSE;
    default:
        break;
    }
    return BR_SETTINGS_NOTHING;
}

static void draw_panel(const br_settings *settings, float x, float y,
                       float w, float h)
{
    if (br_ui_has(&settings->art->t_panel))
        br_draw_rect(x, y, x + w, y + h, &settings->art->t_panel, NULL);
    else
        br_fill_round_rect(x, y, w, h, 14.0f, &BR_PANEL);
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

    if (settings->showing_controls) {
        draw_controls(settings, display, body);
        return;
    }

    draw_panel(settings, x, PANEL_Y, PANEL_W, PANEL_H);
    br_font_draw_centered(display, "SETTINGS", BR_UI_W * 0.5f, PANEL_Y + 24.0f,
                          38.0f, &BR_INK);
    br_option_list_draw(&settings->list, body, 28.0f, &BR_ROW, &BR_ROW_SELECTED,
                        &BR_INK);

    br_hints_draw(hints, 2, body,
                  (BR_UI_W - br_hints_width(hints, 2, body, 26.0f)) * 0.5f,
                  BR_UI_H - 44.0f, 26.0f, &BR_TEXT);
}
