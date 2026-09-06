#ifndef BR_ICONBAR_H
#define BR_ICONBAR_H

#include "../engine/texture.h"
#include "../platform/input.h"
#include "font.h"

/* A row of the game's own round buttons with a label under each.
 *
 * This is how the original's dialogs offered their choices, and it suits the
 * short, obvious sets -- retry, next, level list -- better than a list of
 * sentences. Screens with longer or wordier choices use ui/optionlist.c
 * instead; both share the same navigation contract. */

#define BR_ICONBAR_MAX 5
#define BR_ICON_NONE (-1)

typedef struct {
    const br_texture *icons[BR_ICONBAR_MAX];
    const char       *labels[BR_ICONBAR_MAX];
    int   count;

    int   selected;
    int   held_x;
    float repeat_delay;
    int   touch_target;

    float centre_x, y, size, gap;
} br_iconbar;

void br_iconbar_init(br_iconbar *bar, float centre_x, float y, float size, float gap);
void br_iconbar_clear(br_iconbar *bar);
void br_iconbar_add(br_iconbar *bar, const br_texture *icon, const char *label);

int  br_iconbar_update(br_iconbar *bar, const br_input *in, float dt);
void br_iconbar_draw(const br_iconbar *bar, const br_font *font, float label_size,
                     const br_color *label, const br_color *label_selected);
int  br_iconbar_rect(const br_iconbar *bar, int index,
                     float *x, float *y, float *size);

#endif /* BR_ICONBAR_H */
