/* Bike Race -- PS Vita port. */

#include <psp2/kernel/clib.h>
#include <psp2/kernel/processmgr.h>
#include <psp2/ctrl.h>
#include <psp2/power.h>

#include <vitaGL.h>

#include "app.h"
#include "engine/render.h"
#include "platform/fs.h"
#include "platform/input.h"
#include "platform/log.h"

#define GL_POOL_SIZE (16 * 1024 * 1024)

static br_app   g_app;
static br_input g_input;

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

    if (br_app_init(&g_app) < 0) {
        LOGE("fatal: startup failed -- are the assets under %s ?", br_asset_root());
        goto done;
    }

    LOGI("entering main loop");
    prev_us = sceKernelGetProcessTimeWide();

    for (;;) {
        uint64_t now_us = sceKernelGetProcessTimeWide();
        float dt = (float)(now_us - prev_us) / 1000000.0f;
        prev_us = now_us;

        /* Nothing in the game closes it by accident: leaving is the Exit
         * option on the start screen, or the Vita's own home button. */
        br_input_poll(&g_input);
        br_app_update(&g_app, &g_input, dt);
        if (br_app_should_quit(&g_app))
            break;

        br_app_draw(&g_app, (unsigned)(now_us / 1000));
        frame++;
    }

    LOGI("shutting down after %u frames", frame);
    br_app_free(&g_app);

done:
    br_log_shutdown();
    sceKernelExitProcess(0);
    return 0;
}
