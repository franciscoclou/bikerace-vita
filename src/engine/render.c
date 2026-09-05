#include "render.h"

#include <stdlib.h>
#include <string.h>

#include "../platform/log.h"

#define SCREEN_W 960
#define SCREEN_H 544

static float s_aspect = (float)SCREEN_W / (float)SCREEN_H;

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

void br_draw_sprite(const br_texture *tex, float w, float h, const br_color *color)
{
    float hw = w * 0.5f, hh = h * 0.5f;
    br_draw_rect(-hw, hh, hw, -hh, tex, color);
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
