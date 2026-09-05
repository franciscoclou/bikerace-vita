#include "art.h"

#include <stdio.h>
#include <string.h>

#include "../platform/fs.h"
#include "../platform/log.h"

static int load_one(br_image *image, br_texture *tex, const char *file)
{
    char path[256];

    snprintf(path, sizeof(path), "%s/ui/%s", br_asset_root(), file);
    if (br_image_load(image, path) < 0)
        return -1;
    *tex = br_texture_region(image, 0.0f, 0.0f, 1.0f, 1.0f);
    return 0;
}

int br_ui_art_load(br_ui_art *art)
{
    int missing = 0;

    memset(art, 0, sizeof(*art));

    missing += load_one(&art->background, &art->t_background, "fundo.png") < 0;
    missing += load_one(&art->logo, &art->t_logo, "logo.png") < 0;
    missing += load_one(&art->world_tile, &art->t_world_tile,
                        "button_background_default.png") < 0;
    missing += load_one(&art->level_tile, &art->t_level_tile,
                        "button_level_default.png") < 0;
    missing += load_one(&art->level_tile_active, &art->t_level_tile_active,
                        "button_level_pressed.png") < 0;
    missing += load_one(&art->star_on, &art->t_star_on, "star_fill_small.png") < 0;
    missing += load_one(&art->star_off, &art->t_star_off, "star_empty_dark.png") < 0;
    missing += load_one(&art->back, &art->t_back, "button_back_default.png") < 0;
    missing += load_one(&art->panel, &art->t_panel, "pause_fundo.png") < 0;

    art->complete = missing == 0;
    if (!art->complete)
        LOGW("ui art: %d files missing from %s/ui -- screens will fall back to "
             "plain panels", missing, br_asset_root());
    return 0;
}

void br_ui_art_free(br_ui_art *art)
{
    br_image_free(&art->background);
    br_image_free(&art->logo);
    br_image_free(&art->world_tile);
    br_image_free(&art->level_tile);
    br_image_free(&art->level_tile_active);
    br_image_free(&art->star_on);
    br_image_free(&art->star_off);
    br_image_free(&art->back);
    br_image_free(&art->panel);
    memset(art, 0, sizeof(*art));
}
