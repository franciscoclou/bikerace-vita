#include "body.h"

#include <string.h>

/* ---------------------------------------------------------------- Body --- */

void br_body_init(br_body *b, const vec2 *pos, const vec2 *vel,
                  float mass, float angle_deg, float ang_vel_deg)
{
    memset(b, 0, sizeof(*b));
    if (pos) v2_copy(&b->pos, pos);
    if (vel) v2_copy(&b->vel, vel);
    b->mass        = mass;
    b->angle_deg   = angle_deg;
    b->ang_vel_deg = ang_vel_deg;
}

void br_body_apply_accel(br_body *b, const vec2 *accel, float dt)
{
    vec2 d = *accel;
    v2_add(&b->vel, v2_scale(&d, dt));
}

void br_body_apply_force(br_body *b, const vec2 *force, float dt)
{
    vec2 a = *force;
    v2_div(&a, b->mass);
    br_body_apply_accel(b, &a, dt);
}

void br_body_step(br_body *b, float dt)
{
    vec2 d = b->vel;
    v2_add(&b->pos, v2_scale(&d, dt));
    b->angle_deg += b->ang_vel_deg * dt;
    b->angle_deg = fmodf(b->angle_deg, 360.0f);
}

/* -------------------------------------------------------- ComposedBody --- */

void br_composed_init(br_composed_body *cb)
{
    memset(cb, 0, sizeof(*cb));
}

int br_composed_add(br_composed_body *cb, br_body *b)
{
    if (cb->count >= BR_COMPOSED_MAX_BODIES)
        return -1;
    cb->bodies[cb->count] = b;
    v2_set(&cb->offsets[cb->count], 0.0f, 0.0f);
    cb->count++;
    return 0;
}

void br_composed_remove(br_composed_body *cb, br_body *b)
{
    int i, j;
    for (i = 0; i < cb->count; i++) {
        if (cb->bodies[i] != b)
            continue;
        for (j = i; j < cb->count - 1; j++) {
            cb->bodies[j]  = cb->bodies[j + 1];
            cb->offsets[j] = cb->offsets[j + 1];
        }
        cb->count--;
        return;
    }
}

void br_composed_update(br_composed_body *cb)
{
    float total_mass = 0.0f;
    vec2 tmp;
    int i;

    v2_set(&cb->com, 0.0f, 0.0f);
    v2_set(&cb->com_vel, 0.0f, 0.0f);

    for (i = 0; i < cb->count; i++) {
        br_body *b = cb->bodies[i];
        v2_add(&cb->com, v2_scale(v2_copy(&tmp, &b->pos), b->mass));
        v2_add(&cb->com_vel, v2_scale(v2_copy(&tmp, &b->vel), b->mass));
        total_mass += b->mass;
    }
    if (total_mass != 0.0f) {
        v2_div(&cb->com, total_mass);
        v2_div(&cb->com_vel, total_mass);
    }

    cb->inertia      = 0.0f;
    cb->ang_momentum = 0.0f;
    for (i = 0; i < cb->count; i++) {
        br_body *b = cb->bodies[i];
        vec2 *off = &cb->offsets[i];
        v2_sub(v2_copy(off, &b->pos), &cb->com);
        cb->inertia      += v2_dot(off, off) * b->mass;
        cb->ang_momentum += b->mass * v2_cross(&b->pos, &b->vel);
    }
    cb->ang_momentum -= v2_cross(&cb->com, &cb->com_vel) * total_mass;
}

float br_composed_torque_response(br_composed_body *cb, float a, float b, float c)
{
    br_composed_update(cb);
    return ((a - cb->ang_momentum) * b * c) / cb->inertia;
}

void br_composed_apply_spin(br_composed_body *cb, float amount)
{
    vec2 tmp;
    int i;
    for (i = 0; i < cb->count; i++) {
        v2_copy(&tmp, &cb->offsets[i]);
        v2_scale(v2_rotate(&tmp, 1.5707964f), amount);
        v2_add(&cb->bodies[i]->vel, &tmp);
    }
}

void br_composed_step(br_composed_body *cb, float dt)
{
    int i;
    for (i = 0; i < cb->count; i++)
        br_body_step(cb->bodies[i], dt);
    br_composed_update(cb);
}

void br_composed_apply_accel(br_composed_body *cb, const vec2 *accel, float dt)
{
    int i;
    for (i = 0; i < cb->count; i++)
        br_body_apply_accel(cb->bodies[i], accel, dt);
}

/* -------------------------------------------------- SpringShockAbsorber --- */

void br_spring_init(br_spring *s, float spring_k, float damping, float rest_len)
{
    s->spring_k = spring_k;
    s->damping  = damping;
    s->rest_len = rest_len;
}

/* Java: private Vector2D a(Body, Body) */
static void spring_force(const br_spring *s, const br_body *a, const br_body *b,
                         vec2 *out, vec2 *axis_out)
{
    vec2 delta, rest, stretch, spring, rel, damp, scratch;

    /* delta = b.pos - a.pos */
    v2_sub(v2_copy(&delta, &b->pos), &a->pos);

    /* rest = delta scaled to the rest length; stretch = delta - rest */
    v2_scale(v2_copy(&rest, &delta), s->rest_len / v2_len(&delta));
    v2_sub(v2_copy(&stretch, &delta), &rest);

    /* Hooke */
    v2_scale(v2_copy(&spring, &stretch), -s->spring_k);

    /* Damping along the spring axis only */
    v2_project(v2_sub(v2_copy(&rel, &b->vel), &a->vel), &delta, &scratch);
    v2_scale(v2_copy(&damp, v2_project(&rel, &delta, &scratch)), -s->damping);

    v2_add(v2_copy(out, &spring), &damp);
    if (axis_out)
        v2_copy(axis_out, &delta);
}

float br_spring_apply(br_spring *s, br_body *a, br_body *b, float dt)
{
    vec2 f;
    spring_force(s, a, b, &f, NULL);
    br_body_apply_force(b, &f, dt);
    v2_scale(&f, -1.0f);
    br_body_apply_force(a, &f, dt);
    return v2_len(&f);
}

float br_spring_apply_axis(br_spring *s, br_body *a, br_body *b,
                           const vec2 *axis, float dt)
{
    vec2 f, scratch;
    float magnitude;

    spring_force(s, a, b, &f, NULL);
    magnitude = v2_len(&f);

    v2_scale(v2_project(&f, axis, &scratch), -1.0f);
    br_body_apply_force(a, &f, dt);
    return magnitude;
}
