#include "pause.h"

#include <string.h>

#include "../engine/render.h"
#include "controls.h"
#include "glyphs.h"
#include "theme.h"

#define PANEL_W 620.0f
#define PANEL_H 468.0f
#define PANEL_Y  40.0f

#define ICON_SIZE 78.0f
#define ICON_GAP  44.0f

void br_pause_init(br_pause *pause, const br_ui_art *art)
{
    memset(pause, 0, sizeof(*pause));
    pause->art = art;
    br_iconbar_init(&pause->bar, BR_UI_W * 0.5f, PANEL_Y + 344.0f,
                    ICON_SIZE, ICON_GAP);
    br_iconbar_add(&pause->bar, &art->t_icon_play, "Resume");
    br_iconbar_add(&pause->bar, &art->t_icon_retry, "Restart");
    br_iconbar_add(&pause->bar, &art->t_icon_list, "Levels");
}

void br_pause_open(br_pause *pause)
{
    pause->bar.selected = 0;
    pause->bar.held_x = 0;
    pause->bar.repeat_delay = 0.0f;
    pause->bar.touch_target = BR_ICON_NONE;
}

br_pause_action br_pause_update(br_pause *pause, const br_input *in, float dt)
{
    int chosen = br_iconbar_update(&pause->bar, in, dt);

    /* Circle and Start both back out, which is what both do everywhere else. */
    if (in->back_pressed || in->pause_pressed)
        return BR_PAUSE_RESUME;

    switch (chosen) {
    case 1:  return BR_PAUSE_RESTART;
    case 2:  return BR_PAUSE_MENU;
    case 0:  return BR_PAUSE_RESUME;
    default: return BR_PAUSE_NOTHING;
    }
}

void br_pause_draw(const br_pause *pause, const br_font *display,
                   const br_font *body, const char *subtitle)
{
    float x = (BR_UI_W - PANEL_W) * 0.5f;

    br_ui_begin();
    br_fill_rect(0.0f, 0.0f, BR_UI_W, BR_UI_H, &BR_DIM);

    if (br_ui_has(&pause->art->t_panel))
        br_draw_rect(x, PANEL_Y, x + PANEL_W, PANEL_Y + PANEL_H,
                     &pause->art->t_panel, NULL);
    else
        br_fill_round_rect(x, PANEL_Y, PANEL_W, PANEL_H, 16.0f, &BR_PANEL);

    br_font_draw_centered(display, "PAUSED", BR_UI_W * 0.5f, PANEL_Y + 16.0f,
                          38.0f, &BR_INK);
    if (subtitle)
        br_font_draw_centered(body, subtitle, BR_UI_W * 0.5f, PANEL_Y + 58.0f,
                              22.0f, &BR_INK);

    /* Only the controls that mean anything with a bike under you. */
    br_controls_draw(BR_CONTROLS_RACE, pause->art, body, x + 56.0f,
                     PANEL_Y + 108.0f, 32.0f, &BR_INK);

    br_iconbar_draw(&pause->bar, body, 22.0f, &BR_INK, &BR_INK);
}
