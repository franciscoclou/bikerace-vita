#ifndef BR_BODY_H
#define BR_BODY_H

/* Ports of the three physics classes the original engine has:
 *   Body                 (com.topfreegames.engine.c.a)
 *   ComposedBody         (com.topfreegames.engine.c.b)
 *   SpringShockAbsorber  (com.topfreegames.engine.c.c)
 *
 * This is not a general rigid-body solver. Each Body is a point mass with a
 * decorative rotation; a bike is a ComposedBody of point masses held together
 * by springs. Reproducing it exactly is what makes the ported game feel right,
 * so resist "improving" the maths here. */

#include "vec2.h"

typedef struct {
    vec2  pos;          /* Java field a */
    vec2  vel;          /* Java field b */
    float mass;         /* Java field c */
    float angle_deg;    /* Java field d -- degrees, wrapped to (-360, 360) */
    float ang_vel_deg;  /* Java field e -- degrees per second */
} br_body;

void  br_body_init(br_body *b, const vec2 *pos, const vec2 *vel,
                   float mass, float angle_deg, float ang_vel_deg);
/* accel is an acceleration, applied over dt. (Java: a(Vector2D, float)) */
void  br_body_apply_accel(br_body *b, const vec2 *accel, float dt);
/* force is divided by mass first. (Java: b(Vector2D, float)) */
void  br_body_apply_force(br_body *b, const vec2 *force, float dt);
/* Advance position and angle. (Java: a(float)) */
void  br_body_step(br_body *b, float dt);

#define BR_COMPOSED_MAX_BODIES 16

typedef struct {
    br_body *bodies[BR_COMPOSED_MAX_BODIES];
    vec2     offsets[BR_COMPOSED_MAX_BODIES]; /* body position - centre of mass */
    int      count;

    vec2  com;          /* Java field b -- centre of mass */
    vec2  com_vel;      /* Java field c -- velocity of the centre of mass */
    float inertia;      /* Java field d -- sum(m * |offset|^2) */
    float ang_momentum; /* Java field e */
} br_composed_body;

void  br_composed_init(br_composed_body *cb);
int   br_composed_add(br_composed_body *cb, br_body *b);
void  br_composed_remove(br_composed_body *cb, br_body *b);
/* Recompute com/com_vel/inertia/ang_momentum. (Java: a()) */
void  br_composed_update(br_composed_body *cb);
/* (Java: a(float, float, float)) */
float br_composed_torque_response(br_composed_body *cb, float a, float b, float c);
/* Spin the assembly about its centre of mass. (Java: a(float)) */
void  br_composed_apply_spin(br_composed_body *cb, float amount);
/* Step every member, then recompute. (Java: b(float)) */
void  br_composed_step(br_composed_body *cb, float dt);
/* (Java: a(Vector2D, float)) */
void  br_composed_apply_accel(br_composed_body *cb, const vec2 *accel, float dt);

typedef struct {
    float spring_k;   /* Java field a */
    float damping;    /* Java field b */
    float rest_len;   /* Java field c */
} br_spring;

void  br_spring_init(br_spring *s, float spring_k, float damping, float rest_len);
/* Push a and b apart/together. Returns the magnitude of the applied force.
 * (Java: a(Body, Body, float)) */
float br_spring_apply(br_spring *s, br_body *a, br_body *b, float dt);
/* Apply only to `a`, constrained to the `axis` direction. Returns the
 * unconstrained force magnitude. (Java: a(Body, Body, Vector2D, float)) */
float br_spring_apply_axis(br_spring *s, br_body *a, br_body *b,
                           const vec2 *axis, float dt);

#endif /* BR_BODY_H */
