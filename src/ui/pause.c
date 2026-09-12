#include "pause.h"

#include <string.h>

#include "../engine/render.h"
#include "controls.h"
#include "glyphs.h"
#include "theme.h"

#define PANEL_W 620.0f
#define PANEL_H 274.0f
#define PANEL_Y 128.0f
#define CONTROLS_PANEL_H 396.0f
#define CONTROLS_PANEL_Y  70.0f

#define ICON_SIZE 78.0f
#define ICON_GAP  30.0f

void br_pause_init(br_pause *pause, const br_ui_art *art)
{
    memset(pause, 0, sizeof(*pause));
    pause->art = art;
    (void)art;
}

void br_pause_open(br_pause *pause, int ghost, int ghost_on)
{
    const br_ui_art *art = pause->art;

    pause->showing_controls = 0;
    pause->has_ghost = ghost;

    br_iconbar_init(&pause->bar, BR_UI_W * 0.5f, PANEL_Y + 132.0f,
                    ICON_SIZE, ICON_GAP);
    br_iconbar_add(&pause->bar, &art->t_icon_play, "Resume");
    br_iconbar_add(&pause->bar, &art->t_icon_retry, "Restart");
    br_iconbar_add(&pause->bar, &art->t_icon_controls, "Controls");
    if (ghost)
        br_iconbar_add(&pause->bar, ghost_on ? &art->t_toggle_on
                                             : &art->t_toggle_off, "Ghost");
    br_iconbar_add(&pause->bar, &art->t_icon_list, "Levels");
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

    if (chosen < 0)
        return BR_PAUSE_NOTHING;
    switch (chosen) {
    case 0: return BR_PAUSE_RESUME;
    case 1: return BR_PAUSE_RESTART;
    case 2: pause->showing_controls = 1; return BR_PAUSE_NOTHING;
    default:
        /* The ghost switch only exists when there is a run to hide. */
        if (pause->has_ghost && chosen == 3)
            return BR_PAUSE_TOGGLE_GHOST;
        return BR_PAUSE_MENU;
    }
}

void br_pause_draw(const br_pause *pause, const br_font *display,
                   const br_font *body, const char *subtitle)
{
    static const br_hint back[] = { { BR_BUTTON_CIRCLE, "back" } };
    /* The control list needs a row per button, so the panel grows to hold it
     * rather than the list spilling out of the bottom. */
    float h = pause->showing_controls ? CONTROLS_PANEL_H : PANEL_H;
    float y = pause->showing_controls ? CONTROLS_PANEL_Y : PANEL_Y;
    float x = (BR_UI_W - PANEL_W) * 0.5f;

    br_ui_begin();
    br_fill_screen(&BR_DIM);
    br_ui_panel(pause->art, x, y, PANEL_W, h);

    br_font_draw_centered(display, pause->showing_controls ? "CONTROLS" : "PAUSED",
                          BR_UI_W * 0.5f, y + 16.0f, 38.0f, &BR_INK);

    if (pause->showing_controls) {
        float rows = (float)br_controls_count(BR_CONTROLS_RACE);
        float row_h = 34.0f;
        float top = y + 74.0f;

        /* Centre the block in whatever room is left below the title. */
        top += (h - 74.0f - 48.0f - rows * row_h) * 0.5f;
        br_controls_draw(BR_CONTROLS_RACE, pause->art, body, x + 48.0f, top,
                         row_h, &BR_INK);
        br_hints_draw(back, 1, body,
                      (BR_UI_W - br_hints_width(back, 1, body, 26.0f)) * 0.5f,
                      y + h - 44.0f, 26.0f, &BR_INK);
        return;
    }

    if (subtitle)
        br_font_draw_centered(body, subtitle, BR_UI_W * 0.5f, y + 58.0f,
                              22.0f, &BR_INK);
    br_iconbar_draw(&pause->bar, body, 22.0f, &BR_INK, &BR_INK);
}
