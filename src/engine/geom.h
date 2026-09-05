#ifndef BR_GEOM_H
#define BR_GEOM_H

#include "vec2.h"

/* Port of com.topfreegames.engine.math.Utils (obfuscated engine.a.a). */

/* Signed area of (b - a) x (p - b). Sign tells which side of ab p lies on.
 * Note the second term is p - b, not p - a; that is what the original does and
 * the collision code depends on the magnitude, not just the sign. */
static inline float br_side(const vec2 *a, const vec2 *b, const vec2 *p)
{
    vec2 ab = { b->x - a->x, b->y - a->y };
    vec2 bp = { p->x - b->x, p->y - b->y };
    return v2_cross(&ab, &bp);
}

int  br_segments_intersect(const vec2 *a, const vec2 *b, const vec2 *c, const vec2 *d);
vec2 br_closest_point_on_segment(const vec2 *p, const vec2 *a, const vec2 *b);

/* Axis-aligned box. The original used android.graphics.RectF, whose y axis
 * convention flips between uses; this is always min/max so the ambiguity is
 * gone. Where a port needs the original's field names: left=min.x, right=max.x,
 * and for level bounds top=max.y, bottom=min.y. */
typedef struct { vec2 min, max; } br_aabb;

static inline void br_aabb_set(br_aabb *r, float min_x, float min_y, float max_x, float max_y)
{
    v2_set(&r->min, min_x, min_y);
    v2_set(&r->max, max_x, max_y);
}

static inline int br_aabb_overlap(const br_aabb *a, const br_aabb *b)
{
    return a->min.x < b->max.x && b->min.x < a->max.x &&
           a->min.y < b->max.y && b->min.y < a->max.y;
}

/* Half-open on max, matching android.graphics.RectF.contains. */
static inline int br_aabb_contains(const br_aabb *r, float x, float y)
{
    return r->min.x < r->max.x && r->min.y < r->max.y &&
           x >= r->min.x && x < r->max.x && y >= r->min.y && y < r->max.y;
}

static inline void br_aabb_from_segment(br_aabb *r, const vec2 *a, const vec2 *b, float margin)
{
    br_aabb_set(r,
                (a->x < b->x ? a->x : b->x) - margin,
                (a->y < b->y ? a->y : b->y) - margin,
                (a->x < b->x ? b->x : a->x) + margin,
                (a->y < b->y ? b->y : a->y) + margin);
}

static inline float br_aabb_width(const br_aabb *r)  { return r->max.x - r->min.x; }
static inline float br_aabb_height(const br_aabb *r) { return r->max.y - r->min.y; }

#endif /* BR_GEOM_H */
