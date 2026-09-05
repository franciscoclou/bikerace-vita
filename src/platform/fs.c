#include "fs.h"

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
