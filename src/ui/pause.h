#ifndef BR_PAUSE_H
#define BR_PAUSE_H

#include "../platform/input.h"
#include "art.h"
#include "font.h"
#include "iconbar.h"

/* The pause screen. The original had nothing like it -- Android's back button
 * tore the whole Activity down -- so this is new. It doubles as the in-race
 * button reference, which is the moment anyone actually wants one. */

typedef enum {
    BR_PAUSE_NOTHING = 0,
    BR_PAUSE_RESUME,
    BR_PAUSE_RESTART,
    BR_PAUSE_MENU
} br_pause_action;

typedef struct {
    const br_ui_art *art;
    br_iconbar       bar;
    int              showing_controls;
} br_pause;

void br_pause_init(br_pause *pause, const br_ui_art *art);
void br_pause_open(br_pause *pause);

br_pause_action br_pause_update(br_pause *pause, const br_input *in, float dt);
void br_pause_draw(const br_pause *pause, const br_font *display,
                   const br_font *body, const char *subtitle);

#endif /* BR_PAUSE_H */
