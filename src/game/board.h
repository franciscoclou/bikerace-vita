#ifndef BR_BOARD_H
#define BR_BOARD_H

#include "../engine/geom.h"

/* A Board (com.topfreegames.bikerace.Board) is one line segment. A track is a
 * list of them; that list is a LevelBoards (bikerace.h.LevelBoards). */

typedef struct { vec2 v1, v2; } br_board;

static inline float br_board_angle(const br_board *b)
{
    return atan2f(b->v2.y - b->v1.y, b->v2.x - b->v1.x);
}

#define BR_SLAB_COUNT   100
#define BR_SLAB_MAX_HIT 30

/* One horizontal slab of the broad-phase index. */
typedef struct {
    int   first, count;   /* range into br_boards.order */
    float min_x, max_x;
} br_slab;

typedef struct {
    br_board *boards;
    int       count;
    br_aabb   bounds;

    /* Broad phase. The original bins each board by the x of its *first*
     * endpoint only, so a long board is indexed solely where it starts; that
     * is reproduced here because collision results depend on it. */
    int      *order;
    br_slab  *slabs;
    int       slab_count;
} br_boards;

void br_boards_build_index(br_boards *b);
void br_boards_free(br_boards *b);

/* Fill `out` (capacity `max`) with board indices whose slab spans [min_x,max_x].
 * Returns the number written. */
int  br_boards_query(const br_boards *b, float min_x, float max_x, int *out, int max);

#endif /* BR_BOARD_H */
