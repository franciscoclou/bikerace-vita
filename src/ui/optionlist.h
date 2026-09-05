#ifndef BR_OPTION_LIST_H
#define BR_OPTION_LIST_H

#include "../platform/input.h"
#include "font.h"

/* A vertical list of rows driven by the d-pad, the stick or touch.
 *
 * The pause screen, the start screen and the settings screen are all this
 * shape, so they share it rather than each growing their own copy of the
 * navigation and hit-testing. */

#define BR_OPTION_LIST_MAX 8
#define BR_OPTION_NONE (-1)

typedef struct {
    const char *labels[BR_OPTION_LIST_MAX];
    /* Optional right-hand text per row, for settings values. */
    const char *values[BR_OPTION_LIST_MAX];
    int   count;

    int   selected;
    int   held_y;
    float repeat_delay;
    int   touch_target;

    float x, y, w, h, gap;
} br_option_list;

void br_option_list_init(br_option_list *list, float x, float y,
                         float w, float h, float gap);
void br_option_list_clear(br_option_list *list);
void br_option_list_add(br_option_list *list, const char *label, const char *value);

/* Returns the chosen row, or BR_OPTION_NONE. */
int  br_option_list_update(br_option_list *list, const br_input *in, float dt);
void br_option_list_draw(const br_option_list *list, const br_font *font,
                         float text_size, const br_color *row,
                         const br_color *row_selected, const br_color *ink);
int  br_option_list_rect(const br_option_list *list, int index,
                         float *x, float *y, float *w, float *h);

#endif /* BR_OPTION_LIST_H */
