#include "start.h"

#include <string.h>

#include "../engine/render.h"
#include "glyphs.h"
#include "theme.h"

#define OPTION_X    46.0f
#define OPTION_W   300.0f
#define OPTION_H    58.0f
#define OPTION_GAP  12.0f
#define OPTION_TOP 286.0f

void br_start_init(br_start *start, const br_ui_art *art)
{
    memset(start, 0, sizeof(*start));
    start->art = art;
    br_option_list_init(&start->list, OPTION_X, OPTION_TOP, OPTION_W, OPTION_H,
                        OPTION_GAP);
    br_option_list_add(&start->list, "Start", NULL);
    br_option_list_add(&start->list, "Settings", NULL);
    br_option_list_add(&start->list, "Exit", NULL);
}

void br_start_open(br_start *start)
{
    start->list.selected = 0;
    start->list.held_y = 0;
    start->list.repeat_delay = 0.0f;
    start->list.touch_target = BR_OPTION_NONE;
}

br_start_action br_start_update(br_start *start, const br_input *in, float dt)
{
    switch (br_option_list_update(&start->list, in, dt)) {
    case 0:  return BR_START_SINGLE_PLAYER;
    case 1:  return BR_START_SETTINGS;
    case 2:  return BR_START_EXIT;
    default: return BR_START_NOTHING;
    }
}

void br_start_draw_backdrop(const br_start *start, const br_font *display,
                            const br_font *body)
{
    br_ui_begin();

    if (br_ui_has(&start->art->t_start_screen)) {
        /* The art is 960x640 and the screen is 960x544, so the lower ground is
         * cropped away rather than squashing the rider. */
        br_texture cropped = start->art->t_start_screen;
        cropped.v1 = BR_UI_H / 640.0f;
        br_draw_rect(0.0f, 0.0f, BR_UI_W, BR_UI_H, &cropped, NULL);
    } else {
        br_fill_rect(0.0f, 0.0f, BR_UI_W, BR_UI_H, &BR_PANEL);
    }

    if (br_ui_has(&start->art->t_logo)) {
        /* The artwork carries "by Top Free Games" below a clear gap at 226 of
         * 247 rows. Crop it off and put this port's own line there instead. */
        br_texture cropped = start->art->t_logo;
        float logo_rows = 226.0f / 247.0f;
        float h, w;

        cropped.v1 = cropped.v0 + (cropped.v1 - cropped.v0) * logo_rows;
        h = 138.0f * logo_rows;
        w = 138.0f * (float)start->art->logo.width / (float)start->art->logo.height;
        br_draw_rect(40.0f, 26.0f, 40.0f + w, 26.0f + h, &cropped, NULL);
        br_font_draw(body, "by franciscoclou", 46.0f, 26.0f + h + 2.0f,
                     22.0f, &BR_TEXT);
    } else {
        br_font_draw(display, "BIKE RACE", 40.0f, 40.0f, 54.0f, &BR_TEXT);
    }
}

void br_start_draw(const br_start *start, const br_font *display,
                   const br_font *body)
{
    static const br_hint hints[] = {
        { BR_BUTTON_CROSS, "choose" },
        { BR_BUTTON_DPAD,  "move" },
        { BR_BUTTON_TOUCH, "tap" },
    };

    br_start_draw_backdrop(start, display, body);

    br_option_list_draw(&start->list, body, 28.0f, &BR_PANEL, &BR_ROW_SELECTED,
                        &BR_TEXT);
    /* The selected row is bright, so its label needs the dark ink instead. */
    {
        float x, y, w, h;
        if (br_option_list_rect(&start->list, start->list.selected, &x, &y, &w, &h))
            br_font_draw_centered(body, start->list.labels[start->list.selected],
                                  x + w * 0.5f, y + (h - 28.0f) * 0.5f, 28.0f,
                                  &BR_INK);
    }

    br_hints_draw(hints, 3, body, OPTION_X, BR_UI_H - 42.0f, 26.0f, &BR_TEXT);
}
