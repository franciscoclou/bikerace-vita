#include "device.h"

#include "log.h"

#ifdef BR_HOST_TEST
#include <stdlib.h>
#else
#include <psp2/kernel/sysmem.h>
#endif

/* A fraction of each edge. 2.5% is inside what a set that overscans at all
 * typically takes (2 to 5%), and small enough that the layout is the one the
 * handheld gets rather than a shrunken copy of it. A television that crops
 * harder than this has a display-area setting of its own, and so does the
 * console. */
#define TV_EDGE_INSET 0.025f

static int s_known;
static int s_is_tv;

int br_device_is_tv(void)
{
    if (!s_known) {
#ifdef BR_HOST_TEST
        /* So the host tests and the screenshot pass can render both layouts. */
        s_is_tv = getenv("BR_FAKE_TV") != NULL;
#else
        s_is_tv = sceKernelIsPSVitaTV() == 1;
#endif
        s_known = 1;
        LOGI("device: %s", s_is_tv ? "PlayStation TV -- no touch panel, "
                                     "interface kept inside a title-safe area"
                                   : "PS Vita handheld");
    }
    return s_is_tv;
}

float br_device_ui_inset(void)
{
    return br_device_is_tv() ? TV_EDGE_INSET : 0.0f;
}
