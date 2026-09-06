#ifndef BR_FONT_H
#define BR_FONT_H

#include "../engine/render.h"

/* Bitmap fonts baked from the faces the APK ships, by tools/makefont.py.
 * They are linked into the executable, so text works before any asset has been
 * copied to the Vita -- which is what makes an on-screen error message
 * possible at all. */

typedef struct {
    short x, y, w, h;
    short bearing_x, bearing_y;
    short advance;
} br_glyph;

typedef struct {
    br_image  image;
    br_glyph *glyphs;
    int       first_cp, glyph_count;
    int       line_height, ascent;
    int       atlas_w, atlas_h;
} br_font;

int  br_font_load(br_font *font, const unsigned char *data, unsigned size);
void br_font_free(br_font *font);

/* Sizes and positions are pixels in the UI space br_ui_begin() sets up.
 * `px` is the line height to draw at; the baked size is scaled to suit. */
float br_font_width(const br_font *font, const char *text, float px);
float br_font_height(const br_font *font, float px);

/* `x`, `y` are the top-left of the line box. */
void  br_font_draw(const br_font *font, const char *text, float x, float y,
                   float px, const br_color *color);
void  br_font_draw_centered(const br_font *font, const char *text, float cx, float y,
                            float px, const br_color *color);
void  br_font_draw_right(const br_font *font, const char *text, float right, float y,
                         float px, const br_color *color);

/* Text with a dark ring behind it, for drawing straight onto the game where
 * the background can be bright sky or pale sand. `align` is -1 left, 0 centre,
 * 1 right, and x is the corresponding edge. */
void  br_font_draw_outlined(const br_font *font, const char *text, float x, float y,
                            float px, int align, const br_color *color,
                            const br_color *outline);

#endif /* BR_FONT_H */
