#ifndef BR_GLYPHS_H
#define BR_GLYPHS_H

#include "../engine/render.h"
#include "font.h"

/* Controller buttons drawn as shapes rather than spelled out, so a hint reads
 * the way the pad looks. Nothing in the APK has these -- it was a touch game
 * -- so they are drawn from primitives. */

typedef enum {
    BR_BUTTON_CROSS = 0,
    BR_BUTTON_CIRCLE,
    BR_BUTTON_TRIANGLE,
    BR_BUTTON_SQUARE,
    BR_BUTTON_START,
    BR_BUTTON_LTRIGGER,
    BR_BUTTON_RTRIGGER,
    BR_BUTTON_DPAD,
    BR_BUTTON_TOUCH
} br_button;

/* Draws one button, `size` tall, with its top-left at x, y. The Vita marks
 * START and the triggers with words rather than shapes, so those need a font;
 * pass NULL and they fall back to a plain plate. */
void  br_button_draw(br_button button, float x, float y, float size,
                     const br_font *font);
float br_button_width(br_button button, float size);

typedef struct {
    br_button   button;
    const char *label;
} br_hint;

/* A row of "button label" pairs. Returns the width it drew. */
float br_hints_draw(const br_hint *hints, int count, const br_font *font,
                    float x, float y, float size, const br_color *text);
float br_hints_width(const br_hint *hints, int count, const br_font *font,
                     float size);

#endif /* BR_GLYPHS_H */
