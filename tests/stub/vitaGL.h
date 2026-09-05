/* Minimal vitaGL surface so the game modules can be compiled and exercised on
 * the host. Only the types and calls src/engine/render.c and texture.c use.
 * Draw calls record into a log the tests can inspect. */
#ifndef BR_TEST_VITAGL_H
#define BR_TEST_VITAGL_H

typedef unsigned GLuint;
typedef int      GLint;
typedef int      GLsizei;
typedef unsigned GLenum;
typedef unsigned char GLboolean;
typedef float    GLfloat;

#define GL_FALSE 0
#define GL_TRUE  1
#define GL_FLOAT                  0x1406
#define GL_UNSIGNED_BYTE          0x1401
#define GL_RGBA                   0x1908
#define GL_TEXTURE_2D             0x0DE1
#define GL_TEXTURE_MIN_FILTER     0x2801
#define GL_TEXTURE_MAG_FILTER     0x2800
#define GL_TEXTURE_WRAP_S         0x2802
#define GL_TEXTURE_WRAP_T         0x2803
#define GL_LINEAR                 0x2601
#define GL_CLAMP_TO_EDGE          0x812F
#define GL_BLEND                  0x0BE2
#define GL_DEPTH_TEST             0x0B71
#define GL_ONE                    1
#define GL_ONE_MINUS_SRC_ALPHA    0x0303
#define GL_COLOR_BUFFER_BIT       0x00004000
#define GL_PROJECTION             0x1701
#define GL_MODELVIEW              0x1700
#define GL_VERTEX_ARRAY           0x8074
#define GL_TEXTURE_COORD_ARRAY    0x8078
#define GL_TRIANGLE_STRIP         0x0005

void glViewport(GLint, GLint, GLsizei, GLsizei);
void glMatrixMode(GLenum);
void glLoadIdentity(void);
void glPushMatrix(void);
void glPopMatrix(void);
void glTranslatef(GLfloat, GLfloat, GLfloat);
void glRotatef(GLfloat, GLfloat, GLfloat, GLfloat);
void glScalef(GLfloat, GLfloat, GLfloat);
void glEnable(GLenum);
void glDisable(GLenum);
void glBlendFunc(GLenum, GLenum);
void glEnableClientState(GLenum);
void glClearColor(GLfloat, GLfloat, GLfloat, GLfloat);
void glClear(GLenum);
void glColor4f(GLfloat, GLfloat, GLfloat, GLfloat);
void glBindTexture(GLenum, GLuint);
void glGenTextures(GLsizei, GLuint *);
void glDeleteTextures(GLsizei, const GLuint *);
void glTexParameteri(GLenum, GLenum, GLint);
void glTexImage2D(GLenum, GLint, GLint, GLsizei, GLsizei, GLint, GLenum, GLenum, const void *);
void glVertexPointer(GLint, GLenum, GLsizei, const void *);
void glTexCoordPointer(GLint, GLenum, GLsizei, const void *);
void glDrawArrays(GLenum, GLint, GLsizei);
void vglSwapBuffers(GLboolean);

/* Test-only: current modelview, so transforms can be checked. */
void br_test_gl_reset(void);
void br_test_gl_transform(float x, float y, float *out_x, float *out_y);
int  br_test_gl_draw_count(void);

#endif
