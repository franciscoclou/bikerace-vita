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

    br_option_list_init(&start->confirm_list, (BR_UI_W - 320.0f) * 0.5f,
                        294.0f, 320.0f, 54.0f, 12.0f);
}

void br_start_open(br_start *start)
{
    start->confirming_exit = 0;
    start->list.selected = 0;
    start->list.held_y = 0;
    start->list.repeat_delay = 0.0f;
    start->list.touch_target = BR_OPTION_NONE;
}

static void ask_exit(br_start *start)
{
    start->confirming_exit = 1;
    br_option_list_clear(&start->confirm_list);
    /* "No" first, as everywhere else, so a stray Cross cannot close the game. */
    br_option_list_add(&start->confirm_list, "No, keep playing", NULL);
    br_option_list_add(&start->confirm_list, "Yes, quit", NULL);
    start->confirm_list.selected = 0;
    start->confirm_list.held_y = 0;
    start->confirm_list.repeat_delay = 0.0f;
    start->confirm_list.touch_target = BR_OPTION_NONE;
}

br_start_action br_start_update(br_start *start, const br_input *in, float dt)
{
    if (start->confirming_exit) {
        int answer = br_option_list_update(&start->confirm_list, in, dt);

        if (in->back_pressed)
            answer = 0;
        if (answer == 1)
            return BR_START_EXIT;
        if (answer == 0)
            start->confirming_exit = 0;
        return BR_START_NOTHING;
    }

    switch (br_option_list_update(&start->list, in, dt)) {
    case 0:  return BR_START_SINGLE_PLAYER;
    case 1:  return BR_START_SETTINGS;
    case 2:  ask_exit(start); return BR_START_NOTHING;
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

    if (start->confirming_exit) {
        static const br_hint leaving[] = {
            { BR_BUTTON_CROSS,  "choose" },
            { BR_BUTTON_CIRCLE, "stay" },
        };
        float cw = 420.0f, cx = (BR_UI_W - cw) * 0.5f;

        br_fill_rect(0.0f, 0.0f, BR_UI_W, BR_UI_H, &BR_DIM);
        br_ui_panel(start->art, cx, 186.0f, cw, 226.0f);
        br_font_draw_centered(display, "LEAVE THE GAME?", BR_UI_W * 0.5f,
                              208.0f, 32.0f, &BR_INK);
        br_font_draw_centered(body, "Your progress is already saved.",
                              BR_UI_W * 0.5f, 256.0f, 22.0f, &BR_INK);
        br_option_list_draw(&start->confirm_list, body, 26.0f,
                            &start->art->t_level_tile,
                            &start->art->t_level_tile_active,
                            &BR_ROW, &BR_ROW_SELECTED, &BR_TEXT, &BR_TEXT);
        br_hints_draw(leaving, 2, body,
                      (BR_UI_W - br_hints_width(leaving, 2, body, 26.0f)) * 0.5f,
                      BR_UI_H - 42.0f, 26.0f, &BR_TEXT);
        return;
    }

    br_option_list_draw(&start->list, body, 28.0f,
                        &start->art->t_level_tile, &start->art->t_level_tile_active,
                        &BR_PANEL, &BR_ROW_SELECTED, &BR_TEXT, &BR_TEXT);

    br_hints_draw(hints, 3, body, OPTION_X, BR_UI_H - 42.0f, 26.0f, &BR_TEXT);
}
