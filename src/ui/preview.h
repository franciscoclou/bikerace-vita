#ifndef BR_PREVIEW_H
#define BR_PREVIEW_H

#include "../engine/render.h"
#include "../game/board.h"

/* Draws a track's shape fitted into a rectangle, one quad per segment. Each
 * segment stands alone, so the gaps between a level's separate runs stay gaps.
 *
 * The Android version never showed these; it had nothing to draw them from
 * because levels were code. We have the geometry, so the level tiles can show
 * the real track. */
void br_preview_draw(const br_boards *boards, float x, float y, float w, float h,
                     float thickness, const br_color *color);

#endif /* BR_PREVIEW_H */
