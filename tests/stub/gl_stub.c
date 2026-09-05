/* Software stand-in for the fixed-function matrix stack and draw calls, so the
 * renderer's transform maths can be checked without a Vita. */
#include <math.h>
#include <stdlib.h>
#include <string.h>

#include "vitaGL.h"

/* Software rasteriser, so a frame can be rendered and eyeballed on the host.
 * It is deliberately crude -- nearest sampling, no clipping beyond the
 * viewport -- because its job is to show that transforms, UVs and draw order
 * are right, not to match the Vita pixel for pixel. */
#define FB_W 960
#define FB_H 544
#define MAX_TEXTURES 64

typedef struct { unsigned char *rgba; int w, h; } tex_store;

static unsigned char s_fb[FB_W * FB_H * 4];
static tex_store s_tex[MAX_TEXTURES];
static GLuint s_bound;
static const float *s_xy, *s_uv;
static float s_color[4] = { 1, 1, 1, 1 };

typedef struct { float m[6]; } mat23; /* a b tx / c d ty */

static mat23 s_stack[32];
static int   s_depth;
static int   s_draws;

static mat23 identity(void)
{
    mat23 m = { { 1, 0, 0, 0, 1, 0 } };
    return m;
}

static mat23 mul(const mat23 *a, const mat23 *b)
{
    mat23 r;
    r.m[0] = a->m[0] * b->m[0] + a->m[1] * b->m[3];
    r.m[1] = a->m[0] * b->m[1] + a->m[1] * b->m[4];
    r.m[2] = a->m[0] * b->m[2] + a->m[1] * b->m[5] + a->m[2];
    r.m[3] = a->m[3] * b->m[0] + a->m[4] * b->m[3];
    r.m[4] = a->m[3] * b->m[1] + a->m[4] * b->m[4];
    r.m[5] = a->m[3] * b->m[2] + a->m[4] * b->m[5] + a->m[5];
    return r;
}

static void apply(mat23 local) { s_stack[s_depth] = mul(&s_stack[s_depth], &local); }

void br_test_gl_reset(void) { s_depth = 0; s_stack[0] = identity(); s_draws = 0; }

void br_test_gl_transform(float x, float y, float *out_x, float *out_y)
{
    const mat23 *m = &s_stack[s_depth];
    *out_x = m->m[0] * x + m->m[1] * y + m->m[2];
    *out_y = m->m[3] * x + m->m[4] * y + m->m[5];
}

int br_test_gl_draw_count(void) { return s_draws; }

void glLoadIdentity(void) { s_stack[s_depth] = identity(); }
void glPushMatrix(void)   { if (s_depth < 31) { s_stack[s_depth + 1] = s_stack[s_depth]; s_depth++; } }
void glPopMatrix(void)    { if (s_depth > 0) s_depth--; }

void glTranslatef(GLfloat x, GLfloat y, GLfloat z)
{
    mat23 t = { { 1, 0, x, 0, 1, y } };
    (void)z; apply(t);
}

void glScalef(GLfloat x, GLfloat y, GLfloat z)
{
    mat23 t = { { x, 0, 0, 0, y, 0 } };
    (void)z; apply(t);
}

void glRotatef(GLfloat deg, GLfloat x, GLfloat y, GLfloat z)
{
    float r = deg * 3.14159265358979f / 180.0f;
    mat23 t = { { cosf(r), -sinf(r), 0, sinf(r), cosf(r), 0 } };
    (void)x; (void)y; (void)z; apply(t);
}

static void sample(const tex_store *t, float u, float v, float *out)
{
    int x, y;
    const unsigned char *p;

    if (!t->rgba) { out[0] = out[1] = out[2] = out[3] = 1.0f; return; }
    x = (int)(u * (float)t->w);
    y = (int)(v * (float)t->h);
    if (x < 0) x = 0;
    if (x >= t->w) x = t->w - 1;
    if (y < 0) y = 0;
    if (y >= t->h) y = t->h - 1;
    p = t->rgba + ((size_t)y * t->w + x) * 4;
    out[0] = p[0] / 255.0f; out[1] = p[1] / 255.0f;
    out[2] = p[2] / 255.0f; out[3] = p[3] / 255.0f;
}

/* Clip-space (-1..1) to framebuffer pixels. */
static void to_screen(float cx, float cy, float *sx, float *sy)
{
    *sx = (cx * 0.5f + 0.5f) * (float)FB_W;
    *sy = (0.5f - cy * 0.5f) * (float)FB_H;
}

static void raster_tri(const float *p, const float *uv, const tex_store *t)
{
    float minx = p[0], maxx = p[0], miny = p[1], maxy = p[1];
    float d, w0, w1, w2;
    int x, y, i;

    for (i = 1; i < 3; i++) {
        if (p[i*2]   < minx) minx = p[i*2];
        if (p[i*2]   > maxx) maxx = p[i*2];
        if (p[i*2+1] < miny) miny = p[i*2+1];
        if (p[i*2+1] > maxy) maxy = p[i*2+1];
    }
    if (minx < 0) minx = 0;
    if (maxx > FB_W - 1) maxx = FB_W - 1;
    if (miny < 0) miny = 0;
    if (maxy > FB_H - 1) maxy = FB_H - 1;

    d = (p[2]-p[0])*(p[5]-p[1]) - (p[4]-p[0])*(p[3]-p[1]);
    if (fabsf(d) < 1e-9f) return;

    for (y = (int)miny; y <= (int)maxy; y++) {
        for (x = (int)minx; x <= (int)maxx; x++) {
            float px = (float)x + 0.5f, py = (float)y + 0.5f;
            float u, v, rgba[4];
            unsigned char *dst;

            w0 = ((p[2]-px)*(p[5]-py) - (p[4]-px)*(p[3]-py)) / d;
            w1 = ((p[4]-px)*(p[1]-py) - (p[0]-px)*(p[5]-py)) / d;
            w2 = 1.0f - w0 - w1;
            if (w0 < 0 || w1 < 0 || w2 < 0) continue;

            u = w0*uv[0] + w1*uv[2] + w2*uv[4];
            v = w0*uv[1] + w1*uv[3] + w2*uv[5];
            sample(t, u, v, rgba);

            /* Premultiplied source over destination, as on the Vita. */
            rgba[0] *= s_color[0]; rgba[1] *= s_color[1];
            rgba[2] *= s_color[2]; rgba[3] *= s_color[3];

            dst = s_fb + ((size_t)y * FB_W + x) * 4;
            for (i = 0; i < 3; i++)
                dst[i] = (unsigned char)((rgba[i] + (dst[i] / 255.0f) * (1.0f - rgba[3])) * 255.0f);
            dst[3] = 255;
        }
    }
}

void glDrawArrays(GLenum mode, GLint first, GLsizei count)
{
    const tex_store *t = &s_tex[s_bound < MAX_TEXTURES ? s_bound : 0];
    int step = (mode == GL_TRIANGLES) ? 3 : 1;
    int i;

    s_draws++;
    if (!s_xy || !s_uv || count < 3)
        return;

    for (i = first; i + 2 < first + count; i += step) {
        float p[6], uv[6];
        int k;
        for (k = 0; k < 3; k++) {
            float cx, cy;
            br_test_gl_transform(s_xy[(i+k)*2], s_xy[(i+k)*2+1], &cx, &cy);
            to_screen(cx, cy, &p[k*2], &p[k*2+1]);
            uv[k*2]   = s_uv[(i+k)*2];
            uv[k*2+1] = s_uv[(i+k)*2+1];
        }
        raster_tri(p, uv, t);
    }
}

void br_test_fb_clear(float r, float g, float b)
{
    int i;
    for (i = 0; i < FB_W * FB_H; i++) {
        s_fb[i*4+0] = (unsigned char)(r * 255.0f);
        s_fb[i*4+1] = (unsigned char)(g * 255.0f);
        s_fb[i*4+2] = (unsigned char)(b * 255.0f);
        s_fb[i*4+3] = 255;
    }
}

const unsigned char *br_test_fb(int *w, int *h) { *w = FB_W; *h = FB_H; return s_fb; }

void glViewport(GLint a, GLint b, GLsizei c, GLsizei d) { (void)a;(void)b;(void)c;(void)d; }
void glMatrixMode(GLenum e) { (void)e; }
void glEnable(GLenum e) { (void)e; }
void glDisable(GLenum e) { (void)e; }
void glBlendFunc(GLenum a, GLenum b) { (void)a; (void)b; }
void glEnableClientState(GLenum e) { (void)e; }
static float s_clear[3];
void glClearColor(GLfloat r, GLfloat g, GLfloat b, GLfloat a)
{ (void)a; s_clear[0] = r; s_clear[1] = g; s_clear[2] = b; }
void glClear(GLenum e) { (void)e; br_test_fb_clear(s_clear[0], s_clear[1], s_clear[2]); }
void glColor4f(GLfloat r, GLfloat g, GLfloat b, GLfloat a)
{ s_color[0] = r; s_color[1] = g; s_color[2] = b; s_color[3] = a; }
void glBindTexture(GLenum t, GLuint i) { (void)t; s_bound = i; }
void glGenTextures(GLsizei n, GLuint *ids) { GLsizei i; static GLuint next = 1; for (i = 0; i < n; i++) ids[i] = next++; }
void glDeleteTextures(GLsizei n, const GLuint *ids) { (void)n; (void)ids; }
void glTexParameteri(GLenum a, GLenum b, GLint c) { (void)a;(void)b;(void)c; }
void glTexImage2D(GLenum a, GLint b, GLint c, GLsizei w, GLsizei h, GLint f,
                  GLenum g, GLenum ty, const void *pixels)
{
    tex_store *t;
    (void)a;(void)b;(void)c;(void)f;(void)g;(void)ty;
    if (s_bound >= MAX_TEXTURES) return;
    t = &s_tex[s_bound];
    free(t->rgba);
    t->w = w; t->h = h;
    t->rgba = malloc((size_t)w * h * 4);
    if (t->rgba) memcpy(t->rgba, pixels, (size_t)w * h * 4);
}
void glVertexPointer(GLint a, GLenum b, GLsizei c, const void *p) { (void)a;(void)b;(void)c; s_xy = p; }
void glTexCoordPointer(GLint a, GLenum b, GLsizei c, const void *p) { (void)a;(void)b;(void)c; s_uv = p; }
void vglSwapBuffers(GLboolean b) { (void)b; }
