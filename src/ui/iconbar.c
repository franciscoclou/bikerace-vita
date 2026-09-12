#include "iconbar.h"

#include <string.h>

#include "../engine/render.h"
#include "art.h"
#include "theme.h"
#include "uisound.h"

#define REPEAT_FIRST 0.32f
#define REPEAT_NEXT  0.12f
#define SELECT_GROW  1.16f
#define LABEL_GAP     8.0f

void br_iconbar_init(br_iconbar *bar, float centre_x, float y, float size, float gap)
{
    memset(bar, 0, sizeof(*bar));
    bar->centre_x = centre_x;
    bar->y = y;
    bar->size = size;
    bar->gap = gap;
    bar->touch_target = BR_ICON_NONE;
}

void br_iconbar_clear(br_iconbar *bar)
{
    bar->count = 0;
    bar->selected = 0;
}

void br_iconbar_add(br_iconbar *bar, const br_texture *icon, const char *label)
{
    if (bar->count >= BR_ICONBAR_MAX)
        return;
    bar->icons[bar->count] = icon;
    bar->labels[bar->count] = label;
    bar->count++;
}

int br_iconbar_rect(const br_iconbar *bar, int index, float *x, float *y, float *size)
{
    float row_w;

    if (index < 0 || index >= bar->count)
        return 0;
    row_w = (float)bar->count * bar->size + (float)(bar->count - 1) * bar->gap;
    *x = bar->centre_x - row_w * 0.5f + (float)index * (bar->size + bar->gap);
    *y = bar->y;
    *size = bar->size;
    return 1;
}

static int icon_at(const br_iconbar *bar, float px, float py)
{
    int i;

    for (i = 0; i < bar->count; i++) {
        float x, y, size;
        br_iconbar_rect(bar, i, &x, &y, &size);
        /* The label under each button is part of its target. */
        if (px >= x && px < x + size && py >= y && py < y + size + 26.0f)
            return i;
    }
    return BR_ICON_NONE;
}

static int repeated(br_iconbar *bar, const br_input *in, float dt)
{
    if (in->nav_x != bar->held_x) {
        bar->held_x = in->nav_x;
        bar->repeat_delay = REPEAT_FIRST;
        return in->nav_x != 0;
    }
    if (in->nav_x == 0)
        return 0;

    bar->repeat_delay -= dt;
    if (bar->repeat_delay <= 0.0f) {
        bar->repeat_delay = REPEAT_NEXT;
        return 1;
    }
    return 0;
}

int br_iconbar_update(br_iconbar *bar, const br_input *in, float dt)
{
    int chosen = BR_ICON_NONE;
    int was_selected;

    if (bar->count == 0)
        return BR_ICON_NONE;
    if (bar->selected >= bar->count)
        bar->selected = bar->count - 1;
    was_selected = bar->selected;

    if (repeated(bar, in, dt)) {
        bar->selected += in->nav_x;
        if (bar->selected < 0)           bar->selected = bar->count - 1;
        if (bar->selected >= bar->count) bar->selected = 0;
    }

    if (in->touch_active) {
        int target = icon_at(bar, in->touch_ui_x, in->touch_ui_y);

        if (in->touch_began)
            bar->touch_target = target;
        if (target >= 0 && target == bar->touch_target)
            bar->selected = target;
    } else if (in->touch_ended) {
        if (bar->touch_target >= 0 &&
            icon_at(bar, in->touch_ui_x, in->touch_ui_y) == bar->touch_target)
            chosen = bar->touch_target;
        bar->touch_target = BR_ICON_NONE;
    }

    if (in->confirm_pressed)
        chosen = bar->selected;

    if (bar->selected != was_selected)
        br_ui_click();
    if (chosen != BR_ICON_NONE)
        br_ui_click();
    return chosen;
}

void br_iconbar_draw(const br_iconbar *bar, const br_font *font, float label_size,
                     const br_color *label, const br_color *label_selected)
{
    int i;

    for (i = 0; i < bar->count; i++) {
        int selected = i == bar->selected;
        float x, y, size, grow, gx, gy, gs;

        br_iconbar_rect(bar, i, &x, &y, &size);

        /* The selected button steps forward rather than gaining a border, so
         * the round artwork keeps its own shape. */
        grow = selected ? SELECT_GROW : 1.0f;
        gs = size * grow;
        gx = x - (gs - size) * 0.5f;
        gy = y - (gs - size) * 0.5f;

        if (bar->icons[i] && br_ui_has(bar->icons[i])) {
            const br_image *img = bar->icons[i]->image;
            float fw = gs, fh = gs;

            if (img->width > 0 && img->height > 0) {
                float aspect = (float)img->width / (float)img->height;
                if (aspect > 1.0f) fh = gs / aspect;
                else               fw = gs * aspect;
            }
            br_draw_rect(gx + (gs - fw) * 0.5f, gy + (gs - fh) * 0.5f,
                         gx + (gs + fw) * 0.5f, gy + (gs + fh) * 0.5f,
                         bar->icons[i], NULL);
        } else
            br_fill_round_rect(gx, gy, gs, gs, gs * 0.22f,
                               selected ? &BR_ROW_SELECTED : &BR_ROW);

        if (bar->labels[i])
            br_font_draw_centered(font, bar->labels[i], x + size * 0.5f,
                                  y + size + LABEL_GAP, label_size,
                                  selected ? label_selected : label);
    }
}
