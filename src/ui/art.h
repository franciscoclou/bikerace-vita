#ifndef BR_UI_ART_H
#define BR_UI_ART_H

#include <stddef.h>

#include "../engine/texture.h"
#include "../game/bike.h"

/* The menu artwork, loaded once and shared by every screen.
 *
 * All of it comes from the APK; scripts/extract_assets.sh copies the pieces
 * that are actually drawn into ux0:data/bikerace/ui. Each piece is checked on
 * its own with br_ui_has(), so one missing file costs only the thing it draws
 * and a half-copied asset tree is still navigable. */

typedef struct {
    br_image   background, world_tile, level_tile, level_tile_active;
    br_image   star_on, star_off, logo, back, panel, start_screen, result;

    br_texture t_background, t_world_tile, t_level_tile, t_level_tile_active;
    br_texture t_star_on, t_star_off, t_logo, t_back, t_panel;
    br_texture t_start_screen, t_result;

    /* Wheels, indexed by br_wheel_kind. Cropped out of a world atlas by
     * scripts/extract_assets.sh so the menus never load one. */
    br_image   wheel[4];
    br_texture t_wheel[4];

    int        complete;   /* every file loaded; for logging, not for drawing */
} br_ui_art;

int  br_ui_art_load(br_ui_art *art);
void br_ui_art_free(br_ui_art *art);

static inline int br_ui_has(const br_texture *tex)
{
    return tex->image != NULL && tex->image->id != 0;
}

#endif /* BR_UI_ART_H */
