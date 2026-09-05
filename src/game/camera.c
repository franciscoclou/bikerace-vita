#include "camera.h"

#include <math.h>

/* Scale that fits the whole level on screen. */
static float fit_scale(const br_aabb *limits, float aspect)
{
    float w = fabsf(br_aabb_width(limits)) / aspect;
    float h = fabsf(br_aabb_height(limits));
    float m = w > h ? w : h;
    return m > 1.4f ? m / 1.94f : 1.0f;
}

void br_camera_reset(br_camera *cam, const br_aabb *limits, float aspect)
{
    cam->limits = *limits;
    cam->scale  = fit_scale(limits, aspect);
    v2_set(&cam->pos,
           (limits->max.x + limits->min.x) * 0.5f,
           (limits->max.y + limits->min.y) * 0.5f);
}

void br_camera_follow(br_camera *cam, const vec2 *target, const vec2 *velocity,
                      float lead, float dt, float settle, float rate,
                      int show_whole_level, float aspect)
{
    vec2 want;
    float want_scale;

    if (show_whole_level) {
        want_scale = fit_scale(&cam->limits, aspect);
        v2_set(&want,
               (cam->limits.max.x + cam->limits.min.x) * 0.5f,
               (cam->limits.max.y + cam->limits.min.y) * 0.5f);
    } else {
        float floor_y;

        /* Pull back as the bike speeds up. */
        want_scale = 2.5f - (1.0f / (1.0f + (v2_len(velocity) / 6.0f)));

        v2_copy(&want, target);
        want.x += cam->scale * lead;
        want.y += (float)(((double)(cam->scale * 1.6f) *
                           atan((double)velocity->y / 6.0)) / 3.141592653589793 / 2.0);

        /* Never look below the bottom of the level. */
        floor_y = (cam->limits.min.y + want_scale) - 0.2f;
        if (want.y < floor_y)
            want.y = floor_y;
    }

    cam->scale += ((want_scale - cam->scale) * rate * dt) / settle;

    v2_sub(&want, &cam->pos);
    v2_scale(&want, rate * dt);
    v2_add(&cam->pos, &want);
}
