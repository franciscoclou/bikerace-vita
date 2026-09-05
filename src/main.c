/* Bike Race -- PS Vita port.
 *
 * This is the boot shell: it brings up logging, graphics and input, then runs
 * a smoke-test scene that exercises the ported physics core. Gameplay gets
 * layered on top of this loop as it is ported over from decompiled/. */

#include <psp2/kernel/processmgr.h>
#include <psp2/kernel/clib.h>
#include <psp2/power.h>
#include <psp2/ctrl.h>

#include <vitaGL.h>

#include <stdio.h>
#include <string.h>

#include "platform/log.h"
#include "platform/input.h"
#include "engine/body.h"

#define SCREEN_W 960
#define SCREEN_H 544

/* The Android build worked in metres with y-up; keep those units so the level
 * data lifted out of LevelFactoryWorld*.java can be used verbatim. */
#define WORLD_UNITS_ACROSS 20.0f
#define GRAVITY_Y (-9.81f)

static br_input g_input;

static struct {
    br_body          wheels[2];
    br_composed_body chassis;
    br_spring        axle;
} g_demo;

static void demo_init(void)
{
    vec2 p, v;

    br_body_init(&g_demo.wheels[0], v2_set(&p, -1.0f, 4.0f), v2_set(&v, 0, 0),
                 1.0f, 0.0f, 0.0f);
    br_body_init(&g_demo.wheels[1], v2_set(&p,  1.0f, 4.0f), v2_set(&v, 0, 0),
                 1.0f, 0.0f, 0.0f);

    br_composed_init(&g_demo.chassis);
    br_composed_add(&g_demo.chassis, &g_demo.wheels[0]);
    br_composed_add(&g_demo.chassis, &g_demo.wheels[1]);
    br_composed_update(&g_demo.chassis);

    br_spring_init(&g_demo.axle, 120.0f, 8.0f, 2.0f);

    LOGI("demo scene ready: %d bodies, inertia=%.4f",
         g_demo.chassis.count, g_demo.chassis.inertia);
}

static void demo_step(float dt)
{
    vec2 gravity;
    int i;

    v2_set(&gravity, 0.0f, GRAVITY_Y);
    br_composed_apply_accel(&g_demo.chassis, &gravity, dt);
    br_spring_apply(&g_demo.axle, &g_demo.wheels[0], &g_demo.wheels[1], dt);

    if (g_input.accelerate)
        br_composed_apply_spin(&g_demo.chassis, -4.0f * dt);
    if (g_input.brake)
        br_composed_apply_spin(&g_demo.chassis,  4.0f * dt);

    br_composed_step(&g_demo.chassis, dt);

    /* Floor at y = 0, so the demo stays on screen. */
    for (i = 0; i < 2; i++) {
        if (g_demo.wheels[i].pos.y < 0.4f) {
            g_demo.wheels[i].pos.y = 0.4f;
            if (g_demo.wheels[i].vel.y < 0.0f)
                g_demo.wheels[i].vel.y *= -0.35f;
            g_demo.wheels[i].vel.x *= 0.98f;
        }
    }
}

static void draw_disc(float cx, float cy, float r, int segments)
{
    static GLfloat verts[3 * 66];
    int i, n = 0;

    verts[n++] = cx; verts[n++] = cy; verts[n++] = 0.0f;
    for (i = 0; i <= segments; i++) {
        float a = (float)i / (float)segments * 6.2831853f;
        verts[n++] = cx + cosf(a) * r;
        verts[n++] = cy + sinf(a) * r;
        verts[n++] = 0.0f;
    }
    glVertexPointer(3, GL_FLOAT, 0, verts);
    glDrawArrays(GL_TRIANGLE_FAN, 0, segments + 2);
}

static void demo_draw(void)
{
    int i;

    glClearColor(0.10f, 0.12f, 0.18f, 1.0f);
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    {
        float half_w = WORLD_UNITS_ACROSS * 0.5f;
        float half_h = half_w * (float)SCREEN_H / (float)SCREEN_W;
        glOrtho(-half_w, half_w, -1.0f, half_h * 2.0f - 1.0f, -1.0f, 1.0f);
    }
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();

    glEnableClientState(GL_VERTEX_ARRAY);

    /* Ground line. */
    glColor4f(0.25f, 0.55f, 0.30f, 1.0f);
    {
        GLfloat ground[] = {
            -20.0f, 0.0f, 0.0f,  20.0f, 0.0f, 0.0f,
            -20.0f, 0.4f, 0.0f,  20.0f, 0.4f, 0.0f,
        };
        glVertexPointer(3, GL_FLOAT, 0, ground);
        glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
    }

    /* Wheels. */
    glColor4f(0.90f, 0.75f, 0.20f, 1.0f);
    for (i = 0; i < 2; i++)
        draw_disc(g_demo.wheels[i].pos.x, g_demo.wheels[i].pos.y, 0.4f, 24);

    /* Centre of mass. */
    glColor4f(0.95f, 0.25f, 0.25f, 1.0f);
    draw_disc(g_demo.chassis.com.x, g_demo.chassis.com.y, 0.12f, 12);

    glDisableClientState(GL_VERTEX_ARRAY);
}

int main(void)
{
    unsigned int frame = 0;
    uint64_t prev_us, now_us;

    scePowerSetArmClockFrequency(444);
    scePowerSetBusClockFrequency(222);
    scePowerSetGpuClockFrequency(222);
    scePowerSetGpuXbarClockFrequency(166);

    br_log_init();

    vglInit(0x800000);
    vglWaitVblankStart(GL_TRUE);
    LOGI("vitaGL up (%dx%d)", SCREEN_W, SCREEN_H);

    br_input_init();
    demo_init();

    prev_us = sceKernelGetProcessTimeWide();

    for (;;) {
        float dt;

        now_us = sceKernelGetProcessTimeWide();
        dt = (float)(now_us - prev_us) / 1000000.0f;
        prev_us = now_us;
        if (dt > 0.05f)     /* clamp after a stall so physics stays stable */
            dt = 0.05f;

        br_input_poll(&g_input);

        if (g_input.buttons == (SCE_CTRL_START | SCE_CTRL_SELECT)) {
            LOGI("START+SELECT held -- exiting");
            break;
        }

        demo_step(dt);
        demo_draw();
        vglSwapBuffers(GL_FALSE);

        if ((frame % 120) == 0) {
            LOGI("frame=%u dt=%.4f com=(%.3f, %.3f) accel=%d brake=%d lean=%.2f touches=%d",
                 frame, dt, g_demo.chassis.com.x, g_demo.chassis.com.y,
                 g_input.accelerate, g_input.brake, g_input.lean,
                 g_input.touch_count);
        }
        frame++;
    }

    LOGI("shutting down after %u frames", frame);
    br_log_shutdown();
    /* vitaGL has no teardown entry point; the process exit reclaims it. */
    sceKernelExitProcess(0);
    return 0;
}
