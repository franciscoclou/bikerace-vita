#ifndef BR_TRACK_MESH_H
#define BR_TRACK_MESH_H

#include "../engine/render.h"
#include "board.h"

/* Port of LevelMeshLoader: turns a track's polyline into the textured ribbon
 * that gets drawn. */
int br_track_mesh_build(br_mesh *mesh, const br_boards *boards,
                        const br_texture *tex, float thickness);

#endif /* BR_TRACK_MESH_H */
