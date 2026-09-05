#ifndef BR_VEC2_H
#define BR_VEC2_H

/* Port of com.topfreegames.engine.math.Vector2D (obfuscated:
 * com.topfreegames.engine.a.b). Method names below map 1:1 onto the Java
 * originals -- see docs/ARCHITECTURE.md for the obfuscation table.
 *
 * The originals mutate the receiver and return it so calls can chain; the C
 * versions keep that contract because the physics code depends on the aliasing
 * behaviour (scratch vectors are reused between frames). */

#include <math.h>

typedef struct { float x, y; } vec2;

static inline vec2 *v2_set(vec2 *v, float x, float y)      { v->x = x; v->y = y; return v; }
static inline vec2 *v2_copy(vec2 *v, const vec2 *s)        { v->x = s->x; v->y = s->y; return v; }
static inline vec2 *v2_add(vec2 *v, const vec2 *o)         { v->x += o->x; v->y += o->y; return v; }
static inline vec2 *v2_add_xy(vec2 *v, float x, float y)   { v->x += x; v->y += y; return v; }
static inline vec2 *v2_sub(vec2 *v, const vec2 *o)         { v->x -= o->x; v->y -= o->y; return v; }
static inline vec2 *v2_scale(vec2 *v, float s)             { v->x *= s; v->y *= s; return v; }
static inline vec2 *v2_div(vec2 *v, float s)               { v->x /= s; v->y /= s; return v; }
static inline float v2_dot(const vec2 *v, const vec2 *o)   { return v->x * o->x + v->y * o->y; }
static inline float v2_cross(const vec2 *v, const vec2 *o) { return v->x * o->y - v->y * o->x; }
static inline int   v2_eq(const vec2 *v, const vec2 *o)    { return v->x == o->x && v->y == o->y; }

/* Java returned exactly 0 for the zero vector rather than letting sqrtf(0)
 * round-trip; keep that so comparisons against 0.0f behave identically. */
static inline float v2_len(const vec2 *v)
{
    if (v->x == 0.0f && v->y == 0.0f)
        return 0.0f;
    return sqrtf(v->x * v->x + v->y * v->y);
}

static inline vec2 *v2_normalize(vec2 *v)
{
    float len = v2_len(v);
    if (len != 0.0f)
        v2_div(v, len);
    return v;
}

static inline vec2 *v2_rotate(vec2 *v, float radians)
{
    float c = cosf(radians);
    float s = sinf(radians);
    float x = v->x;
    v->x = v->x * c - v->y * s;
    v->y = c * v->y + s * x;
    return v;
}

/* Project v onto `onto`, in place. `tmp` is caller-owned scratch, exactly as
 * in the Java (which threaded a scratch Vector2D through to avoid allocating). */
static inline vec2 *v2_project(vec2 *v, const vec2 *onto, vec2 *tmp)
{
    v2_normalize(v2_copy(tmp, onto));
    v2_scale(tmp, v2_dot(v, tmp));
    return v2_copy(v, tmp);
}

#endif /* BR_VEC2_H */
