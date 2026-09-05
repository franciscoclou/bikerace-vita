#include "board.h"

#include <stdlib.h>
#include <string.h>

#include "../platform/log.h"

void br_boards_build_index(br_boards *b)
{
    float slab_w = (1.05f * br_aabb_width(&b->bounds)) / (float)BR_SLAB_COUNT;
    char *used = calloc((size_t)b->count, 1);
    br_slab *slabs = calloc(BR_SLAB_COUNT, sizeof(br_slab));
    int *order = malloc(sizeof(int) * (size_t)b->count);
    float lo = b->bounds.min.x, hi = lo + slab_w;
    int written = 0, n = 0, i, s;

    if (!used || !slabs || !order) {
        free(used); free(slabs); free(order);
        LOGE("boards: index allocation failed for %d boards", b->count);
        b->order = NULL; b->slabs = NULL; b->slab_count = 0;
        return;
    }

    for (s = 0; s < BR_SLAB_COUNT; s++) {
        br_slab slab;
        slab.first = written;
        slab.count = 0;
        slab.min_x =  1e30f;
        slab.max_x = -1e30f;

        for (i = 0; i < b->count; i++) {
            const br_board *bd = &b->boards[i];
            if (used[i] || bd->v1.x < lo || bd->v1.x > hi)
                continue;
            used[i] = 1;
            order[written++] = i;
            slab.count++;
            if (bd->v1.x < slab.min_x) slab.min_x = bd->v1.x;
            if (bd->v2.x < slab.min_x) slab.min_x = bd->v2.x;
            if (bd->v1.x > slab.max_x) slab.max_x = bd->v1.x;
            if (bd->v2.x > slab.max_x) slab.max_x = bd->v2.x;
        }
        lo = hi;
        hi += slab_w;
        if (slab.count > 0)
            slabs[n++] = slab;
    }

    /* Merge neighbouring slabs while the pair still fits the size cap. Their
     * index ranges are already adjacent, so merging is just arithmetic. */
    i = 0;
    while (i + 1 < n) {
        if (slabs[i].count < BR_SLAB_MAX_HIT &&
            slabs[i].count + slabs[i + 1].count <= BR_SLAB_MAX_HIT) {
            br_slab *dst = &slabs[i], *src = &slabs[i + 1];
            dst->count += src->count;
            if (src->min_x < dst->min_x) dst->min_x = src->min_x;
            if (src->max_x > dst->max_x) dst->max_x = src->max_x;
            memmove(src, src + 1, sizeof(br_slab) * (size_t)(n - i - 2));
            n--;
        } else {
            i++;
        }
    }

    free(used);
    b->order = order;
    b->slabs = slabs;
    b->slab_count = n;
}

void br_boards_free(br_boards *b)
{
    free(b->boards); free(b->order); free(b->slabs);
    memset(b, 0, sizeof(*b));
}

int br_boards_query(const br_boards *b, float min_x, float max_x, int *out, int max)
{
    int written = 0, s, i;

    if (!b->slabs) {
        for (i = 0; i < b->count && written < max; i++)
            out[written++] = i;
        return written;
    }

    for (s = 0; s < b->slab_count; s++) {
        const br_slab *slab = &b->slabs[s];
        if (min_x >= slab->max_x || slab->min_x >= max_x)
            continue;
        for (i = 0; i < slab->count && written < max; i++)
            out[written++] = b->order[slab->first + i];
    }
    return written;
}
