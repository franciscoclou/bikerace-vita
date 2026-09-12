#include "fs.h"

#include <stdio.h>

#include "log.h"

#ifdef BR_HOST_TEST
#include <stdlib.h>
#endif

const char *br_asset_root(void)
{
#ifdef BR_HOST_TEST
    /* The host tests load the real converted assets out of assets_out/. */
    const char *override = getenv("BR_ASSET_ROOT");
    if (override)
        return override;
#endif
    return "ux0:data/bikerace";
}

int br_fs_replace(const char *tmp, const char *path)
{
    remove(path);
    if (rename(tmp, path) != 0) {
        LOGE("fs: wrote %s but could not put it in place of %s", tmp, path);
        return -1;
    }
    return 0;
}

FILE *br_fs_open_saved(const char *path, const char *tmp)
{
    FILE *f = fopen(path, "rb");

    if (f)
        return f;

    f = fopen(tmp, "rb");
    if (f)
        LOGW("fs: %s is missing -- a write was interrupted, reading %s instead",
             path, tmp);
    return f;
}
