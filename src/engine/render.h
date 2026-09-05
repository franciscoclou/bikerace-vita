#ifndef BR_RENDER_H
#define BR_RENDER_H

#include "texture.h"
#include "vec2.h"

/* Equivalent of com.topfreegames.engine.video.IVideoDriver.
 *
 * The original targets OpenGL ES 1.0 fixed function and leaves the projection
 * matrix as identity, so the world is drawn straight in clip space: x and y
 * both span -1..1, and the scene graph's own matrices do all the framing.
 * vitaGL gives us the same fixed-function pipeline, so that is kept as-is. */

/* Colours are premultiplied, matching the textures and the blend mode. Build
 * them with br_rgba() rather than by hand. */
typedef struct { float r, g, b, a; } br_color;

#define BR_WHITE ((br_color){ 1.0f, 1.0f, 1.0f, 1.0f })

static inline br_color br_rgba(float r, float g, float b, float a)
{
    br_color c;
    c.r = r * a; c.g = g * a; c.b = b * a; c.a = a;
    return c;
}

/* A run of vertices drawn as one triangle strip, as MeshBuffer was. */
typedef struct {
    float          *xy;
    float          *uv;
    int             vertex_count;
    const br_texture *texture;
} br_mesh_buffer;

typedef struct {
    br_mesh_buffer *buffers;
    int             count;
} br_mesh;

void  br_render_init(void);
/* A 1x1 opaque white texture, for panels, rules and untextured shapes. */
const br_texture *br_white_texture(void);
void  br_render_begin(const br_color *clear);
void  br_render_end(void);
float br_render_aspect(void);

void  br_push(void);
void  br_pop(void);
void  br_identity(void);
/* Replaces the current transform with one where (0,0) is the top-left pixel
 * and (960,544) the bottom-right, for menus and overlays. */
void  br_ui_begin(void);
void  br_translate(float x, float y);
void  br_rotate_deg(float degrees);
void  br_scale(float x, float y);

/* Rect corners in the current transform's space, exactly as SpriteSceneNode
 * and CyclicSpriteSceneNode built them. */
void  br_draw_rect(float left, float top, float right, float bottom,
                   const br_texture *tex, const br_color *color);
/* Centred quad of the given size -- what SpriteSceneNode.render did. */
void  br_draw_sprite(const br_texture *tex, float w, float h, const br_color *color);
/* Solid rectangle in the current transform, given as position and size. */
void  br_fill_rect(float x, float y, float w, float h, const br_color *color);
void  br_draw_mesh(const br_mesh *mesh);
/* Loose triangles, for batching many quads into one call. */
void  br_draw_triangles(const float *xy, const float *uv, int vertex_count,
                        const br_texture *tex, const br_color *color);

int   br_mesh_buffer_init(br_mesh_buffer *buf, int vertex_count, const br_texture *tex);
void  br_mesh_free(br_mesh *mesh);

#endif /* BR_RENDER_H */
