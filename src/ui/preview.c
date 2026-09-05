#include "preview.h"

#include <math.h>

#define BATCH_QUADS     256
#define VERTS_PER_QUAD  6

static float s_xy[BATCH_QUADS * VERTS_PER_QUAD * 2];
static float s_uv[BATCH_QUADS * VERTS_PER_QUAD * 2];

static void push_quad(int index, const vec2 *a, const vec2 *b, float half)
{
    vec2 normal;
    float x0, y0, x1, y1, x2, y2, x3, y3;
    int v = index * VERTS_PER_QUAD * 2;

    v2_sub(v2_copy(&normal, b), a);
    v2_scale(v2_normalize(v2_rotate(&normal, 1.5707964f)), half);

    x0 = a->x + normal.x; y0 = a->y + normal.y;
    x1 = a->x - normal.x; y1 = a->y - normal.y;
    x2 = b->x + normal.x; y2 = b->y + normal.y;
    x3 = b->x - normal.x; y3 = b->y - normal.y;

    s_xy[v +  0] = x0; s_xy[v +  1] = y0;
    s_xy[v +  2] = x1; s_xy[v +  3] = y1;
    s_xy[v +  4] = x2; s_xy[v +  5] = y2;
    s_xy[v +  6] = x2; s_xy[v +  7] = y2;
    s_xy[v +  8] = x1; s_xy[v +  9] = y1;
    s_xy[v + 10] = x3; s_xy[v + 11] = y3;
}

void br_preview_draw(const br_boards *boards, float x, float y, float w, float h,
                     float thickness, const br_color *color)
{
    float span_x = br_aabb_width(&boards->bounds);
    float span_y = br_aabb_height(&boards->bounds);
    float scale, offset_x, offset_y, half = thickness * 0.5f;
    int i, batched = 0;

    if (boards->count <= 0 || span_x <= 0.0f)
        return;

    scale = w / span_x;
    if (span_y > 0.0f && h / span_y < scale)
        scale = h / span_y;

    /* Centre the track in the box. UI space has y growing downward, so the
     * world's y is flipped on the way in. */
    offset_x = x + (w - span_x * scale) * 0.5f;
    offset_y = y + (h + span_y * scale) * 0.5f;

    for (i = 0; i < BATCH_QUADS * VERTS_PER_QUAD * 2; i += 2) {
        s_uv[i] = 0.5f;
        s_uv[i + 1] = 0.5f;
    }

    for (i = 0; i < boards->count; i++) {
        const br_board *board = &boards->boards[i];
        vec2 a, b;

        v2_set(&a, offset_x + (board->v1.x - boards->bounds.min.x) * scale,
                   offset_y - (board->v1.y - boards->bounds.min.y) * scale);
        v2_set(&b, offset_x + (board->v2.x - boards->bounds.min.x) * scale,
                   offset_y - (board->v2.y - boards->bounds.min.y) * scale);
        if (v2_eq(&a, &b))
            continue;

        push_quad(batched++, &a, &b, half);
        if (batched == BATCH_QUADS) {
            br_draw_triangles(s_xy, s_uv, batched * VERTS_PER_QUAD,
                              br_white_texture(), color);
            batched = 0;
        }
    }

    if (batched > 0)
        br_draw_triangles(s_xy, s_uv, batched * VERTS_PER_QUAD,
                          br_white_texture(), color);
}
