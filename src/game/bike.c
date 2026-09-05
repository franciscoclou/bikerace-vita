#include "bike.h"

#include <math.h>
#include <string.h>

#include "../engine/geom.h"

#define WHEEL_RADIUS   0.1f
#define WHEEL_BOX      0.2f
#define HEAD_BOX       0.38f
#define HEAD_KILL_DIST 0.05f
#define RIDER_HEIGHT   0.32f
#define GROUND_MASS    1.0e9f

/* Constants per bike, from Bike.java's factory methods, Game.java's gravity
 * table and GameSceneDirector's sprite tables. Only the bikes reachable in
 * single player are listed; the rest are shop and World Cup variants. */
static const br_bike_def s_defs[BR_BIKE_TYPE_COUNT] = {
    { "regular",   "m00.png",  44.0f,  16.0f, 12.0f, 1.0f, 2000.0f,
      0.17f,  -0.2f,   0, 0, 0, -4.0f, 0.53866667f, {  0.0f,   -0.01f  },
      { 0.0f, 0.0f, 0.7890625f,  0.6640625f  } },
    { "kids",      "m05.png",  44.0f,  16.0f, 12.0f, 1.0f, 2000.0f,
      0.17f,  -0.2f,   1, 1, 0, -3.0f, 0.50666666f, {  0.02f,  -0.01f  },
      { 0.0f, 0.0f, 0.7890625f,  0.703125f   } },
    { "super",     "m09.png", 132.0f,  32.0f, 12.0f, 1.0f, 2000.0f,
      0.17f,  -0.2f,   1, 1, 0, -4.0f, 0.61333334f, { -0.04f,   0.015f },
      { 0.0f, 0.0f, 0.8984375f,  0.6640625f  } },
    { "acrobatic", "m07.png",  44.0f,  16.0f, 12.0f, 1.5f, 2000.0f,
      0.17f,  -0.2f,   0, 0, 0, -4.0f, 0.765625f,   { -0.0235f, 0.0f   },
      { 0.0f, 0.0f, 0.765625f,   0.63671875f } },
    { "ultra",     "m06.png", 154.0f,  40.0f, 24.0f, 2.0f, 2000.0f,
      0.25f, -0.285f,  1, 0, 0, -6.0f, 0.71466666f, {  0.01f,  -0.033f },
      { 0.0f, 0.0f, 1.0f,        0.67578125f } },
    { "ghost",     "m13.png",  44.0f,  16.0f, 12.0f, 1.0f, 2000.0f,
      0.17f,  -0.2f,   1, 0, 0, -4.0f, 0.53866667f, {  0.0f,   -0.01f  },
      { 0.0f, 0.0f, 0.78125f,    0.6640625f  } },
};

const br_bike_def *br_bike_def_for(br_bike_type type)
{
    if ((unsigned)type >= (unsigned)BR_BIKE_TYPE_COUNT)
        type = BR_BIKE_REGULAR;
    return &s_defs[type];
}

void br_bike_init(br_bike *bike, br_bike_type type, const vec2 *spawn)
{
    const br_bike_def *def = br_bike_def_for(type);
    vec2 p, zero;

    memset(bike, 0, sizeof(*bike));
    bike->def   = def;
    bike->type  = type;
    bike->state = BR_BIKE_IDLE;

    v2_set(&zero, 0.0f, 0.0f);

    v2_add(v2_set(&p, 0.0f, 0.42f), spawn);
    br_body_init(&bike->head, &p, &zero, 1.0f, 0.0f, 0.0f);

    v2_add(v2_set(&p, def->front_x, 0.1f), spawn);
    br_body_init(&bike->front, &p, &zero, 1.0f, 0.0f, 0.0f);

    v2_add(v2_set(&p, def->rear_x, 0.1f), spawn);
    br_body_init(&bike->rear, &p, &zero, 1.0f, 0.0f, 0.0f);

    br_composed_init(&bike->chassis);
    br_composed_add(&bike->chassis, &bike->head);
    br_composed_add(&bike->chassis, &bike->front);
    br_composed_add(&bike->chassis, &bike->rear);
    br_composed_update(&bike->chassis);

    br_spring_init(&bike->head_front, 1600.0f, 40.0f,
                   sqrtf(RIDER_HEIGHT * RIDER_HEIGHT + def->front_x * def->front_x));
    br_spring_init(&bike->head_rear, 1600.0f, 40.0f,
                   sqrtf(RIDER_HEIGHT * RIDER_HEIGHT + def->rear_x * def->rear_x));
    br_spring_init(&bike->axle, 1600.0f, 40.0f, def->front_x - def->rear_x);
    br_spring_init(&bike->contact, 4900.0f, 112.0f, WHEEL_RADIUS);

    br_body_init(&bike->ground, &zero, &zero, GROUND_MASS, 0.0f, 0.0f);
}

void br_bike_set_state(br_bike *bike, br_bike_state state)
{
    bike->state = state;
    switch (state) {
    case BR_BIKE_ACCELERATING:
        bike->rear.ang_vel_deg = -bike->def->wheel_spin;
        break;
    case BR_BIKE_BRAKING:
        if (bike->def->reverse_on_brake) {
            bike->rear.ang_vel_deg = bike->def->wheel_spin;
        } else {
            bike->rear.ang_vel_deg  = 0.0f;
            bike->front.ang_vel_deg = 0.0f;
        }
        break;
    case BR_BIKE_REVERSING:
        bike->rear.ang_vel_deg = bike->def->wheel_spin;
        break;
    case BR_BIKE_CRASHED:
        /* The rider comes off: the head stops being part of the assembly. */
        br_composed_remove(&bike->chassis, &bike->head);
        break;
    case BR_BIKE_IDLE:
        break;
    }
}

void br_bike_apply_gravity(br_bike *bike, const vec2 *gravity, float dt)
{
    br_composed_apply_accel(&bike->chassis, gravity, dt);
}

void br_bike_apply_springs(br_bike *bike, float dt)
{
    if (bike->state == BR_BIKE_CRASHED)
        return;
    br_spring_apply(&bike->head_rear,  &bike->head, &bike->rear,  dt);
    br_spring_apply(&bike->head_front, &bike->head, &bike->front, dt);
    br_spring_apply(&bike->axle,       &bike->rear, &bike->front, dt);
}

void br_bike_step(br_bike *bike, float dt)
{
    br_composed_step(&bike->chassis, dt);
}

void br_bike_apply_lean(br_bike *bike, float input, float dt, int blocked)
{
    float spin;

    if (bike->state == BR_BIKE_CRASHED)
        return;

    spin = br_composed_torque_response(&bike->chassis,
                                       bike->def->lean * input, 15.0f, dt);
    if (!blocked || spin >= 0.0f)
        br_composed_apply_spin(&bike->chassis, spin);
}

static int body_near_board(const br_body *body, const br_board *board, float margin)
{
    br_aabb box;
    br_aabb_from_segment(&box, &board->v1, &board->v2, margin);
    return br_aabb_contains(&box, body->pos.x, body->pos.y);
}

/* Resolve one point mass against one track segment, and drive or brake it
 * along that segment. Returns the contact force magnitude. */
static float resolve(br_bike *bike, br_body *body, const br_board *board,
                     float dt, int drive, int brake, int reverse)
{
    const br_bike_def *def = bike->def;
    vec2 contact, delta, normal, along, push;
    float force, length, speed_along;

    contact = br_closest_point_on_segment(&body->pos, &board->v1, &board->v2);

    v2_sub(v2_copy(&delta, &body->pos), &contact);
    if (v2_len(&delta) >= WHEEL_RADIUS)
        return 0.0f;

    v2_set(&normal, board->v1.y - board->v2.y, board->v2.x - board->v1.x);
    v2_copy(&bike->ground.pos, &contact);
    force = br_spring_apply_axis(&bike->contact, body, &bike->ground, &normal, dt);

    v2_sub(v2_copy(&along, &board->v2), &board->v1);
    length = v2_len(&along);
    speed_along = v2_dot(&along, &body->vel);

    if (drive || reverse) {
        float amount;

        /* Constant torque until the wheel is up to speed, then taper so the
         * bike stops accelerating past its cap. */
        if (fabsf((speed_along * def->drive) / length) < def->drive_cap)
            amount = def->drive / length;
        else
            amount = def->drive_cap / fabsf(speed_along);

        if (br_side(&board->v1, &board->v2, &body->pos) < 0.0f)
            amount = -amount;
        if (reverse)
            amount = -amount;

        v2_scale(v2_copy(&push, &along), amount * dt);
        v2_add(&body->vel, &push);
    } else if (brake) {
        v2_scale(v2_copy(&push, &along),
                 (speed_along > 0.0f ? -1.0f : 1.0f) * (def->brake / length) * dt);
        v2_add(&body->vel, &push);
    }

    /* Spin the wheel sprite to match how fast it is travelling over the
     * surface it is resting on. */
    v2_sub(v2_copy(&delta, &body->pos), &contact);
    body->ang_vel_deg = (float)(180.0 / 3.14159265358979323846 *
        (double)((delta.x * body->vel.y - delta.y * body->vel.x) /
                 (delta.y * delta.y + delta.x * delta.x)));

    return force;
}

float br_bike_collide_rear(br_bike *bike, const br_board *board, float dt)
{
    int reversing = bike->state == BR_BIKE_REVERSING ||
                    (bike->state == BR_BIKE_BRAKING && bike->def->reverse_on_brake);

    if (!body_near_board(&bike->rear, board, WHEEL_BOX))
        return 0.0f;
    return resolve(bike, &bike->rear, board, dt,
                   bike->state == BR_BIKE_ACCELERATING,
                   bike->state == BR_BIKE_BRAKING && !bike->def->reverse_on_brake,
                   reversing);
}

float br_bike_collide_front(br_bike *bike, const br_board *board, float dt)
{
    if (!body_near_board(&bike->front, board, WHEEL_BOX))
        return 0.0f;
    return resolve(bike, &bike->front, board, dt, 0,
                   bike->state == BR_BIKE_BRAKING && !bike->def->reverse_on_brake, 0);
}

float br_bike_collide_head(br_bike *bike, const br_board *board, float dt)
{
    if (!bike->def->head_collides || !body_near_board(&bike->head, board, HEAD_BOX))
        return 0.0f;
    return resolve(bike, &bike->head, board, dt, 0, 1, 0);
}

int br_bike_head_hits(br_bike *bike, const br_board *board)
{
    vec2 contact, delta;

    if (bike->state == BR_BIKE_CRASHED || bike->def->invulnerable ||
        !body_near_board(&bike->head, board, HEAD_BOX))
        return 0;

    /* Either the board cuts through the rider's body, or it is close enough to
     * the head itself. */
    if (br_segments_intersect(&board->v1, &board->v2, &bike->head.pos, &bike->front.pos) ||
        br_segments_intersect(&bike->head.pos, &bike->rear.pos, &board->v1, &board->v2))
        return 1;

    contact = br_closest_point_on_segment(&bike->head.pos, &board->v1, &board->v2);
    v2_sub(v2_copy(&delta, &contact), &bike->head.pos);
    return v2_len(&delta) <= HEAD_KILL_DIST;
}

void br_bike_bounds(const br_bike *bike, br_aabb *out, float margin)
{
    const vec2 *h = &bike->head.pos, *f = &bike->front.pos, *r = &bike->rear.pos;
    float min_x = h->x, max_x = h->x, min_y = h->y, max_y = h->y;

    if (f->x < min_x) min_x = f->x;
    if (f->x > max_x) max_x = f->x;
    if (r->x < min_x) min_x = r->x;
    if (r->x > max_x) max_x = r->x;
    if (f->y < min_y) min_y = f->y;
    if (f->y > max_y) max_y = f->y;
    if (r->y < min_y) min_y = r->y;
    if (r->y > max_y) max_y = r->y;

    br_aabb_set(out, min_x - margin, min_y - margin, max_x + margin, max_y + margin);
}

float br_bike_angle_deg(const br_bike *bike)
{
    return (float)(180.0 / 3.14159265358979323846 *
        atan2((double)(bike->front.pos.y - bike->rear.pos.y),
              (double)(bike->front.pos.x - bike->rear.pos.x)));
}
