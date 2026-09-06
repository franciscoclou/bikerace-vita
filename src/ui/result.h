#ifndef BR_RESULT_H
#define BR_RESULT_H

#include "../platform/input.h"
#include "art.h"
#include "font.h"
#include "iconbar.h"

/* What comes up when a run ends, finished or crashed. The original handed this
 * to an Android dialog, so the panel art carried over but nothing else did. */

typedef enum {
    BR_RESULT_NOTHING = 0,
    BR_RESULT_NEXT,
    BR_RESULT_REPEAT,
    BR_RESULT_MENU
} br_result_action;

typedef struct {
    const br_ui_art *art;
    br_iconbar       bar;
    int   finished;      /* false means the rider crashed */
    int   has_next;      /* a next level exists and is unlocked */
    int   stars;
    float time;
    float best_time;     /* 0 when there is no record yet */
    int   is_record;
} br_result;

void br_result_init(br_result *result, const br_ui_art *art);
void br_result_open(br_result *result, int finished, int has_next, int stars,
                    float time, float best_time, int is_record);

br_result_action br_result_update(br_result *result, const br_input *in, float dt);
void br_result_draw(const br_result *result, const br_font *display,
                    const br_font *body, const char *subtitle);

#endif /* BR_RESULT_H */
