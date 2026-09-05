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

typedef struct { float r, g, b, a; } br_color;

#define BR_WHITE ((br_color){ 1.0f, 1.0f, 1.0f, 1.0f })

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
void  br_render_begin(const br_color *clear);
void  br_render_end(void);
float br_render_aspect(void);

void  br_push(void);
void  br_pop(void);
void  br_identity(void);
void  br_translate(float x, float y);
void  br_rotate_deg(float degrees);
void  br_scale(float x, float y);

/* Rect corners in the current transform's space, exactly as SpriteSceneNode
 * and CyclicSpriteSceneNode built them. */
void  br_draw_rect(float left, float top, float right, float bottom,
                   const br_texture *tex, const br_color *color);
/* Centred quad of the given size -- what SpriteSceneNode.render did. */
void  br_draw_sprite(const br_texture *tex, float w, float h, const br_color *color);
void  br_draw_mesh(const br_mesh *mesh);

int   br_mesh_buffer_init(br_mesh_buffer *buf, int vertex_count, const br_texture *tex);
void  br_mesh_free(br_mesh *mesh);

#endif /* BR_RENDER_H */
