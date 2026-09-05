#ifndef BR_UI_ART_H
#define BR_UI_ART_H

#include <stddef.h>

#include "../engine/texture.h"

/* The menu artwork, loaded once and shared by every screen.
 *
 * All of it comes from the APK; scripts/extract_assets.sh copies the pieces
 * that are actually drawn into ux0:data/bikerace/ui. Each piece is checked on
 * its own with br_ui_has(), so one missing file costs only the thing it draws
 * and a half-copied asset tree is still navigable. */

typedef struct {
    br_image   background, world_tile, level_tile, level_tile_active;
    br_image   star_on, star_off, logo, back, panel;

    br_texture t_background, t_world_tile, t_level_tile, t_level_tile_active;
    br_texture t_star_on, t_star_off, t_logo, t_back, t_panel;

    int        complete;   /* every file loaded; for logging, not for drawing */
} br_ui_art;

int  br_ui_art_load(br_ui_art *art);
void br_ui_art_free(br_ui_art *art);

static inline int br_ui_has(const br_texture *tex)
{
    return tex->image != NULL && tex->image->id != 0;
}

#endif /* BR_UI_ART_H */
