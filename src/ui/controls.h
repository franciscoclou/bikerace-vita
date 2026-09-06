#ifndef BR_CONTROLS_H
#define BR_CONTROLS_H

#include "art.h"
#include "font.h"

/* The button reference, in one place because two screens show it: settings
 * shows everything, and pause shows only what matters with a bike under you. */

typedef enum {
    BR_CONTROLS_ALL = 0,
    BR_CONTROLS_RACE
} br_controls_scope;

/* Draws the list into a column starting at x, y. Returns the height used. */
float br_controls_draw(br_controls_scope scope, const br_ui_art *art,
                       const br_font *font, float x, float y, float row_height,
                       const br_color *ink);
int   br_controls_count(br_controls_scope scope);

#endif /* BR_CONTROLS_H */
