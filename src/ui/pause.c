#include "pause.h"

#include <string.h>

#include "../engine/render.h"
#include "glyphs.h"
#include "theme.h"

#define PANEL_W 470.0f
#define PANEL_H 330.0f
#define PANEL_Y 108.0f

#define OPTION_W 360.0f
#define OPTION_H  62.0f
#define OPTION_GAP 14.0f
#define OPTION_TOP 214.0f

void br_pause_init(br_pause *pause, const br_ui_art *art)
{
    memset(pause, 0, sizeof(*pause));
    pause->art = art;
    br_option_list_init(&pause->list, (BR_UI_W - OPTION_W) * 0.5f, OPTION_TOP,
                        OPTION_W, OPTION_H, OPTION_GAP);
    br_option_list_add(&pause->list, "Resume", NULL);
    br_option_list_add(&pause->list, "Restart", NULL);
    br_option_list_add(&pause->list, "Level list", NULL);
}

void br_pause_open(br_pause *pause)
{
    pause->list.selected = 0;
    pause->list.held_y = 0;
    pause->list.repeat_delay = 0.0f;
    pause->list.touch_target = BR_OPTION_NONE;
}

br_pause_action br_pause_update(br_pause *pause, const br_input *in, float dt)
{
    int chosen = br_option_list_update(&pause->list, in, dt);

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
    static const br_hint hints[] = {
        { BR_BUTTON_CROSS,  "choose" },
        { BR_BUTTON_CIRCLE, "resume" },
    };
    float panel_x = (BR_UI_W - PANEL_W) * 0.5f;

    br_ui_begin();
    br_fill_rect(0.0f, 0.0f, BR_UI_W, BR_UI_H, &BR_DIM);

    if (br_ui_has(&pause->art->t_panel))
        br_draw_rect(panel_x, PANEL_Y, panel_x + PANEL_W, PANEL_Y + PANEL_H,
                     &pause->art->t_panel, NULL);
    else
        br_fill_round_rect(panel_x, PANEL_Y, PANEL_W, PANEL_H, 14.0f, &BR_PANEL);

    br_font_draw_centered(display, "PAUSED", BR_UI_W * 0.5f, PANEL_Y + 26.0f,
                          40.0f, &BR_INK);
    if (subtitle)
        br_font_draw_centered(body, subtitle, BR_UI_W * 0.5f, PANEL_Y + 72.0f,
                              24.0f, &BR_INK);

    br_option_list_draw(&pause->list, body, 30.0f, &BR_ROW, &BR_ROW_SELECTED, &BR_INK);

    br_hints_draw(hints, 2, body,
                  (BR_UI_W - br_hints_width(hints, 2, body, 26.0f)) * 0.5f,
                  BR_UI_H - 46.0f, 26.0f, &BR_TEXT);
}
