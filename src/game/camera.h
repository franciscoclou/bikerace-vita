#ifndef BR_CAMERA_H
#define BR_CAMERA_H

#include "../engine/geom.h"

/* Port of com.topfreegames.bikerace.Camera.
 *
 * `scale` is the visible half-height in world units; half-width is
 * scale * aspect. */
typedef struct {
    vec2    pos;
    br_aabb limits;
    float   scale;
} br_camera;

void  br_camera_reset(br_camera *cam, const br_aabb *limits, float aspect);
/* Smoothing constants come from Game: while racing settle=2.0 and rate=6.0
 * (or 1/dt when the frame is slower than 1/6 s); otherwise settle=4.0 and
 * rate=4.0. Position tracks at `rate`, zoom at `rate/settle`.
 * `lead` shifts the framing ahead of the bike and is set per level. */
void  br_camera_follow(br_camera *cam, const vec2 *target, const vec2 *velocity,
                       float lead, float dt, float settle, float rate,
                       int show_whole_level, float aspect);

#endif /* BR_CAMERA_H */
