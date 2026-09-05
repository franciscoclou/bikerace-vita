#ifndef BR_INPUT_H
#define BR_INPUT_H

#include <stdint.h>

/* The Android original drove the bike with two on-screen halves (left half =
 * brake, right half = accelerate) plus an accelerometer tilt mode. On the Vita:
 *
 *   accelerate    -> Cross, or the right half of the front touchscreen
 *   brake, then
 *   reverse       -> Square, or the left half of the front touchscreen
 *   lean forward  -> R trigger, left stick right, or d-pad right
 *   lean back     -> L trigger, left stick left, or d-pad left
 *   reset level   -> Circle
 *   pause         -> Start
 *   quit          -> Start + Select
 *
 * Menus take the d-pad and the touchscreen both.
 */

typedef struct {
    int   accelerate;   /* held this frame */
    int   brake;
    /* -1 .. 1 along the stick: +1 is right, which leans the bike forward. */
    float lean;

    int   pause_pressed;   /* edge-triggered */
    int   back_pressed;
    int   confirm_pressed;
    int   bikes_pressed;   /* Triangle */

    /* Held menu direction from the d-pad or the left stick, -1/0/+1.
     * Auto-repeat is the menu's business, since it needs the frame time. */
    int   nav_x, nav_y;

    int   touch_count;
    float touch_x[2];   /* 0..1 across the screen */
    float touch_y[2];

    /* The first touch, in the 960x544 space menus lay out in. Position is
     * held over the frame the touch ends, so a release can be hit-tested. */
    int   touch_active;
    int   touch_began;
    int   touch_ended;
    float touch_ui_x, touch_ui_y;

    uint32_t buttons;      /* raw SceCtrlData.buttons */
    uint32_t buttons_prev;
} br_input;

void br_input_init(void);
void br_input_poll(br_input *in);

#endif /* BR_INPUT_H */
