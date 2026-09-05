#include "track_mesh.h"

#include <stdlib.h>
#include <string.h>

#include "../platform/log.h"

/* Each board becomes four vertices of one triangle strip. Where consecutive
 * boards meet, board N's last two vertices coincide with board N+1's first
 * two, so the triangles bridging them are degenerate and draw nothing. The
 * original relies on exactly this. */
#define VERTS_PER_BOARD 4

static void emit_run(br_mesh_buffer *buf, const br_boards *boards,
                     int first, int last, const br_texture *tex, float thickness)
{
    int i, v = 0;

    for (i = first; i <= last; i++) {
        const br_board *b = &boards->boards[i];
        vec2 offset;

        /* Perpendicular to the board, pointing away from the surface. */
        v2_sub(v2_copy(&offset, &b->v1), &b->v2);
        v2_scale(v2_normalize(v2_rotate(&offset, 1.5707964f)), thickness);

        buf->xy[v * 2 + 0] = b->v1.x;
        buf->xy[v * 2 + 1] = b->v1.y;
        buf->uv[v * 2 + 0] = tex->u0;
        buf->uv[v * 2 + 1] = tex->v0;
        v++;

        buf->xy[v * 2 + 0] = b->v1.x + offset.x;
        buf->xy[v * 2 + 1] = b->v1.y + offset.y;
        buf->uv[v * 2 + 0] = tex->u0;
        buf->uv[v * 2 + 1] = tex->v1;
        v++;

        buf->xy[v * 2 + 0] = b->v2.x;
        buf->xy[v * 2 + 1] = b->v2.y;
        buf->uv[v * 2 + 0] = tex->u1;
        buf->uv[v * 2 + 1] = tex->v0;
        v++;

        buf->xy[v * 2 + 0] = b->v2.x + offset.x;
        buf->xy[v * 2 + 1] = b->v2.y + offset.y;
        buf->uv[v * 2 + 0] = tex->u1;
        buf->uv[v * 2 + 1] = tex->v1;
        v++;
    }
}

int br_track_mesh_build(br_mesh *mesh, const br_boards *boards,
                        const br_texture *tex, float thickness)
{
    int runs = 0, i, first, out;

    memset(mesh, 0, sizeof(*mesh));
    if (boards->count <= 0)
        return -1;

    /* A run ends wherever one board's end is not the next board's start. */
    for (i = 0; i < boards->count - 1; i++) {
        if (!v2_eq(&boards->boards[i].v2, &boards->boards[i + 1].v1))
            runs++;
    }
    runs++;

    mesh->buffers = calloc((size_t)runs, sizeof(br_mesh_buffer));
    if (!mesh->buffers) {
        LOGE("track mesh: out of memory for %d runs", runs);
        return -1;
    }
    mesh->count = runs;

    first = 0;
    out = 0;
    for (i = 0; i < boards->count - 1; i++) {
        if (v2_eq(&boards->boards[i].v2, &boards->boards[i + 1].v1))
            continue;
        if (br_mesh_buffer_init(&mesh->buffers[out], (i - first + 1) * VERTS_PER_BOARD, tex) < 0)
            goto fail;
        emit_run(&mesh->buffers[out], boards, first, i, tex, thickness);
        out++;
        first = i + 1;
    }
    if (br_mesh_buffer_init(&mesh->buffers[out],
                            (boards->count - first) * VERTS_PER_BOARD, tex) < 0)
        goto fail;
    emit_run(&mesh->buffers[out], boards, first, boards->count - 1, tex, thickness);

    return 0;

fail:
    LOGE("track mesh: out of memory building %d boards", boards->count);
    br_mesh_free(mesh);
    return -1;
}
