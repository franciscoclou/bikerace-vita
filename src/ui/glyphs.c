#include "glyphs.h"

#include <string.h>

#define STROKE_RATIO 0.10f
#define GAP_AFTER_GLYPH 7.0f
#define GAP_AFTER_LABEL 22.0f

/* The pad's own colours, so the shapes read at a glance. */
static const br_color FACE     = { 0.16f, 0.16f, 0.19f, 1.0f };
static const br_color CROSS    = { 0.55f, 0.72f, 0.95f, 1.0f };
static const br_color CIRCLE   = { 0.95f, 0.48f, 0.48f, 1.0f };
static const br_color TRIANGLE = { 0.50f, 0.90f, 0.68f, 1.0f };
static const br_color SQUARE   = { 0.95f, 0.62f, 0.85f, 1.0f };
static const br_color PLAIN    = { 0.93f, 0.92f, 0.90f, 1.0f };

static void draw_face(float cx, float cy, float radius)
{
    br_fill_circle(cx, cy, radius, &FACE);
}

void br_button_draw(br_button button, float x, float y, float size)
{
    float radius = size * 0.5f;
    float cx = x + radius, cy = y + radius;
    float stroke = size * STROKE_RATIO;
    float arm = size * 0.24f;

    switch (button) {
    case BR_BUTTON_CROSS:
        draw_face(cx, cy, radius);
        br_fill_line(cx - arm, cy - arm, cx + arm, cy + arm, stroke, &CROSS);
        br_fill_line(cx - arm, cy + arm, cx + arm, cy - arm, stroke, &CROSS);
        break;

    case BR_BUTTON_CIRCLE:
        draw_face(cx, cy, radius);
        br_stroke_circle(cx, cy, arm * 1.15f, stroke, &CIRCLE);
        break;

    case BR_BUTTON_TRIANGLE:
        draw_face(cx, cy, radius);
        br_fill_line(cx, cy - arm * 1.15f, cx + arm, cy + arm * 0.75f, stroke, &TRIANGLE);
        br_fill_line(cx + arm, cy + arm * 0.75f, cx - arm, cy + arm * 0.75f, stroke, &TRIANGLE);
        br_fill_line(cx - arm, cy + arm * 0.75f, cx, cy - arm * 1.15f, stroke, &TRIANGLE);
        break;

    case BR_BUTTON_SQUARE:
        draw_face(cx, cy, radius);
        br_fill_line(cx - arm, cy - arm, cx + arm, cy - arm, stroke, &SQUARE);
        br_fill_line(cx + arm, cy - arm, cx + arm, cy + arm, stroke, &SQUARE);
        br_fill_line(cx + arm, cy + arm, cx - arm, cy + arm, stroke, &SQUARE);
        br_fill_line(cx - arm, cy + arm, cx - arm, cy - arm, stroke, &SQUARE);
        break;

    case BR_BUTTON_START: {
        /* The Vita prints Start as a bar with a triangle beside it. */
        float w = size * 1.45f;
        br_fill_round_rect(x, y + size * 0.12f, w, size * 0.76f, size * 0.24f, &FACE);
        br_fill_rect(x + w * 0.24f, cy - arm * 0.8f, stroke, arm * 1.6f, &PLAIN);
        br_fill_line(x + w * 0.46f, cy - arm * 0.8f, x + w * 0.72f, cy, stroke, &PLAIN);
        br_fill_line(x + w * 0.72f, cy, x + w * 0.46f, cy + arm * 0.8f, stroke, &PLAIN);
        br_fill_line(x + w * 0.46f, cy - arm * 0.8f, x + w * 0.46f, cy + arm * 0.8f,
                     stroke, &PLAIN);
        break;
    }

    case BR_BUTTON_DPAD: {
        float bar = size * 0.30f;
        br_fill_round_rect(cx - bar * 0.5f, y + size * 0.10f, bar, size * 0.80f,
                           bar * 0.3f, &FACE);
        br_fill_round_rect(x + size * 0.10f, cy - bar * 0.5f, size * 0.80f, bar,
                           bar * 0.3f, &FACE);
        break;
    }

    case BR_BUTTON_TOUCH:
        br_fill_round_rect(x, y + size * 0.16f, size * 1.30f, size * 0.68f,
                           size * 0.14f, &FACE);
        br_fill_circle(x + size * 0.65f, cy, size * 0.13f, &PLAIN);
        break;
    }
}

float br_button_width(br_button button, float size)
{
    switch (button) {
    case BR_BUTTON_START: return size * 1.45f;
    case BR_BUTTON_TOUCH: return size * 1.30f;
    default:              return size;
    }
}

float br_hints_width(const br_hint *hints, int count, const br_font *font,
                     float size)
{
    float width = 0.0f;
    int i;

    for (i = 0; i < count; i++) {
        width += br_button_width(hints[i].button, size) + GAP_AFTER_GLYPH;
        width += br_font_width(font, hints[i].label, size * 0.86f);
        if (i + 1 < count)
            width += GAP_AFTER_LABEL;
    }
    return width;
}

float br_hints_draw(const br_hint *hints, int count, const br_font *font,
                    float x, float y, float size, const br_color *text)
{
    float start = x;
    int i;

    for (i = 0; i < count; i++) {
        float label_size = size * 0.86f;

        br_button_draw(hints[i].button, x, y, size);
        x += br_button_width(hints[i].button, size) + GAP_AFTER_GLYPH;

        /* Sit the label on the glyph's centre line. */
        br_font_draw(font, hints[i].label, x, y + (size - label_size) * 0.5f,
                     label_size, text);
        x += br_font_width(font, hints[i].label, label_size);
        if (i + 1 < count)
            x += GAP_AFTER_LABEL;
    }
    return x - start;
}
