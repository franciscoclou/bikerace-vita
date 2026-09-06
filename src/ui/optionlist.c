#include "optionlist.h"

#include <string.h>

#include "../engine/render.h"

#define REPEAT_FIRST 0.32f
#define REPEAT_NEXT  0.12f
#define CORNER       10.0f
/* The level tile's bevel is about this many pixels of its own art. */
#define PLATE_BORDER 34.0f

void br_option_list_init(br_option_list *list, float x, float y,
                         float w, float h, float gap)
{
    memset(list, 0, sizeof(*list));
    list->x = x;
    list->y = y;
    list->w = w;
    list->h = h;
    list->gap = gap;
    list->touch_target = BR_OPTION_NONE;
}

void br_option_list_clear(br_option_list *list)
{
    list->count = 0;
}

void br_option_list_add(br_option_list *list, const char *label, const char *value)
{
    if (list->count >= BR_OPTION_LIST_MAX)
        return;
    list->labels[list->count] = label;
    list->values[list->count] = value;
    list->count++;
}

int br_option_list_rect(const br_option_list *list, int index,
                        float *x, float *y, float *w, float *h)
{
    if (index < 0 || index >= list->count)
        return 0;
    *x = list->x;
    *y = list->y + (float)index * (list->h + list->gap);
    *w = list->w;
    *h = list->h;
    return 1;
}

static int row_at(const br_option_list *list, float px, float py)
{
    int i;

    for (i = 0; i < list->count; i++) {
        float x, y, w, h;
        br_option_list_rect(list, i, &x, &y, &w, &h);
        if (px >= x && px < x + w && py >= y && py < y + h)
            return i;
    }
    return BR_OPTION_NONE;
}

static int repeated(br_option_list *list, const br_input *in, float dt)
{
    if (in->nav_y != list->held_y) {
        list->held_y = in->nav_y;
        list->repeat_delay = REPEAT_FIRST;
        return in->nav_y != 0;
    }
    if (in->nav_y == 0)
        return 0;

    list->repeat_delay -= dt;
    if (list->repeat_delay <= 0.0f) {
        list->repeat_delay = REPEAT_NEXT;
        return 1;
    }
    return 0;
}

int br_option_list_update(br_option_list *list, const br_input *in, float dt)
{
    int chosen = BR_OPTION_NONE;

    if (list->count == 0)
        return BR_OPTION_NONE;
    if (list->selected >= list->count)
        list->selected = list->count - 1;

    if (repeated(list, in, dt)) {
        list->selected += in->nav_y;
        if (list->selected < 0)             list->selected = list->count - 1;
        if (list->selected >= list->count)  list->selected = 0;
    }

    if (in->touch_active) {
        int target = row_at(list, in->touch_ui_x, in->touch_ui_y);

        if (in->touch_began)
            list->touch_target = target;
        if (target >= 0 && target == list->touch_target)
            list->selected = target;
    } else if (in->touch_ended) {
        if (list->touch_target >= 0 &&
            row_at(list, in->touch_ui_x, in->touch_ui_y) == list->touch_target)
            chosen = list->touch_target;
        list->touch_target = BR_OPTION_NONE;
    }

    if (in->confirm_pressed)
        chosen = list->selected;
    return chosen;
}

void br_option_list_draw(const br_option_list *list, const br_font *font,
                         float text_size,
                         const br_texture *plate, const br_texture *plate_selected,
                         const br_color *row, const br_color *row_selected,
                         const br_color *ink, const br_color *ink_selected)
{
    int i;

    for (i = 0; i < list->count; i++) {
        int selected = i == list->selected;
        const br_texture *art = selected ? plate_selected : plate;
        const br_color *text = selected ? ink_selected : ink;
        float x, y, w, h;

        br_option_list_rect(list, i, &x, &y, &w, &h);

        if (art && art->image)
            br_draw_nine(art, PLATE_BORDER, x, y, w, h, NULL);
        else
            br_fill_round_rect(x, y, w, h, CORNER, selected ? row_selected : row);

        if (list->values[i]) {
            br_font_draw(font, list->labels[i], x + 26.0f,
                         y + (h - text_size) * 0.5f, text_size, text);
            br_font_draw_right(font, list->values[i], x + w - 26.0f,
                               y + (h - text_size) * 0.5f, text_size, text);
        } else {
            br_font_draw_centered(font, list->labels[i], x + w * 0.5f,
                                  y + (h - text_size) * 0.5f, text_size, text);
        }
    }
}
