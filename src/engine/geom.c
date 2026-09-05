#include "geom.h"

int br_segments_intersect(const vec2 *a, const vec2 *b, const vec2 *c, const vec2 *d)
{
    return br_side(a, b, c) * br_side(a, b, d) <= 0.0f &&
           br_side(c, d, a) * br_side(c, d, b) <= 0.0f;
}

vec2 br_closest_point_on_segment(const vec2 *p, const vec2 *a, const vec2 *b)
{
    vec2 ab, pb, pa, scratch, out;

    v2_sub(v2_copy(&ab, b), a);
    v2_sub(v2_copy(&pb, p), b);
    v2_sub(v2_copy(&pa, p), a);

    if (v2_dot(&ab, &pa) * v2_dot(&ab, &pb) < 0.0f) {
        v2_project(&pa, &ab, &scratch);
        v2_add(v2_copy(&out, &pa), a);
    } else if (v2_dot(&ab, &pb) > 0.0f) {
        v2_copy(&out, b);
    } else {
        v2_copy(&out, a);
    }
    return out;
}
