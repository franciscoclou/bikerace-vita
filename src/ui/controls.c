#include "controls.h"

#include "../platform/device.h"
#include "glyphs.h"

/* `race` marks the ones worth showing mid-level; everything is listed in
 * settings. `touch` marks the rows that describe a panel the PlayStation TV
 * does not have -- they are left out there rather than telling someone to tap
 * a television. Nothing is lost: every one of them has a button beside it in
 * this same list. */
static const struct {
    br_button   button;
    const char *what;
    int         race;
    int         touch;
} s_controls[] = {
    { BR_BUTTON_CROSS,    "Accelerate",                    1, 0 },
    { BR_BUTTON_SQUARE,   "Brake, hold to reverse",        1, 0 },
    { BR_BUTTON_RTRIGGER, "Lean forward",                  1, 0 },
    { BR_BUTTON_LTRIGGER, "Lean back",                     1, 0 },
    { BR_BUTTON_CIRCLE,   "Reset the level",               1, 0 },
    { BR_BUTTON_START,    "Pause",                         1, 0 },
    { BR_BUTTON_TOUCH,    "Left half brakes, right half accelerates", 1, 1 },
    { BR_BUTTON_CROSS,    "Choose, in the menus",          0, 0 },
    { BR_BUTTON_CIRCLE,   "Back, in the menus",            0, 0 },
    { BR_BUTTON_TRIANGLE, "Bike list, from the menus",     0, 0 },
    { BR_BUTTON_DPAD,     "Move around the menus",         0, 0 },
    { BR_BUTTON_TOUCH,    "Tap anything in the menus",     0, 1 },
};
#define CONTROL_COUNT ((int)(sizeof(s_controls) / sizeof(s_controls[0])))

static int in_scope(int index, br_controls_scope scope)
{
    if (s_controls[index].touch && br_device_is_tv())
        return 0;
    return scope == BR_CONTROLS_ALL || s_controls[index].race;
}

int br_controls_count(br_controls_scope scope)
{
    int i, n = 0;

    for (i = 0; i < CONTROL_COUNT; i++)
        if (in_scope(i, scope))
            n++;
    return n;
}

float br_controls_draw(br_controls_scope scope, const br_ui_art *art,
                       const br_font *font, float x, float y, float row_height,
                       const br_color *ink)
{
    float glyph = row_height * 0.82f;
    float text_size = row_height * 0.62f;
    float column = 0.0f;
    float row = 0.0f;
    int i;

    (void)art;

    /* One label column for the whole list, past the widest button in it. */
    for (i = 0; i < CONTROL_COUNT; i++) {
        float width;
        if (!in_scope(i, scope))
            continue;
        width = br_button_width(s_controls[i].button, glyph);
        if (width > column)
            column = width;
    }
    column += glyph * 0.5f;

    for (i = 0; i < CONTROL_COUNT; i++) {
        float row_y;

        if (!in_scope(i, scope))
            continue;
        row_y = y + row * row_height;
        br_button_draw(s_controls[i].button, x, row_y, glyph, font);
        br_font_draw(font, s_controls[i].what, x + column,
                     row_y + (glyph - text_size) * 0.5f, text_size, ink);
        row += 1.0f;
    }
    return row * row_height;
}
