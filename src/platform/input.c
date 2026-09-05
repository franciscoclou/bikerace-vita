#include "input.h"

#include <psp2/ctrl.h>
#include <psp2/touch.h>

#include <string.h>

#define TOUCH_MAX_X 1919.0f  /* front panel reports 1920x1088 */
#define TOUCH_MAX_Y 1087.0f
#define STICK_DEADZONE 24

void br_input_init(void)
{
    sceCtrlSetSamplingMode(SCE_CTRL_MODE_ANALOG_WIDE);
    sceTouchSetSamplingState(SCE_TOUCH_PORT_FRONT, SCE_TOUCH_SAMPLING_STATE_START);
    sceTouchEnableTouchForce(SCE_TOUCH_PORT_FRONT);
}

static float stick_axis(unsigned char raw)
{
    int v = (int)raw - 128;
    if (v > -STICK_DEADZONE && v < STICK_DEADZONE)
        return 0.0f;
    return (float)v / 127.0f;
}

void br_input_poll(br_input *in)
{
    SceCtrlData pad;
    SceTouchData touch;
    int i;

    in->buttons_prev = in->buttons;

    memset(&pad, 0, sizeof(pad));
    sceCtrlPeekBufferPositive(0, &pad, 1);
    in->buttons = pad.buttons;

    memset(&touch, 0, sizeof(touch));
    sceTouchPeek(SCE_TOUCH_PORT_FRONT, &touch, 1);

    in->touch_count = touch.reportNum > 2 ? 2 : touch.reportNum;
    for (i = 0; i < in->touch_count; i++) {
        in->touch_x[i] = (float)touch.report[i].x / TOUCH_MAX_X;
        in->touch_y[i] = (float)touch.report[i].y / TOUCH_MAX_Y;
    }

    in->accelerate = (pad.buttons & (SCE_CTRL_RTRIGGER | SCE_CTRL_CROSS)) != 0;
    in->brake      = (pad.buttons & (SCE_CTRL_LTRIGGER | SCE_CTRL_SQUARE)) != 0;

    /* Touch halves mirror the Android control scheme. */
    for (i = 0; i < in->touch_count; i++) {
        if (in->touch_x[i] >= 0.5f)
            in->accelerate = 1;
        else
            in->brake = 1;
    }

    in->lean = stick_axis(pad.lx);
    if (in->lean == 0.0f) {
        if (pad.buttons & SCE_CTRL_LEFT)  in->lean = -1.0f;
        if (pad.buttons & SCE_CTRL_RIGHT) in->lean =  1.0f;
    }

    {
        uint32_t pressed = in->buttons & ~in->buttons_prev;
        in->pause_pressed   = (pressed & SCE_CTRL_START)  != 0;
        in->back_pressed    = (pressed & SCE_CTRL_CIRCLE) != 0;
        in->confirm_pressed = (pressed & SCE_CTRL_CROSS)  != 0;
    }
}
