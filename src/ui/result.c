#include "result.h"

#include <stdio.h>
#include <string.h>

#include "../engine/render.h"
#include "glyphs.h"
#include "theme.h"

#define PANEL_W 560.0f
#define PANEL_H 392.0f
#define PANEL_Y  70.0f

#define OPTION_W 380.0f
#define OPTION_H  52.0f
#define OPTION_GAP  9.0f

#define STAR_SIZE 70.0f
#define STAR_GAP  14.0f

enum { ROW_NEXT = 0, ROW_REPEAT, ROW_MENU };

void br_result_init(br_result *result, const br_ui_art *art)
{
    memset(result, 0, sizeof(*result));
    result->art = art;
    br_option_list_init(&result->list, (BR_UI_W - OPTION_W) * 0.5f,
                        PANEL_Y + 218.0f, OPTION_W, OPTION_H, OPTION_GAP);
}

void br_result_open(br_result *result, int finished, int stars, float time,
                    float best_time, int is_record)
{
    result->finished = finished;
    result->stars = stars;
    result->time = time;
    result->best_time = best_time;
    result->is_record = is_record;

    br_option_list_clear(&result->list);
    br_option_list_add(&result->list, finished ? "Next level" : "Try again", NULL);
    br_option_list_add(&result->list, "Repeat this level", NULL);
    br_option_list_add(&result->list, "Level list", NULL);

    result->list.selected = 0;
    result->list.held_y = 0;
    result->list.repeat_delay = 0.0f;
    result->list.touch_target = BR_OPTION_NONE;
}

br_result_action br_result_update(br_result *result, const br_input *in, float dt)
{
    int chosen = br_option_list_update(&result->list, in, dt);

    /* The buttons shortcut the list, so nobody has to arrow to the obvious
     * choice: Cross carries on, Circle repeats, Start steps out. */
    if (in->back_pressed)
        return BR_RESULT_REPEAT;
    if (in->pause_pressed)
        return BR_RESULT_MENU;

    switch (chosen) {
    case ROW_NEXT:   return result->finished ? BR_RESULT_NEXT : BR_RESULT_REPEAT;
    case ROW_REPEAT: return BR_RESULT_REPEAT;
    case ROW_MENU:   return BR_RESULT_MENU;
    default:         return BR_RESULT_NOTHING;
    }
}

static void draw_stars(const br_result *result, float cy)
{
    float total = STAR_SIZE * 3.0f + STAR_GAP * 2.0f;
    float x = (BR_UI_W - total) * 0.5f;
    int i;

    for (i = 0; i < 3; i++) {
        int earned = i < result->stars;
        const br_texture *tex = earned ? &result->art->t_star_on
                                       : &result->art->t_star_off;
        float sx = x + (float)i * (STAR_SIZE + STAR_GAP);

        if (br_ui_has(tex))
            br_draw_rect(sx, cy, sx + STAR_SIZE, cy + STAR_SIZE, tex,
                         earned ? NULL : &BR_TEXT_DIM);
        else
            br_fill_rect(sx, cy, STAR_SIZE, STAR_SIZE,
                         earned ? &BR_HIGHLIGHT : &BR_TEXT_DIM);
    }
}

void br_result_draw(const br_result *result, const br_font *display,
                    const br_font *body, const char *subtitle)
{
    static const br_hint finished_hints[] = {
        { BR_BUTTON_CROSS,  "next" },
        { BR_BUTTON_CIRCLE, "repeat" },
        { BR_BUTTON_START,  "levels" },
    };
    static const br_hint crashed_hints[] = {
        { BR_BUTTON_CROSS,  "try again" },
        { BR_BUTTON_CIRCLE, "repeat" },
        { BR_BUTTON_START,  "levels" },
    };
    const br_hint *hints = result->finished ? finished_hints : crashed_hints;
    float x = (BR_UI_W - PANEL_W) * 0.5f;
    char text[96];

    br_ui_begin();
    br_fill_rect(0.0f, 0.0f, BR_UI_W, BR_UI_H, &BR_DIM);

    if (br_ui_has(&result->art->t_result))
        br_draw_rect(x, PANEL_Y, x + PANEL_W, PANEL_Y + PANEL_H,
                     &result->art->t_result, NULL);
    else
        br_fill_round_rect(x, PANEL_Y, PANEL_W, PANEL_H, 16.0f, &BR_PANEL);

    br_font_draw_centered(display, result->finished ? "COMPLETE" : "CRASHED",
                          BR_UI_W * 0.5f, PANEL_Y + 18.0f, 40.0f, &BR_INK);
    if (subtitle)
        br_font_draw_centered(body, subtitle, BR_UI_W * 0.5f, PANEL_Y + 62.0f,
                              22.0f, &BR_INK);

    if (result->finished) {
        draw_stars(result, PANEL_Y + 84.0f);

        snprintf(text, sizeof(text), "%.2fs", result->time);
        br_font_draw_centered(display, text, BR_UI_W * 0.5f, PANEL_Y + 162.0f,
                              32.0f, &BR_INK);

        if (result->is_record)
            br_font_draw_centered(body, "New best time", BR_UI_W * 0.5f,
                                  PANEL_Y + 196.0f, 21.0f, &BR_INK);
        else if (result->best_time > 0.0f) {
            snprintf(text, sizeof(text), "best %.2fs", result->best_time);
            br_font_draw_centered(body, text, BR_UI_W * 0.5f, PANEL_Y + 196.0f,
                                  21.0f, &BR_INK);
        }
    } else {
        br_font_draw_centered(body, "The rider came off.", BR_UI_W * 0.5f,
                              PANEL_Y + 132.0f, 26.0f, &BR_INK);
    }

    br_option_list_draw(&result->list, body, 26.0f, &BR_ROW, &BR_ROW_SELECTED,
                        &BR_INK);

    br_hints_draw(hints, 3, body,
                  (BR_UI_W - br_hints_width(hints, 3, body, 26.0f)) * 0.5f,
                  BR_UI_H - 42.0f, 26.0f, &BR_TEXT);
}
