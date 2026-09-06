#include "pause.h"

#include <string.h>

#include "../engine/render.h"
#include "controls.h"
#include "glyphs.h"
#include "theme.h"

#define PANEL_W 620.0f
#define PANEL_H 274.0f
#define PANEL_Y 128.0f

#define ICON_SIZE 78.0f
#define ICON_GAP  30.0f

void br_pause_init(br_pause *pause, const br_ui_art *art)
{
    memset(pause, 0, sizeof(*pause));
    pause->art = art;
    br_iconbar_init(&pause->bar, BR_UI_W * 0.5f, PANEL_Y + 132.0f,
                    ICON_SIZE, ICON_GAP);
    br_iconbar_add(&pause->bar, &art->t_icon_play, "Resume");
    br_iconbar_add(&pause->bar, &art->t_icon_retry, "Restart");
    br_iconbar_add(&pause->bar, &art->t_icon_controls, "Controls");
    br_iconbar_add(&pause->bar, &art->t_icon_list, "Levels");
}

void br_pause_open(br_pause *pause)
{
    pause->showing_controls = 0;
    pause->bar.selected = 0;
    pause->bar.held_x = 0;
    pause->bar.repeat_delay = 0.0f;
    pause->bar.touch_target = BR_ICON_NONE;
}

br_pause_action br_pause_update(br_pause *pause, const br_input *in, float dt)
{
    int chosen;

    if (pause->showing_controls) {
        if (in->back_pressed || in->confirm_pressed || in->touch_ended)
            pause->showing_controls = 0;
        return BR_PAUSE_NOTHING;
    }

    chosen = br_iconbar_update(&pause->bar, in, dt);

    /* Circle and Start both back out, which is what both do everywhere else. */
    if (in->back_pressed || in->pause_pressed)
        return BR_PAUSE_RESUME;

    switch (chosen) {
    case 0:  return BR_PAUSE_RESUME;
    case 1:  return BR_PAUSE_RESTART;
    case 2:  pause->showing_controls = 1; return BR_PAUSE_NOTHING;
    case 3:  return BR_PAUSE_MENU;
    default: return BR_PAUSE_NOTHING;
    }
}

void br_pause_draw(const br_pause *pause, const br_font *display,
                   const br_font *body, const char *subtitle)
{
    float x = (BR_UI_W - PANEL_W) * 0.5f;

    br_ui_begin();
    br_fill_rect(0.0f, 0.0f, BR_UI_W, BR_UI_H, &BR_DIM);

    br_ui_panel(pause->art, x, PANEL_Y, PANEL_W, PANEL_H);

    br_font_draw_centered(display, "PAUSED", BR_UI_W * 0.5f, PANEL_Y + 16.0f,
                          38.0f, &BR_INK);
    if (subtitle)
        br_font_draw_centered(body, subtitle, BR_UI_W * 0.5f, PANEL_Y + 58.0f,
                              22.0f, &BR_INK);

    if (pause->showing_controls) {
        /* Only the ones that mean anything with a bike under you. */
        static const br_hint back[] = { { BR_BUTTON_CIRCLE, "back" } };

        br_controls_draw(BR_CONTROLS_RACE, pause->art, body, x + 52.0f,
                         PANEL_Y + 104.0f, 30.0f, &BR_INK);
        br_hints_draw(back, 1, body,
                      (BR_UI_W - br_hints_width(back, 1, body, 26.0f)) * 0.5f,
                      PANEL_Y + PANEL_H - 46.0f, 26.0f, &BR_INK);
        return;
    }

    br_iconbar_draw(&pause->bar, body, 22.0f, &BR_INK, &BR_INK);
}
