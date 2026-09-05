/* Bike Race -- PS Vita port. */

#include <psp2/kernel/clib.h>
#include <psp2/kernel/processmgr.h>
#include <psp2/ctrl.h>
#include <psp2/power.h>

#include <vitaGL.h>

#include "engine/render.h"
#include "game/game.h"
#include "platform/fs.h"
#include "platform/input.h"
#include "platform/log.h"

#define GL_POOL_SIZE (16 * 1024 * 1024)
#define START_WORLD  0
#define START_LEVEL  0

static br_game  g_game;
static br_input g_input;

static void log_frame(const br_game *game, unsigned frame, float dt)
{
    const br_bike *bike = &game->bike;
    static const char *states[] = { "waiting", "running", "finished", "dead" };

    LOGI("f=%u dt=%.4f %s t=%.2f | head(%.2f,%.2f) v(%.2f,%.2f) ang=%.1f "
         "| ground r=%d f=%d | cam(%.2f,%.2f) s=%.2f | in a=%d b=%d lean=%.2f",
         frame, dt, states[game->state], game->elapsed,
         bike->head.pos.x, bike->head.pos.y,
         bike->chassis.com_vel.x, bike->chassis.com_vel.y,
         br_bike_angle_deg(bike),
         game->rear_grounded, game->front_grounded,
         game->camera.pos.x, game->camera.pos.y, game->camera.scale,
         g_input.accelerate, g_input.brake, g_input.lean);
}

int main(void)
{
    unsigned frame = 0;
    uint64_t prev_us;

    scePowerSetArmClockFrequency(444);
    scePowerSetBusClockFrequency(222);
    scePowerSetGpuClockFrequency(222);
    scePowerSetGpuXbarClockFrequency(166);

    br_log_init();

    vglInit(GL_POOL_SIZE);
    vglWaitVblankStart(GL_TRUE);
    br_render_init();
    br_input_init();

    if (br_game_init(&g_game) < 0) {
        LOGE("fatal: game init failed");
        goto done;
    }
    if (br_game_load(&g_game, START_WORLD, START_LEVEL) < 0) {
        LOGE("fatal: could not load the first level -- are the textures on the "
             "Vita under %s/textures ?", br_asset_root());
        goto done;
    }

    LOGI("entering main loop");
    prev_us = sceKernelGetProcessTimeWide();

    for (;;) {
        uint64_t now_us = sceKernelGetProcessTimeWide();
        float dt = (float)(now_us - prev_us) / 1000000.0f;
        prev_us = now_us;

        br_input_poll(&g_input);
        if ((g_input.buttons & (SCE_CTRL_START | SCE_CTRL_SELECT)) ==
            (SCE_CTRL_START | SCE_CTRL_SELECT)) {
            LOGI("START+SELECT held -- exiting");
            break;
        }

        br_game_update(&g_game, &g_input, dt);
        br_game_draw(&g_game, (unsigned)(now_us / 1000));

        if ((frame % 60) == 0)
            log_frame(&g_game, frame, dt);
        frame++;
    }

    LOGI("shutting down after %u frames", frame);
    br_game_free(&g_game);

done:
    br_log_shutdown();
    sceKernelExitProcess(0);
    return 0;
}
