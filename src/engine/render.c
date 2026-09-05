#include "render.h"

#include <math.h>
#include <stdlib.h>
#include <string.h>

#include "../platform/log.h"

#define SCREEN_W 960
#define SCREEN_H 544

static float s_aspect = (float)SCREEN_W / (float)SCREEN_H;

static br_image   s_white_image;
static br_texture s_white;

void br_render_init(void)
{
    glViewport(0, 0, SCREEN_W, SCREEN_H);

    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();

    glEnable(GL_TEXTURE_2D);
    glBlendFunc(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);
    glEnable(GL_BLEND);
    glDisable(GL_DEPTH_TEST);

    glEnableClientState(GL_VERTEX_ARRAY);
    glEnableClientState(GL_TEXTURE_COORD_ARRAY);

    {
        static const unsigned char pixel[4] = { 255, 255, 255, 255 };
        br_image_from_rgba(&s_white_image, pixel, 1, 1);
        s_white = br_texture_region(&s_white_image, 0.0f, 0.0f, 1.0f, 1.0f);
    }

    LOGI("render: %dx%d, aspect %.4f, premultiplied-alpha blending",
         SCREEN_W, SCREEN_H, s_aspect);
}

float br_render_aspect(void) { return s_aspect; }

void br_render_begin(const br_color *clear)
{
    glClearColor(clear->r, clear->g, clear->b, clear->a);
    glClear(GL_COLOR_BUFFER_BIT);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
}

void br_render_end(void)
{
    vglSwapBuffers(GL_FALSE);
}

void br_ui_begin(void)
{
    glLoadIdentity();
    glScalef(2.0f / (float)SCREEN_W, -2.0f / (float)SCREEN_H, 1.0f);
    glTranslatef(-(float)SCREEN_W * 0.5f, -(float)SCREEN_H * 0.5f, 0.0f);
}

void br_push(void)                     { glPushMatrix(); }
void br_pop(void)                      { glPopMatrix(); }
void br_identity(void)                 { glLoadIdentity(); }
void br_translate(float x, float y)    { glTranslatef(x, y, 0.0f); }
void br_rotate_deg(float degrees)      { glRotatef(degrees, 0.0f, 0.0f, 1.0f); }
void br_scale(float x, float y)        { glScalef(x, y, 1.0f); }

static void bind(const br_texture *tex)
{
    glBindTexture(GL_TEXTURE_2D, tex && tex->image ? tex->image->id : 0);
}

void br_draw_rect(float left, float top, float right, float bottom,
                  const br_texture *tex, const br_color *color)
{
    GLfloat xy[8], uv[8];

    if (!tex || !tex->image)
        return;

    /* Vertex order matches the original's triangle strip:
     * (left,bottom) (left,top) (right,bottom) (right,top). */
    xy[0] = left;  xy[1] = bottom;
    xy[2] = left;  xy[3] = top;
    xy[4] = right; xy[5] = bottom;
    xy[6] = right; xy[7] = top;

    uv[0] = tex->u0; uv[1] = tex->v1;
    uv[2] = tex->u0; uv[3] = tex->v0;
    uv[4] = tex->u1; uv[5] = tex->v1;
    uv[6] = tex->u1; uv[7] = tex->v0;

    if (color)
        glColor4f(color->r, color->g, color->b, color->a);
    else
        glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

    glVertexPointer(2, GL_FLOAT, 0, xy);
    glTexCoordPointer(2, GL_FLOAT, 0, uv);
    bind(tex);
    glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
}

const br_texture *br_white_texture(void) { return &s_white; }

void br_draw_sprite(const br_texture *tex, float w, float h, const br_color *color)
{
    float hw = w * 0.5f, hh = h * 0.5f;
    br_draw_rect(-hw, hh, hw, -hh, tex, color);
}

void br_fill_rect(float x, float y, float w, float h, const br_color *color)
{
    br_draw_rect(x, y, x + w, y + h, &s_white, color);
}

#define CIRCLE_SEGMENTS 28
#define UI_SHAPE_MAX_VERTS (CIRCLE_SEGMENTS * 6 + 12)

static float s_shape_xy[UI_SHAPE_MAX_VERTS * 2];
static float s_shape_uv[UI_SHAPE_MAX_VERTS * 2];

static int push_tri(int n, float ax, float ay, float bx, float by, float cx, float cy)
{
    int v = n * 2;

    if (n + 3 > UI_SHAPE_MAX_VERTS)
        return n;
    s_shape_xy[v + 0] = ax; s_shape_xy[v + 1] = ay;
    s_shape_xy[v + 2] = bx; s_shape_xy[v + 3] = by;
    s_shape_xy[v + 4] = cx; s_shape_xy[v + 5] = cy;
    return n + 3;
}

static void flush_shape(int count, const br_color *color)
{
    int i;

    for (i = 0; i < count * 2; i += 2) {
        s_shape_uv[i] = 0.5f;
        s_shape_uv[i + 1] = 0.5f;
    }
    br_draw_triangles(s_shape_xy, s_shape_uv, count, &s_white, color);
}

void br_fill_circle(float cx, float cy, float radius, const br_color *color)
{
    int i, n = 0;

    for (i = 0; i < CIRCLE_SEGMENTS; i++) {
        float a0 = (float)i / CIRCLE_SEGMENTS * 6.2831853f;
        float a1 = (float)(i + 1) / CIRCLE_SEGMENTS * 6.2831853f;
        n = push_tri(n, cx, cy,
                     cx + cosf(a0) * radius, cy + sinf(a0) * radius,
                     cx + cosf(a1) * radius, cy + sinf(a1) * radius);
    }
    flush_shape(n, color);
}

void br_stroke_circle(float cx, float cy, float radius, float thickness,
                      const br_color *color)
{
    float inner = radius - thickness;
    int i, n = 0;

    if (inner < 0.0f)
        inner = 0.0f;
    for (i = 0; i < CIRCLE_SEGMENTS; i++) {
        float a0 = (float)i / CIRCLE_SEGMENTS * 6.2831853f;
        float a1 = (float)(i + 1) / CIRCLE_SEGMENTS * 6.2831853f;
        float c0 = cosf(a0), s0 = sinf(a0), c1 = cosf(a1), s1 = sinf(a1);

        n = push_tri(n, cx + c0 * inner,  cy + s0 * inner,
                        cx + c0 * radius, cy + s0 * radius,
                        cx + c1 * radius, cy + s1 * radius);
        n = push_tri(n, cx + c0 * inner,  cy + s0 * inner,
                        cx + c1 * radius, cy + s1 * radius,
                        cx + c1 * inner,  cy + s1 * inner);
    }
    flush_shape(n, color);
}

void br_fill_line(float x0, float y0, float x1, float y1, float thickness,
                  const br_color *color)
{
    float dx = x1 - x0, dy = y1 - y0;
    float len = sqrtf(dx * dx + dy * dy);
    float nx, ny;
    int n = 0;

    if (len < 1e-5f)
        return;
    nx = -dy / len * thickness * 0.5f;
    ny =  dx / len * thickness * 0.5f;

    n = push_tri(n, x0 + nx, y0 + ny, x0 - nx, y0 - ny, x1 + nx, y1 + ny);
    n = push_tri(n, x1 + nx, y1 + ny, x0 - nx, y0 - ny, x1 - nx, y1 - ny);
    flush_shape(n, color);
}

/* A quarter disc, for rounding a corner without covering anything else. */
static void fill_quarter(float cx, float cy, float radius, float start_angle,
                         const br_color *color)
{
    int steps = CIRCLE_SEGMENTS / 4;
    int i, n = 0;

    for (i = 0; i < steps; i++) {
        float a0 = start_angle + (float)i / steps * 1.5707963f;
        float a1 = start_angle + (float)(i + 1) / steps * 1.5707963f;
        n = push_tri(n, cx, cy,
                     cx + cosf(a0) * radius, cy + sinf(a0) * radius,
                     cx + cosf(a1) * radius, cy + sinf(a1) * radius);
    }
    flush_shape(n, color);
}

/* Three rectangles and four quarter discs, none of them overlapping. Full
 * circles at the corners would cover the edges twice, and a translucent fill
 * shows every doubled pixel as a seam. */
void br_fill_round_rect(float x, float y, float w, float h, float radius,
                        const br_color *color)
{
    float max_radius = (w < h ? w : h) * 0.5f;

    if (radius > max_radius)
        radius = max_radius;
    if (radius <= 0.0f) {
        br_fill_rect(x, y, w, h, color);
        return;
    }

    br_fill_rect(x + radius, y, w - radius * 2.0f, h, color);
    br_fill_rect(x, y + radius, radius, h - radius * 2.0f, color);
    br_fill_rect(x + w - radius, y + radius, radius, h - radius * 2.0f, color);

    /* UI space has y growing downward, so "up" is a negative sweep. */
    fill_quarter(x + radius,     y + radius,     radius, 3.1415927f,  color);
    fill_quarter(x + w - radius, y + radius,     radius, 4.7123890f,  color);
    fill_quarter(x + w - radius, y + h - radius, radius, 0.0f,        color);
    fill_quarter(x + radius,     y + h - radius, radius, 1.5707963f,  color);
}

void br_draw_triangles(const float *xy, const float *uv, int vertex_count,
                       const br_texture *tex, const br_color *color)
{
    if (vertex_count < 3)
        return;

    if (color)
        glColor4f(color->r, color->g, color->b, color->a);
    else
        glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

    glVertexPointer(2, GL_FLOAT, 0, xy);
    glTexCoordPointer(2, GL_FLOAT, 0, uv);
    bind(tex);
    glDrawArrays(GL_TRIANGLES, 0, vertex_count);
}

void br_draw_mesh(const br_mesh *mesh)
{
    int i;

    glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    for (i = 0; i < mesh->count; i++) {
        const br_mesh_buffer *b = &mesh->buffers[i];
        if (b->vertex_count <= 0)
            continue;
        glVertexPointer(2, GL_FLOAT, 0, b->xy);
        glTexCoordPointer(2, GL_FLOAT, 0, b->uv);
        bind(b->texture);
        glDrawArrays(GL_TRIANGLE_STRIP, 0, b->vertex_count);
    }
}

int br_mesh_buffer_init(br_mesh_buffer *buf, int vertex_count, const br_texture *tex)
{
    buf->xy = malloc(sizeof(float) * 2 * (size_t)vertex_count);
    buf->uv = malloc(sizeof(float) * 2 * (size_t)vertex_count);
    if (!buf->xy || !buf->uv) {
        free(buf->xy); free(buf->uv);
        buf->xy = buf->uv = NULL;
        buf->vertex_count = 0;
        return -1;
    }
    buf->vertex_count = vertex_count;
    buf->texture = tex;
    return 0;
}

void br_mesh_free(br_mesh *mesh)
{
    int i;
    for (i = 0; i < mesh->count; i++) {
        free(mesh->buffers[i].xy);
        free(mesh->buffers[i].uv);
    }
    free(mesh->buffers);
    mesh->buffers = NULL;
    mesh->count = 0;
}
