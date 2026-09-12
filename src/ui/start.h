#ifndef BR_START_H
#define BR_START_H

#include "../platform/input.h"
#include "art.h"
#include "font.h"
#include "optionlist.h"

/* The screen the game opens on, over the artwork the APK ships. Multiplayer
 * and the shop are not ported, so the only ways on are single player,
 * settings, and out.
 *
 * Out asks first. Every destructive thing in settings does, and quitting from
 * the third row of a three-row list is one stray Cross away. */

typedef enum {
    BR_START_NOTHING = 0,
    BR_START_SINGLE_PLAYER,
    BR_START_SETTINGS,
    BR_START_EXIT
} br_start_action;

typedef struct {
    const br_ui_art *art;
    br_option_list   list;

    int              confirming_exit;
    br_option_list   confirm_list;
} br_start;

void br_start_init(br_start *start, const br_ui_art *art);
void br_start_open(br_start *start);
br_start_action br_start_update(br_start *start, const br_input *in, float dt);
void br_start_draw(const br_start *start, const br_font *display,
                   const br_font *body);
/* Just the artwork and logo, for screens that sit on top of it. */
void br_start_draw_backdrop(const br_start *start, const br_font *display,
                            const br_font *body);

#endif /* BR_START_H */
