#ifndef BR_SCENE_H
#define BR_SCENE_H

#include "../engine/render.h"
#include "bike.h"
#include "camera.h"
#include "level.h"
#include "track_mesh.h"

/* Port of GameSceneDirector plus the parts of GraphicsResources and
 * TextureCoordinates that single player needs.
 *
 * The original built a scene graph of nodes; the structure is fixed, so this
 * walks the same transforms directly against the fixed-function matrix stack
 * instead of allocating nodes for them. */

typedef struct {
    br_image   atlas;
    br_image   bike_image;
    br_image   wheel_image;   /* only bikes with their own wheel art use this */
    br_image   ghost_image;   /* the bike a recorded run was set on */
    int        atlas_index;
    br_bike_type bike_type;
    int          ghost_bike;  /* br_bike_type, or -1 for none loaded */

    br_texture sky, background_mid, background_near;
    br_texture track, pole, wheel, bike_sprite, ghost_sprite;
    br_texture flag[8];

    br_mesh    track_mesh;
    int        has_track;
} br_scene;

int  br_scene_init(br_scene *scene);
void br_scene_free(br_scene *scene);

/* Loads the atlas for the world and the sprite for the bike, if either
 * changed. Safe to call every level load. */
int  br_scene_use(br_scene *scene, int atlas_index, br_bike_type bike);
/* Loads the sprite a ghost was recorded on. Pass -1 to drop it. */
int  br_scene_use_ghost(br_scene *scene, int bike);
int  br_scene_set_level(br_scene *scene, const br_level *level);

/* A ghost's pose for this frame, or active == 0 for none. */
typedef struct {
    int          active;
    vec2         pos;          /* the rider's head, as Bike stores it */
    float        angle_deg;
    br_bike_type bike;
} br_ghost_pose;

/* Draws the world into the frame the caller has already opened. */
void br_scene_draw(br_scene *scene, const br_level *level,
                   const br_camera *cam, const br_bike *bike,
                   const br_ghost_pose *ghost, unsigned time_ms);

#endif /* BR_SCENE_H */
