/* Software stand-in for the fixed-function matrix stack and draw calls, so the
 * renderer's transform maths can be checked without a Vita. */
#include <math.h>
#include <string.h>

#include "vitaGL.h"

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

void glDrawArrays(GLenum m, GLint f, GLsizei c) { (void)m; (void)f; (void)c; s_draws++; }

void glViewport(GLint a, GLint b, GLsizei c, GLsizei d) { (void)a;(void)b;(void)c;(void)d; }
void glMatrixMode(GLenum e) { (void)e; }
void glEnable(GLenum e) { (void)e; }
void glDisable(GLenum e) { (void)e; }
void glBlendFunc(GLenum a, GLenum b) { (void)a; (void)b; }
void glEnableClientState(GLenum e) { (void)e; }
void glClearColor(GLfloat r, GLfloat g, GLfloat b, GLfloat a) { (void)r;(void)g;(void)b;(void)a; }
void glClear(GLenum e) { (void)e; }
void glColor4f(GLfloat r, GLfloat g, GLfloat b, GLfloat a) { (void)r;(void)g;(void)b;(void)a; }
void glBindTexture(GLenum t, GLuint i) { (void)t; (void)i; }
void glGenTextures(GLsizei n, GLuint *ids) { GLsizei i; static GLuint next = 1; for (i = 0; i < n; i++) ids[i] = next++; }
void glDeleteTextures(GLsizei n, const GLuint *ids) { (void)n; (void)ids; }
void glTexParameteri(GLenum a, GLenum b, GLint c) { (void)a;(void)b;(void)c; }
void glTexImage2D(GLenum a, GLint b, GLint c, GLsizei d, GLsizei e, GLint f,
                  GLenum g, GLenum h, const void *p)
{ (void)a;(void)b;(void)c;(void)d;(void)e;(void)f;(void)g;(void)h;(void)p; }
void glVertexPointer(GLint a, GLenum b, GLsizei c, const void *p) { (void)a;(void)b;(void)c;(void)p; }
void glTexCoordPointer(GLint a, GLenum b, GLsizei c, const void *p) { (void)a;(void)b;(void)c;(void)p; }
void vglSwapBuffers(GLboolean b) { (void)b; }
