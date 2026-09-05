#include "menu.h"

#include <stdio.h>
#include <string.h>

#include "../engine/render.h"
#include "../platform/fs.h"
#include "../platform/log.h"
#include "preview.h"

#define SCREEN_W 960.0f
#define SCREEN_H 544.0f

#define WORLD_COLS   5
#define WORLD_TILE_W 168.0f
#define WORLD_TILE_H  92.0f
#define WORLD_GAP     12.0f
#define WORLD_TOP     92.0f

#define LEVEL_COLS   4
#define LEVEL_TILE_W 200.0f
#define LEVEL_TILE_H 160.0f
#define LEVEL_GAP     16.0f
#define LEVEL_TOP     96.0f

#define REPEAT_FIRST 0.32f
#define REPEAT_NEXT  0.10f

/* Names as the game itself has them, from its string table. */
static const char *s_world_names[] = {
    "Desert", "Arctic", "Dunes", "Hills", "Beach", "Savanna",
    "Desert 2", "Arctic 2", "Dunes 2", "Hills 2", "Beach 2", "Savanna 2",
    "Holiday", "Holiday 2", "Special", "Halloween", "Thanksgiving",
    "Holiday 3", "Easter",
};
#define WORLD_NAME_COUNT ((int)(sizeof(s_world_names) / sizeof(s_world_names[0])))

const char *br_world_name(int world_index)
{
    if (world_index < 0 || world_index >= WORLD_NAME_COUNT)
        return "World";
    return s_world_names[world_index];
}

/* ------------------------------------------------------------------- art -- */

static int load_art(br_image *image, br_texture *tex, const char *file)
{
    char path[256];

    snprintf(path, sizeof(path), "%s/ui/%s", br_asset_root(), file);
    if (br_image_load(image, path) < 0)
        return -1;
    *tex = br_texture_region(image, 0.0f, 0.0f, 1.0f, 1.0f);
    return 0;
}

int br_menu_init(br_menu *menu)
{
    br_menu_art *art;
    int missing = 0;

    memset(menu, 0, sizeof(*menu));
    art = &menu->art;

    missing += load_art(&art->background, &art->t_background, "fundo.png") < 0;
    missing += load_art(&art->logo, &art->t_logo, "logo.png") < 0;
    missing += load_art(&art->world_tile, &art->t_world_tile,
                        "button_background_default.png") < 0;
    missing += load_art(&art->level_tile, &art->t_level_tile,
                        "button_level_default.png") < 0;
    missing += load_art(&art->level_tile_active, &art->t_level_tile_active,
                        "button_level_pressed.png") < 0;
    missing += load_art(&art->star_on, &art->t_star_on, "star_fill_small.png") < 0;
    missing += load_art(&art->star_off, &art->t_star_off, "star_empty_dark.png") < 0;

    art->have_art = missing == 0;
    if (!art->have_art)
        LOGW("menu: %d art files missing from %s/ui -- falling back to plain "
             "panels", missing, br_asset_root());
    return 0;
}

void br_menu_free(br_menu *menu)
{
    br_menu_art *art = &menu->art;

    br_image_free(&art->background);
    br_image_free(&art->logo);
    br_image_free(&art->world_tile);
    br_image_free(&art->level_tile);
    br_image_free(&art->level_tile_active);
    br_image_free(&art->star_on);
    br_image_free(&art->star_off);
    memset(menu, 0, sizeof(*menu));
}

/* ---------------------------------------------------------------- update -- */

void br_menu_open_worlds(br_menu *menu)
{
    menu->screen = BR_MENU_WORLDS;
    menu->repeat_delay = 0.0f;
    menu->held_x = menu->held_y = 0;
}

void br_menu_open_levels(br_menu *menu, int world)
{
    menu->screen = BR_MENU_LEVELS;
    menu->world = world;
    menu->repeat_delay = 0.0f;
    menu->held_x = menu->held_y = 0;
}

/* True on the frame a direction is first pushed, and again while it is held. */
static int repeated(br_menu *menu, const br_input *in, float dt)
{
    if (in->nav_x != menu->held_x || in->nav_y != menu->held_y) {
        menu->held_x = in->nav_x;
        menu->held_y = in->nav_y;
        menu->repeat_delay = REPEAT_FIRST;
        return in->nav_x != 0 || in->nav_y != 0;
    }
    if (in->nav_x == 0 && in->nav_y == 0)
        return 0;

    menu->repeat_delay -= dt;
    if (menu->repeat_delay <= 0.0f) {
        menu->repeat_delay = REPEAT_NEXT;
        return 1;
    }
    return 0;
}

static void move_selection(int *index, int count, int columns, int dx, int dy)
{
    int next = *index;

    if (dx) {
        next += dx;
        if (next < 0)      next = count - 1;
        if (next >= count) next = 0;
    }
    if (dy) {
        next += dy * columns;
        if (next < 0)      next += ((count + columns - 1) / columns) * columns;
        if (next >= count) next -= ((count + columns - 1) / columns) * columns;
        if (next < 0 || next >= count)
            next = *index;
    }
    *index = next;
}

br_menu_action br_menu_update(br_menu *menu, const br_input *in, float dt,
                              const br_level_pack *pack)
{
    int step = repeated(menu, in, dt);

    if (menu->screen == BR_MENU_WORLDS) {
        if (step)
            move_selection(&menu->world, pack->world_count, WORLD_COLS,
                           in->nav_x, in->nav_y);
        if (in->confirm_pressed) {
            br_menu_open_levels(menu, menu->world);
            return BR_MENU_STAY;
        }
        if (in->back_pressed)
            return BR_MENU_QUIT;
        return BR_MENU_STAY;
    }

    {
        int count = pack->worlds[menu->world].level_count;

        if (menu->level >= count)
            menu->level = count - 1;
        if (step)
            move_selection(&menu->level, count, LEVEL_COLS, in->nav_x, in->nav_y);
        if (in->confirm_pressed)
            return BR_MENU_PLAY;
        if (in->back_pressed) {
            br_menu_open_worlds(menu);
            return BR_MENU_STAY;
        }
    }
    return BR_MENU_STAY;
}

/* ------------------------------------------------------------------ draw -- */

/* Written premultiplied, since that is what the blend mode expects. */
static const br_color DIM   = { 0.06f * 0.55f, 0.06f * 0.55f, 0.09f * 0.55f, 0.55f };
static const br_color PANEL = { 0.11f * 0.80f, 0.12f * 0.80f, 0.16f * 0.80f, 0.80f };
static const br_color HIGHLIGHT = { 1.00f, 0.72f, 0.16f, 1.00f };
/* Text over the dimmed background. */
static const br_color TEXT      = { 0.99f, 0.97f, 0.92f, 1.00f };
static const br_color TEXT_DIM  = { 0.72f, 0.70f, 0.66f, 1.00f };
/* Text over the pale world tiles. */
static const br_color INK       = { 0.30f, 0.17f, 0.09f, 1.00f };
/* Level tiles are dark brown until selected, when they turn orange, so the
 * track line and the unearned stars swap contrast along with them. */
static const br_color TRACK        = { 0.93f, 0.87f, 0.76f, 1.00f };
static const br_color TRACK_SEL    = { 0.24f, 0.11f, 0.04f, 1.00f };
static const br_color STAR_OFF     = { 0.52f, 0.44f, 0.36f, 1.00f };
static const br_color STAR_OFF_SEL = { 0.42f, 0.24f, 0.10f, 1.00f };
/* Warms the pale world tile so the selected one reads at a glance, the way
 * the level tiles already do by swapping art. */
static const br_color TILE_SEL     = { 1.00f, 0.74f, 0.34f, 1.00f };

static void draw_background(const br_menu *menu)
{
    if (menu->art.have_art) {
        br_draw_rect(0.0f, 0.0f, SCREEN_W, SCREEN_H, &menu->art.t_background, NULL);
        br_fill_rect(0.0f, 0.0f, SCREEN_W, SCREEN_H, &DIM);
    } else {
        br_fill_rect(0.0f, 0.0f, SCREEN_W, SCREEN_H, &PANEL);
    }
}

static void draw_tile(const br_menu *menu, const br_texture *tex, const br_color *tint,
                      float x, float y, float w, float h, int selected)
{
    if (menu->art.have_art && tex->image) {
        br_draw_rect(x, y, x + w, y + h, tex, tint);
    } else {
        br_fill_rect(x, y, w, h, selected ? &TILE_SEL : &PANEL);
    }
    if (selected) {
        float t = 3.0f;
        br_fill_rect(x - t, y - t, w + t * 2.0f, t, &HIGHLIGHT);
        br_fill_rect(x - t, y + h, w + t * 2.0f, t, &HIGHLIGHT);
        br_fill_rect(x - t, y, t, h, &HIGHLIGHT);
        br_fill_rect(x + w, y, t, h, &HIGHLIGHT);
    }
}

/* `earned` of three, drawn left to right from `x`. */
static void draw_stars(const br_menu *menu, float x, float y, float size,
                       int earned, const br_color *empty_tint)
{
    int i;

    for (i = 0; i < 3; i++) {
        int filled = i < earned;
        const br_texture *tex = filled ? &menu->art.t_star_on
                                       : &menu->art.t_star_off;
        float sx = x + (float)i * (size + 3.0f);

        if (menu->art.have_art && tex->image)
            br_draw_rect(sx, y, sx + size, y + size, tex,
                         filled ? NULL : empty_tint);
        else
            br_fill_rect(sx, y, size, size, filled ? &HIGHLIGHT : empty_tint);
    }
}

static void tile_origin(int index, int columns, float tile_w, float tile_h,
                        float gap, float top, float *x, float *y)
{
    float row_w = (float)columns * tile_w + (float)(columns - 1) * gap;
    float left = (SCREEN_W - row_w) * 0.5f;

    *x = left + (float)(index % columns) * (tile_w + gap);
    *y = top + (float)(index / columns) * (tile_h + gap);
}

static void draw_worlds(const br_menu *menu, const br_level_pack *pack,
                        const br_save *save, const br_font *display,
                        const br_font *body)
{
    char text[64];
    int i;

    if (menu->art.have_art) {
        float logo_h = 62.0f;
        float logo_w = logo_h * (float)menu->art.logo.width /
                                (float)menu->art.logo.height;
        br_draw_rect(24.0f, 12.0f, 24.0f + logo_w, 12.0f + logo_h,
                     &menu->art.t_logo, NULL);
    } else {
        br_font_draw(display, "BIKE RACE", 24.0f, 20.0f, 40.0f, &TEXT);
    }

    snprintf(text, sizeof(text), "%d of %d stars", br_save_total_stars(save),
             pack->world_count * pack->worlds[0].level_count * 3);
    br_font_draw_right(body, text, SCREEN_W - 24.0f, 34.0f, 26.0f, &TEXT_DIM);

    for (i = 0; i < pack->world_count; i++) {
        int selected = i == menu->world;
        float x, y;

        tile_origin(i, WORLD_COLS, WORLD_TILE_W, WORLD_TILE_H, WORLD_GAP,
                    WORLD_TOP, &x, &y);
        draw_tile(menu, &menu->art.t_world_tile, selected ? &TILE_SEL : NULL,
                  x, y, WORLD_TILE_W, WORLD_TILE_H, selected);

        snprintf(text, sizeof(text), "%d", pack->worlds[i].id);
        br_font_draw(display, text, x + 12.0f, y + 8.0f, 30.0f, &INK);
        br_font_draw_centered(body, br_world_name(i), x + WORLD_TILE_W * 0.5f,
                              y + 34.0f, 25.0f, &INK);

        snprintf(text, sizeof(text), "%d/%d", br_save_world_stars(save, i),
                 pack->worlds[i].level_count * 3);
        br_font_draw_centered(body, text, x + WORLD_TILE_W * 0.5f, y + 62.0f,
                              23.0f, &INK);
    }

    br_font_draw(body, "Cross  select      Circle  quit", 24.0f,
                 SCREEN_H - 34.0f, 22.0f, &TEXT);
}

static void draw_levels(const br_menu *menu, const br_level_pack *pack,
                        const br_save *save, const br_font *display,
                        const br_font *body)
{
    const br_world *world = &pack->worlds[menu->world];
    char text[64];
    int i;

    snprintf(text, sizeof(text), "%s", br_world_name(menu->world));
    br_font_draw(display, text, 32.0f, 22.0f, 40.0f, &TEXT);

    snprintf(text, sizeof(text), "%d of %d stars", br_save_world_stars(save, menu->world),
             world->level_count * 3);
    br_font_draw_right(body, text, SCREEN_W - 32.0f, 34.0f, 26.0f, &TEXT_DIM);

    for (i = 0; i < world->level_count; i++) {
        const br_level *level = &world->levels[i];
        const br_level_progress *progress =
            br_save_level((br_save *)save, menu->world, i);
        int selected = i == menu->level;
        float x, y;

        tile_origin(i, LEVEL_COLS, LEVEL_TILE_W, LEVEL_TILE_H, LEVEL_GAP,
                    LEVEL_TOP, &x, &y);
        draw_tile(menu, selected ? &menu->art.t_level_tile_active
                                 : &menu->art.t_level_tile,
                  NULL, x, y, LEVEL_TILE_W, LEVEL_TILE_H, selected);

        /* The real track, drawn from the level's own geometry. */
        br_preview_draw(&level->boards, x + 16.0f, y + 34.0f,
                        LEVEL_TILE_W - 32.0f, LEVEL_TILE_H - 74.0f, 3.0f,
                        selected ? &TRACK_SEL : &TRACK);

        snprintf(text, sizeof(text), "%d", i + 1);
        br_font_draw(display, text, x + 12.0f, y + 6.0f, 28.0f, &TEXT);

        if (progress && progress->best_time > 0.0f) {
            snprintf(text, sizeof(text), "%.2fs", progress->best_time);
            br_font_draw_right(body, text, x + LEVEL_TILE_W - 12.0f, y + 10.0f,
                               22.0f, &TEXT);
        }

        draw_stars(menu, x + LEVEL_TILE_W * 0.5f - 33.0f,
                   y + LEVEL_TILE_H - 30.0f, 22.0f,
                   progress ? (int)progress->stars : 0,
                   selected ? &STAR_OFF_SEL : &STAR_OFF);
    }

    br_font_draw(body, "Cross  play      Circle  back", 32.0f,
                 SCREEN_H - 34.0f, 22.0f, &TEXT);
}

void br_menu_draw(const br_menu *menu, const br_level_pack *pack,
                  const br_save *save, const br_font *display, const br_font *body)
{
    br_ui_begin();
    draw_background(menu);

    if (menu->screen == BR_MENU_WORLDS)
        draw_worlds(menu, pack, save, display, body);
    else
        draw_levels(menu, pack, save, display, body);
}
