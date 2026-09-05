#include "pause.h"

#include <string.h>

#include "../engine/render.h"

#define SCREEN_W 960.0f
#define SCREEN_H 544.0f

#define OPTION_COUNT 3
#define OPTION_W     360.0f
#define OPTION_H      62.0f
#define OPTION_GAP    14.0f
#define OPTION_TOP   214.0f

#define PANEL_W 470.0f
#define PANEL_H 330.0f

#define REPEAT_FIRST 0.32f
#define REPEAT_NEXT  0.12f

static const char *s_options[OPTION_COUNT] = { "Resume", "Restart", "Level list" };

static const br_color DIM       = { 0.0f, 0.0f, 0.0f, 0.62f };
static const br_color PANEL     = { 0.10f * 0.94f, 0.08f * 0.94f, 0.06f * 0.94f, 0.94f };
static const br_color ROW       = { 0.20f * 0.30f, 0.13f * 0.30f, 0.07f * 0.30f, 0.30f };
static const br_color ROW_SEL   = { 1.00f, 0.72f, 0.16f, 1.00f };
static const br_color INK       = { 0.28f, 0.15f, 0.06f, 1.00f };
static const br_color TEXT_DIM  = { 0.74f, 0.71f, 0.66f, 1.00f };

int br_pause_option_rect(int index, float *x, float *y, float *w, float *h)
{
    if (index < 0 || index >= OPTION_COUNT)
        return 0;
    *w = OPTION_W;
    *h = OPTION_H;
    *x = (SCREEN_W - OPTION_W) * 0.5f;
    *y = OPTION_TOP + (float)index * (OPTION_H + OPTION_GAP);
    return 1;
}

void br_pause_init(br_pause *pause, const br_ui_art *art)
{
    memset(pause, 0, sizeof(*pause));
    pause->art = art;
    pause->touch_target = -1;
}

void br_pause_open(br_pause *pause)
{
    pause->selected = 0;
    pause->held_y = 0;
    pause->repeat_delay = 0.0f;
    pause->touch_target = -1;
}

static int option_at(float px, float py)
{
    int i;

    for (i = 0; i < OPTION_COUNT; i++) {
        float x, y, w, h;
        br_pause_option_rect(i, &x, &y, &w, &h);
        if (px >= x && px < x + w && py >= y && py < y + h)
            return i;
    }
    return -1;
}

static int repeated(br_pause *pause, const br_input *in, float dt)
{
    if (in->nav_y != pause->held_y) {
        pause->held_y = in->nav_y;
        pause->repeat_delay = REPEAT_FIRST;
        return in->nav_y != 0;
    }
    if (in->nav_y == 0)
        return 0;

    pause->repeat_delay -= dt;
    if (pause->repeat_delay <= 0.0f) {
        pause->repeat_delay = REPEAT_NEXT;
        return 1;
    }
    return 0;
}

br_pause_action br_pause_update(br_pause *pause, const br_input *in, float dt)
{
    int commit = 0;

    if (repeated(pause, in, dt)) {
        pause->selected += in->nav_y;
        if (pause->selected < 0)             pause->selected = OPTION_COUNT - 1;
        if (pause->selected >= OPTION_COUNT) pause->selected = 0;
    }

    if (in->touch_active) {
        int target = option_at(in->touch_ui_x, in->touch_ui_y);

        if (in->touch_began)
            pause->touch_target = target;
        if (target >= 0 && target == pause->touch_target)
            pause->selected = target;
    } else if (in->touch_ended) {
        if (option_at(in->touch_ui_x, in->touch_ui_y) == pause->touch_target &&
            pause->touch_target >= 0)
            commit = 1;
        pause->touch_target = -1;
    }

    if (in->confirm_pressed)
        commit = 1;
    /* Circle and Start both back out, which is what both do everywhere else. */
    if (in->back_pressed || in->pause_pressed)
        return BR_PAUSE_RESUME;

    if (!commit)
        return BR_PAUSE_NOTHING;

    switch (pause->selected) {
    case 1:  return BR_PAUSE_RESTART;
    case 2:  return BR_PAUSE_MENU;
    default: return BR_PAUSE_RESUME;
    }
}

void br_pause_draw(const br_pause *pause, const br_font *display,
                   const br_font *body, const char *subtitle)
{
    float panel_x = (SCREEN_W - PANEL_W) * 0.5f;
    float panel_y = 108.0f;
    int i;

    br_ui_begin();
    br_fill_rect(0.0f, 0.0f, SCREEN_W, SCREEN_H, &DIM);

    if (br_ui_has(&pause->art->t_panel))
        br_draw_rect(panel_x, panel_y, panel_x + PANEL_W, panel_y + PANEL_H,
                     &pause->art->t_panel, NULL);
    else
        br_fill_rect(panel_x, panel_y, PANEL_W, PANEL_H, &PANEL);

    br_font_draw_centered(display, "PAUSED", SCREEN_W * 0.5f, panel_y + 26.0f,
                          40.0f, &INK);
    if (subtitle)
        br_font_draw_centered(body, subtitle, SCREEN_W * 0.5f, panel_y + 72.0f,
                              24.0f, &INK);

    for (i = 0; i < OPTION_COUNT; i++) {
        int selected = i == pause->selected;
        float x, y, w, h;

        br_pause_option_rect(i, &x, &y, &w, &h);
        br_fill_rect(x, y, w, h, selected ? &ROW_SEL : &ROW);
        /* Both row colours are light against the paper panel, so the label
         * stays dark either way. */
        br_font_draw_centered(body, s_options[i], x + w * 0.5f, y + 16.0f,
                              30.0f, &INK);
    }

    br_font_draw_centered(body, "Cross or tap  choose      Start  resume",
                          SCREEN_W * 0.5f, SCREEN_H - 40.0f, 22.0f, &TEXT_DIM);
}
