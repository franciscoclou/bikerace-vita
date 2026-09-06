#include "result.h"

#include <stdio.h>
#include <string.h>

#include "../engine/render.h"
#include "glyphs.h"
#include "theme.h"

#define PANEL_W 570.0f
#define PANEL_H 400.0f
#define PANEL_Y  62.0f

#define ICON_SIZE 84.0f
#define ICON_GAP  40.0f

#define STAR_SIZE 68.0f
#define STAR_GAP  16.0f

void br_result_init(br_result *result, const br_ui_art *art)
{
    memset(result, 0, sizeof(*result));
    result->art = art;
    br_iconbar_init(&result->bar, BR_UI_W * 0.5f, PANEL_Y + 258.0f,
                    ICON_SIZE, ICON_GAP);
}

void br_result_open(br_result *result, int finished, int stars, float time,
                    float best_time, int is_record)
{
    result->finished = finished;
    result->stars = stars;
    result->time = time;
    result->best_time = best_time;
    result->is_record = is_record;

    /* After a crash there is no next level, so offering both "try again" and
     * "repeat" would be the same button twice. */
    br_iconbar_clear(&result->bar);
    if (finished)
        br_iconbar_add(&result->bar, &result->art->t_icon_next, "Next");
    br_iconbar_add(&result->bar, &result->art->t_icon_retry,
                   finished ? "Repeat" : "Try again");
    br_iconbar_add(&result->bar, &result->art->t_icon_list, "Levels");

    result->bar.selected = 0;
    result->bar.held_x = 0;
    result->bar.repeat_delay = 0.0f;
    result->bar.touch_target = BR_ICON_NONE;
}

br_result_action br_result_update(br_result *result, const br_input *in, float dt)
{
    int chosen = br_iconbar_update(&result->bar, in, dt);

    /* The buttons shortcut the row, so nobody has to arrow to the obvious
     * choice: Circle tries again, Start steps out to the level list. */
    if (in->back_pressed)
        return BR_RESULT_REPEAT;
    if (in->pause_pressed)
        return BR_RESULT_MENU;

    if (chosen < 0)
        return BR_RESULT_NOTHING;
    if (!result->finished)
        chosen++;              /* the rows shift up without a "next" */
    return chosen == 0 ? BR_RESULT_NEXT
                       : (chosen == 1 ? BR_RESULT_REPEAT : BR_RESULT_MENU);
}

static void draw_stars(const br_result *result, float y)
{
    float total = STAR_SIZE * 3.0f + STAR_GAP * 2.0f;
    float x = (BR_UI_W - total) * 0.5f;
    int i;

    for (i = 0; i < 3; i++) {
        int earned = i < result->stars;
        const br_texture *tex = earned ? &result->art->t_star_on
                                       : &result->art->t_star_off;
        float sx = x + (float)i * (STAR_SIZE + STAR_GAP);
        /* Earned stars sit a little proud of the empty ones. */
        float lift = earned ? 6.0f : 0.0f;

        if (br_ui_has(tex))
            br_draw_rect(sx, y - lift, sx + STAR_SIZE, y + STAR_SIZE - lift, tex,
                         earned ? NULL : &BR_TEXT_DIM);
        else
            br_fill_rect(sx, y - lift, STAR_SIZE, STAR_SIZE,
                         earned ? &BR_HIGHLIGHT : &BR_TEXT_DIM);
    }
}

void br_result_draw(const br_result *result, const br_font *display,
                    const br_font *body, const char *subtitle)
{
    float x = (BR_UI_W - PANEL_W) * 0.5f;
    char text[96];

    br_ui_begin();
    br_fill_rect(0.0f, 0.0f, BR_UI_W, BR_UI_H, &BR_DIM);

    /* The result window has its own tyre-marked paper; it only wants the frame. */
    br_ui_panel(result->art, x, PANEL_Y, PANEL_W, PANEL_H);
    if (br_ui_has(&result->art->t_result))
        br_draw_rect(x, PANEL_Y, x + PANEL_W, PANEL_Y + PANEL_H,
                     &result->art->t_result, NULL);

    br_font_draw_centered(display, result->finished ? "COMPLETE" : "CRASHED",
                          BR_UI_W * 0.5f, PANEL_Y + 16.0f, 38.0f, &BR_INK);
    if (subtitle)
        br_font_draw_centered(body, subtitle, BR_UI_W * 0.5f, PANEL_Y + 58.0f,
                              22.0f, &BR_INK);

    if (result->finished) {
        draw_stars(result, PANEL_Y + 108.0f);

        snprintf(text, sizeof(text), "%.2fs", result->time);
        br_font_draw_centered(display, text, BR_UI_W * 0.5f, PANEL_Y + 186.0f,
                              34.0f, &BR_INK);

        if (result->is_record)
            br_font_draw_centered(body, "New best time", BR_UI_W * 0.5f,
                                  PANEL_Y + 224.0f, 21.0f, &BR_INK);
        else if (result->best_time > 0.0f) {
            snprintf(text, sizeof(text), "best %.2fs", result->best_time);
            br_font_draw_centered(body, text, BR_UI_W * 0.5f, PANEL_Y + 224.0f,
                                  21.0f, &BR_INK);
        }
    } else {
        br_font_draw_centered(body, "The rider came off.", BR_UI_W * 0.5f,
                              PANEL_Y + 150.0f, 26.0f, &BR_INK);
        snprintf(text, sizeof(text), "%.2fs before the crash", result->time);
        br_font_draw_centered(body, text, BR_UI_W * 0.5f, PANEL_Y + 190.0f,
                              21.0f, &BR_INK);
    }

    br_iconbar_draw(&result->bar, body, 22.0f, &BR_INK, &BR_INK);
}
