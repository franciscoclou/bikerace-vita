#include "art.h"

#include <stdio.h>
#include <string.h>

#include "../platform/fs.h"
#include "../engine/render.h"
#include "../platform/log.h"
#include "theme.h"

#define BACK_SIZE 54.0f
#define BACK_X    26.0f
#define BACK_Y    (544.0f - BACK_SIZE - 18.0f)

void br_ui_back_button_rect(float *x, float *y, float *size)
{
    *x = BACK_X;
    *y = BACK_Y;
    *size = BACK_SIZE;
}

int br_ui_back_button_hit(float px, float py)
{
    return px >= BACK_X && px < BACK_X + BACK_SIZE &&
           py >= BACK_Y && py < BACK_Y + BACK_SIZE;
}

void br_ui_back_button_draw(const br_ui_art *art, int pressed)
{
    if (br_ui_has(&art->t_back))
        br_draw_rect(BACK_X, BACK_Y, BACK_X + BACK_SIZE, BACK_Y + BACK_SIZE,
                     &art->t_back, pressed ? &BR_TILE_SEL : NULL);
    else
        br_fill_round_rect(BACK_X, BACK_Y, BACK_SIZE, BACK_SIZE, 12.0f, &BR_PANEL);
}

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
    missing += load_one(&art->icon_controls, &art->t_icon_controls, "button_options_default.png") < 0;
    missing += load_one(&art->tiremarks, &art->t_tiremarks, "display_tiremarks.png") < 0;
    missing += load_one(&art->label, &art->t_label, "display_label.png") < 0;
    missing += load_one(&art->lock, &art->t_lock, "lock_level.png") < 0;
    missing += load_one(&art->toggle_on, &art->t_toggle_on, "button_toggle_on.png") < 0;
    missing += load_one(&art->toggle_off, &art->t_toggle_off, "button_toggle_off.png") < 0;
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
    br_image_free(&art->icon_controls);
    br_image_free(&art->tiremarks);
    br_image_free(&art->label);
    br_image_free(&art->lock);
    br_image_free(&art->toggle_on);
    br_image_free(&art->toggle_off);
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

void br_ui_panel(const br_ui_art *art, float x, float y, float w, float h)
{
    /* Premultiplied, as the blend mode expects. */
    static const br_color mud    = { 0.20f, 0.13f, 0.07f, 1.00f };
    static const br_color lip    = { 0.34f, 0.22f, 0.12f, 1.00f };
    static const br_color shadow = { 0.0f, 0.0f, 0.0f, 0.35f };
    const float frame = 9.0f;
    const float radius = 16.0f;

    br_fill_round_rect(x - frame + 4.0f, y - frame + 6.0f, w + frame * 2.0f,
                       h + frame * 2.0f, radius, &shadow);
    br_fill_round_rect(x - frame, y - frame, w + frame * 2.0f, h + frame * 2.0f,
                       radius, &mud);
    br_fill_round_rect(x - frame + 3.0f, y - frame + 3.0f,
                       w + frame * 2.0f - 6.0f, h + frame * 2.0f - 6.0f,
                       radius - 3.0f, &lip);

    if (br_ui_has(&art->t_panel))
        br_draw_rect(x, y, x + w, y + h, &art->t_panel, NULL);
    else
        br_fill_round_rect(x, y, w, h, 8.0f, &BR_PAPER);
}
