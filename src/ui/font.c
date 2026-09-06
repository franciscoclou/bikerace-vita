#include "font.h"

#include <stdlib.h>
#include <string.h>

#include "../platform/log.h"

/* One draw call per string, so a menu full of text is a handful of calls. */
#define MAX_GLYPHS_PER_CALL 192
#define VERTS_PER_GLYPH     6

static float s_xy[MAX_GLYPHS_PER_CALL * VERTS_PER_GLYPH * 2];
static float s_uv[MAX_GLYPHS_PER_CALL * VERTS_PER_GLYPH * 2];

static unsigned read_u32(const unsigned char *p)
{
    return (unsigned)p[0] | ((unsigned)p[1] << 8) |
           ((unsigned)p[2] << 16) | ((unsigned)p[3] << 24);
}

static short read_i16(const unsigned char *p)
{
    return (short)((unsigned short)p[0] | ((unsigned short)p[1] << 8));
}

int br_font_load(br_font *font, const unsigned char *data, unsigned size)
{
    const unsigned char *p = data;
    unsigned char *rgba;
    unsigned needed;
    int i;

    memset(font, 0, sizeof(*font));

    if (size < 32 || memcmp(p, "BRFN", 4) != 0) {
        LOGE("font: bad magic (%u bytes)", size);
        return -1;
    }
    p += 4;
    if (read_u32(p) != 1) {
        LOGE("font: unsupported version %u", read_u32(p));
        return -1;
    }
    p += 4;

    font->atlas_w     = (int)read_u32(p); p += 4;
    font->atlas_h     = (int)read_u32(p); p += 4;
    font->line_height = (int)read_u32(p); p += 4;
    font->ascent      = (int)read_u32(p); p += 4;
    font->first_cp    = (int)read_u32(p); p += 4;
    font->glyph_count = (int)read_u32(p); p += 4;

    needed = 32 + (unsigned)font->glyph_count * 16 +
             (unsigned)(font->atlas_w * font->atlas_h);
    if (size < needed) {
        LOGE("font: truncated, need %u bytes have %u", needed, size);
        return -1;
    }

    font->glyphs = malloc(sizeof(br_glyph) * (size_t)font->glyph_count);
    if (!font->glyphs) {
        LOGE("font: out of memory for %d glyphs", font->glyph_count);
        return -1;
    }
    for (i = 0; i < font->glyph_count; i++) {
        br_glyph *g = &font->glyphs[i];
        g->x         = read_i16(p + 0);
        g->y         = read_i16(p + 2);
        g->w         = read_i16(p + 4);
        g->h         = read_i16(p + 6);
        g->bearing_x = read_i16(p + 8);
        g->bearing_y = read_i16(p + 10);
        g->advance   = read_i16(p + 12);
        p += 16;
    }

    /* The atlas is coverage only. Expand to premultiplied white so it blends
     * like every other texture and tints with glColor. */
    rgba = malloc((size_t)font->atlas_w * font->atlas_h * 4);
    if (!rgba) {
        LOGE("font: out of memory for a %dx%d atlas", font->atlas_w, font->atlas_h);
        free(font->glyphs);
        font->glyphs = NULL;
        return -1;
    }
    for (i = 0; i < font->atlas_w * font->atlas_h; i++) {
        unsigned char a = p[i];
        rgba[i * 4 + 0] = a;
        rgba[i * 4 + 1] = a;
        rgba[i * 4 + 2] = a;
        rgba[i * 4 + 3] = a;
    }
    br_image_from_rgba(&font->image, rgba, font->atlas_w, font->atlas_h);
    free(rgba);

    LOGI("font: %d glyphs, %dx%d atlas, line height %d",
         font->glyph_count, font->atlas_w, font->atlas_h, font->line_height);
    return 0;
}

void br_font_free(br_font *font)
{
    br_image_free(&font->image);
    free(font->glyphs);
    font->glyphs = NULL;
    font->glyph_count = 0;
}

static const br_glyph *glyph_for(const br_font *font, unsigned char c)
{
    int index = (int)c - font->first_cp;
    if (index < 0 || index >= font->glyph_count)
        return NULL;
    return &font->glyphs[index];
}

static float scale_for(const br_font *font, float px)
{
    return font->line_height > 0 ? px / (float)font->line_height : 1.0f;
}

float br_font_height(const br_font *font, float px)
{
    (void)font;
    return px;
}

float br_font_width(const br_font *font, const char *text, float px)
{
    float scale = scale_for(font, px);
    float width = 0.0f;
    const unsigned char *p;

    for (p = (const unsigned char *)text; *p; p++) {
        const br_glyph *g = glyph_for(font, *p);
        if (g)
            width += (float)g->advance;
    }
    return width * scale;
}

void br_font_draw(const br_font *font, const char *text, float x, float y,
                  float px, const br_color *color)
{
    float scale = scale_for(font, px);
    float inv_w = 1.0f / (float)font->atlas_w;
    float inv_h = 1.0f / (float)font->atlas_h;
    br_texture tex;
    const unsigned char *p;
    int n = 0;

    if (!font->glyphs)
        return;

    tex = br_texture_region(&font->image, 0.0f, 0.0f, 1.0f, 1.0f);

    for (p = (const unsigned char *)text; *p; p++) {
        const br_glyph *g = glyph_for(font, *p);
        float x0, y0, x1, y1, u0, v0, u1, v1;
        int v;

        if (!g)
            continue;
        if (g->w > 0 && g->h > 0 && n < MAX_GLYPHS_PER_CALL) {
            x0 = x + (float)g->bearing_x * scale;
            y0 = y + (float)g->bearing_y * scale;
            x1 = x0 + (float)g->w * scale;
            y1 = y0 + (float)g->h * scale;

            u0 = (float)g->x * inv_w;
            v0 = (float)g->y * inv_h;
            u1 = (float)(g->x + g->w) * inv_w;
            v1 = (float)(g->y + g->h) * inv_h;

            v = n * VERTS_PER_GLYPH * 2;
            s_xy[v + 0] = x0; s_xy[v + 1] = y0;  s_uv[v + 0] = u0; s_uv[v + 1] = v0;
            s_xy[v + 2] = x0; s_xy[v + 3] = y1;  s_uv[v + 2] = u0; s_uv[v + 3] = v1;
            s_xy[v + 4] = x1; s_xy[v + 5] = y0;  s_uv[v + 4] = u1; s_uv[v + 5] = v0;
            s_xy[v + 6] = x1; s_xy[v + 7] = y0;  s_uv[v + 6] = u1; s_uv[v + 7] = v0;
            s_xy[v + 8] = x0; s_xy[v + 9] = y1;  s_uv[v + 8] = u0; s_uv[v + 9] = v1;
            s_xy[v + 10] = x1; s_xy[v + 11] = y1; s_uv[v + 10] = u1; s_uv[v + 11] = v1;
            n++;
        }
        x += (float)g->advance * scale;
    }

    br_draw_triangles(s_xy, s_uv, n * VERTS_PER_GLYPH, &tex, color);
}

void br_font_draw_centered(const br_font *font, const char *text, float cx, float y,
                           float px, const br_color *color)
{
    br_font_draw(font, text, cx - br_font_width(font, text, px) * 0.5f, y, px, color);
}

void br_font_draw_right(const br_font *font, const char *text, float right, float y,
                        float px, const br_color *color)
{
    br_font_draw(font, text, right - br_font_width(font, text, px), y, px, color);
}

void br_font_draw_outlined(const br_font *font, const char *text, float x, float y,
                           float px, int align, const br_color *color,
                           const br_color *outline)
{
    /* Eight offsets rather than four: a diagonal-only ring leaves gaps on
     * stems, and the strings here are short enough that the cost is nothing. */
    static const float ring[8][2] = {
        { -1.0f, 0.0f }, { 1.0f, 0.0f }, { 0.0f, -1.0f }, { 0.0f, 1.0f },
        { -1.0f, -1.0f }, { 1.0f, -1.0f }, { -1.0f, 1.0f }, { 1.0f, 1.0f },
    };
    float spread = px * 0.055f;
    float left = x;
    int i;

    if (align == 0)
        left = x - br_font_width(font, text, px) * 0.5f;
    else if (align > 0)
        left = x - br_font_width(font, text, px);

    if (spread < 1.5f)
        spread = 1.5f;

    for (i = 0; i < 8; i++)
        br_font_draw(font, text, left + ring[i][0] * spread,
                     y + ring[i][1] * spread, px, outline);
    br_font_draw(font, text, left, y, px, color);
}
