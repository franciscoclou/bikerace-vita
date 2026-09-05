#ifndef BR_PAUSE_H
#define BR_PAUSE_H

#include "../platform/input.h"
#include "art.h"
#include "font.h"

/* The pause screen. The original had nothing like it -- Android's back button
 * dropped the whole Activity -- so this is new: resume, restart, or step back
 * out to the level list. */

typedef enum {
    BR_PAUSE_NOTHING = 0,
    BR_PAUSE_RESUME,
    BR_PAUSE_RESTART,
    BR_PAUSE_MENU
} br_pause_action;

typedef struct {
    const br_ui_art *art;
    int   selected;
    int   held_y;
    float repeat_delay;
    int   touch_target;
} br_pause;

void br_pause_init(br_pause *pause, const br_ui_art *art);
void br_pause_open(br_pause *pause);

br_pause_action br_pause_update(br_pause *pause, const br_input *in, float dt);
void br_pause_draw(const br_pause *pause, const br_font *display,
                   const br_font *body, const char *subtitle);

/* Where option `index` is drawn, so a caller can point at it. */
int  br_pause_option_rect(int index, float *x, float *y, float *w, float *h);

#endif /* BR_PAUSE_H */
