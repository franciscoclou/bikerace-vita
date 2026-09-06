#include "art.h"

#include <stdio.h>
#include <string.h>

#include "../platform/fs.h"
#include "../engine/render.h"
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
    missing += load_one(&art->start_screen, &art->t_start_screen, "start_screen.png") < 0;
    missing += load_one(&art->result, &art->t_result, "result_window.png") < 0;
    missing += load_one(&art->icon_retry, &art->t_icon_retry, "button_retry_default.png") < 0;
    missing += load_one(&art->icon_next, &art->t_icon_next, "button_next_default.png") < 0;
    missing += load_one(&art->icon_list, &art->t_icon_list, "button_menu_default.png") < 0;
    missing += load_one(&art->icon_play, &art->t_icon_play, "button_play_final_default.png") < 0;
    missing += load_one(&art->icon_options, &art->t_icon_options, "button_options_default.png") < 0;
    missing += load_one(&art->icon_ok, &art->t_icon_ok, "button_ok_default.png") < 0;
    missing += load_one(&art->tiremarks, &art->t_tiremarks, "display_tiremarks.png") < 0;
    missing += load_one(&art->label, &art->t_label, "display_label.png") < 0;
    missing += load_one(&art->sign, &art->t_sign, "placa_start.png") < 0;
    missing += load_one(&art->wheel[BR_WHEEL_STANDARD],  &art->t_wheel[BR_WHEEL_STANDARD],  "wheel.png") < 0;
    missing += load_one(&art->wheel[BR_WHEEL_ULTRA],     &art->t_wheel[BR_WHEEL_ULTRA],     "wheel_ultra.png") < 0;
    missing += load_one(&art->wheel[BR_WHEEL_HALLOWEEN], &art->t_wheel[BR_WHEEL_HALLOWEEN], "wheel_halloween.png") < 0;
    missing += load_one(&art->wheel[BR_WHEEL_SANTA],     &art->t_wheel[BR_WHEEL_SANTA],     "wheel_santa.png") < 0;

    art->complete = missing == 0;
    if (!art->complete)
        LOGW("ui art: %d files missing from %s/ui -- screens will fall back to "
             "plain panels", missing, br_asset_root());
    return 0;
}

void br_ui_art_free(br_ui_art *art)
{
    int i;

    br_image_free(&art->background);
    br_image_free(&art->logo);
    br_image_free(&art->world_tile);
    br_image_free(&art->level_tile);
    br_image_free(&art->level_tile_active);
    br_image_free(&art->star_on);
    br_image_free(&art->star_off);
    br_image_free(&art->back);
    br_image_free(&art->panel);
    br_image_free(&art->start_screen);
    br_image_free(&art->result);
    br_image_free(&art->icon_retry);
    br_image_free(&art->icon_next);
    br_image_free(&art->icon_list);
    br_image_free(&art->icon_play);
    br_image_free(&art->icon_options);
    br_image_free(&art->icon_ok);
    br_image_free(&art->tiremarks);
    br_image_free(&art->label);
    br_image_free(&art->sign);
    for (i = 0; i < 4; i++)
        br_image_free(&art->wheel[i]);
    memset(art, 0, sizeof(*art));
}

void br_ui_tiremarks(const br_ui_art *art, float centre_x, float y,
                     float width, float height)
{
    /* Premultiplied, so the alpha is in the colour as well. */
    static const br_color fade = { 0.45f, 0.45f, 0.45f, 0.45f };

    if (!br_ui_has(&art->t_tiremarks))
        return;
    br_draw_rect(centre_x - width * 0.5f, y - height * 0.5f,
                 centre_x + width * 0.5f, y + height * 0.5f,
                 &art->t_tiremarks, &fade);
}
