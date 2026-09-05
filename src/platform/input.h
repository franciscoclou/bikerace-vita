#ifndef BR_INPUT_H
#define BR_INPUT_H

#include <stdint.h>

/* The Android original drove the bike with two on-screen halves (left half =
 * brake/lean back, right half = accelerate/lean forward) plus an accelerometer
 * tilt mode. On the Vita we map:
 *
 *   accelerate  -> R trigger / Cross / right half of the front touchscreen
 *   brake       -> L trigger / Square / left half of the front touchscreen
 *   lean        -> left stick X (also the accelerometer via sceMotion)
 *   pause       -> Start
 *   back        -> Circle
 */

typedef struct {
    int   accelerate;   /* held this frame */
    int   brake;
    float lean;         /* -1 .. 1, negative = lean back */

    int   pause_pressed;   /* edge-triggered */
    int   back_pressed;
    int   confirm_pressed;

    int   touch_count;
    float touch_x[2];   /* 0..1 across the screen */
    float touch_y[2];

    uint32_t buttons;      /* raw SceCtrlData.buttons */
    uint32_t buttons_prev;
} br_input;

void br_input_init(void);
void br_input_poll(br_input *in);

#endif /* BR_INPUT_H */
