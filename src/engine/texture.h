#ifndef BR_TEXTURE_H
#define BR_TEXTURE_H

#include <vitaGL.h>

/* A decoded image living in GPU memory. The game ships everything in a handful
 * of 2048x2048 atlases, so these are few and long-lived. */
typedef struct {
    GLuint id;
    int    width, height;
} br_image;

/* A rectangle of an atlas. Coordinates are normalised, and follow the
 * original's RectF layout: v0 is the top edge, v1 the bottom. Some regions are
 * stored with v0 > v1, which flips the sprite vertically -- that is
 * deliberate in the source art, so it is preserved rather than normalised. */
typedef struct {
    const br_image *image;
    float u0, v0, u1, v1;
} br_texture;

int  br_image_load(br_image *img, const char *path);
/* Upload already-decoded, premultiplied RGBA. */
void br_image_from_rgba(br_image *img, const unsigned char *rgba, int w, int h);
void br_image_free(br_image *img);

br_texture br_texture_region(const br_image *img, float u0, float v0, float u1, float v1);

static inline float br_texture_w(const br_texture *t) { return t->u1 - t->u0; }
static inline float br_texture_h(const br_texture *t) { return t->v1 - t->v0; }

/* Height that keeps the region's aspect at the given width, as the original
 * computed it for every sprite: w / region_w * region_h. */
static inline float br_texture_fit_h(const br_texture *t, float w)
{
    return (w / br_texture_w(t)) * br_texture_h(t);
}

#endif /* BR_TEXTURE_H */
