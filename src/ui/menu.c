#include "menu.h"

#include <stdio.h>
#include <string.h>

#include "../engine/render.h"
#include "../game/bike.h"
#include "../platform/fs.h"
#include "../platform/log.h"
#include "glyphs.h"
#include "preview.h"
#include "theme.h"

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

#define BIKE_COLS   7
#define BIKE_TILE_W 124.0f
#define BIKE_TILE_H 104.0f
#define BIKE_GAP     10.0f
#define BIKE_TOP    120.0f

#define BACK_SIZE 54.0f
#define BACK_X    26.0f
#define BACK_Y    (SCREEN_H - BACK_SIZE - 18.0f)

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

static void br_menu_reset_nav(br_menu *menu)
{
    menu->repeat_delay = 0.0f;
    menu->held_x = menu->held_y = 0;
    menu->touch_target = BR_TOUCH_NONE;
}

void br_menu_init(br_menu *menu, const br_ui_art *art)
{
    char path[256];
    int i;

    memset(menu, 0, sizeof(*menu));
    menu->art = art;
    br_menu_reset_nav(menu);

    /* All 21 at once: they are 256x256 each, the world grid needs one of them
     * for its bike cell, and loading them on demand buys nothing. */
    for (i = 0; i < BR_BIKE_TYPE_COUNT; i++) {
        const br_bike_def *def = br_bike_def_for((br_bike_type)i);

        snprintf(path, sizeof(path), "%s/textures/%s", br_asset_root(), def->sprite);
        if (br_image_load(&menu->bike_image[i], path) == 0)
            menu->bike_tex[i] = br_texture_region(&menu->bike_image[i],
                                                  def->sprite_uv[0], def->sprite_uv[1],
                                                  def->sprite_uv[2], def->sprite_uv[3]);
    }
}

void br_menu_free(br_menu *menu)
{
    int i;

    for (i = 0; i < BR_BIKE_TYPE_COUNT; i++)
        br_image_free(&menu->bike_image[i]);
    memset(menu, 0, sizeof(*menu));
}

void br_menu_open_bikes(br_menu *menu)
{
    menu->came_from = menu->screen;
    menu->screen = BR_MENU_BIKES;
    br_menu_reset_nav(menu);
}

/* ---------------------------------------------------------------- layout -- */

static void tile_origin(int index, int columns, float tile_w, float tile_h,
                        float gap, float top, float *x, float *y)
{
    float row_w = (float)columns * tile_w + (float)(columns - 1) * gap;
    float left = (SCREEN_W - row_w) * 0.5f;

    *x = left + (float)(index % columns) * (tile_w + gap);
    *y = top + (float)(index / columns) * (tile_h + gap);
}

static int point_in(float px, float py, float x, float y, float w, float h)
{
    return px >= x && px < x + w && py >= y && py < y + h;
}

/* Which tile the point is over, or BR_TOUCH_NONE. */
static int tile_at(float px, float py, int count, int columns,
                   float tile_w, float tile_h, float gap, float top)
{
    int i;

    for (i = 0; i < count; i++) {
        float x, y;
        tile_origin(i, columns, tile_w, tile_h, gap, top, &x, &y);
        if (point_in(px, py, x, y, tile_w, tile_h))
            return i;
    }
    return BR_TOUCH_NONE;
}

static int world_tile_at(float px, float py, int count)
{
    return tile_at(px, py, count, WORLD_COLS, WORLD_TILE_W, WORLD_TILE_H,
                   WORLD_GAP, WORLD_TOP);
}

static int bike_tile_at(float px, float py, int count)
{
    return tile_at(px, py, count, BIKE_COLS, BIKE_TILE_W, BIKE_TILE_H,
                   BIKE_GAP, BIKE_TOP);
}

static int level_tile_at(float px, float py, int count)
{
    return tile_at(px, py, count, LEVEL_COLS, LEVEL_TILE_W, LEVEL_TILE_H,
                   LEVEL_GAP, LEVEL_TOP);
}

/* How many cells a screen has. The world grid runs one past the last world for
 * the bike cell, and both the cursor and the hit test have to agree on that. */
static int cell_count(const br_menu *menu, const br_level_pack *pack)
{
    switch (menu->screen) {
    case BR_MENU_WORLDS: return pack->world_count + 1;
    case BR_MENU_BIKES:  return BR_BIKE_TYPE_COUNT;
    default:             return pack->worlds[menu->world].level_count;
    }
}

int br_menu_tile_rect(const br_menu *menu, const br_level_pack *pack, int index,
                      float *x, float *y, float *w, float *h)
{
    int worlds = menu->screen == BR_MENU_WORLDS;

    if (index < 0 || index >= cell_count(menu, pack))
        return 0;

    if (menu->screen == BR_MENU_BIKES) {
        *w = BIKE_TILE_W;
        *h = BIKE_TILE_H;
        tile_origin(index, BIKE_COLS, BIKE_TILE_W, BIKE_TILE_H, BIKE_GAP,
                    BIKE_TOP, x, y);
        return 1;
    }

    if (worlds) {
        *w = WORLD_TILE_W;
        *h = WORLD_TILE_H;
        tile_origin(index, WORLD_COLS, WORLD_TILE_W, WORLD_TILE_H, WORLD_GAP,
                    WORLD_TOP, x, y);
    } else {
        *w = LEVEL_TILE_W;
        *h = LEVEL_TILE_H;
        tile_origin(index, LEVEL_COLS, LEVEL_TILE_W, LEVEL_TILE_H, LEVEL_GAP,
                    LEVEL_TOP, x, y);
    }
    return 1;
}

void br_menu_back_button_rect(float *x, float *y, float *size)
{
    *x = BACK_X;
    *y = BACK_Y;
    *size = BACK_SIZE;
}

static int over_back_button(float px, float py)
{
    return point_in(px, py, BACK_X, BACK_Y, BACK_SIZE, BACK_SIZE);
}

/* ---------------------------------------------------------------- update -- */

void br_menu_open_worlds(br_menu *menu)
{
    menu->screen = BR_MENU_WORLDS;
    br_menu_reset_nav(menu);
}

void br_menu_open_levels(br_menu *menu, int world)
{
    menu->screen = BR_MENU_LEVELS;
    menu->world = world;
    br_menu_reset_nav(menu);
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

/* What a screen is a grid of: how many, how wide, and what the cursor is. */
typedef struct {
    int  count;
    int  columns;
    int *selection;
} grid;

static grid grid_for(br_menu *menu, const br_level_pack *pack)
{
    grid g;

    g.count = cell_count(menu, pack);
    switch (menu->screen) {
    case BR_MENU_WORLDS:
        g.columns = WORLD_COLS;
        g.selection = &menu->world;
        break;
    case BR_MENU_BIKES:
        g.columns = BIKE_COLS;
        g.selection = &menu->bike;
        break;
    default:
        g.columns = LEVEL_COLS;
        g.selection = &menu->level;
        break;
    }
    return g;
}

/* Touch selects on the way down and commits on the way up, but only when both
 * land on the same thing -- so a mis-touch can be slid off and released. */
static int touch_target_now(const br_menu *menu, const br_input *in, int count)
{
    switch (menu->screen) {
    case BR_MENU_WORLDS:
        return world_tile_at(in->touch_ui_x, in->touch_ui_y, count);
    case BR_MENU_BIKES:
        if (over_back_button(in->touch_ui_x, in->touch_ui_y))
            return BR_TOUCH_BACK;
        return bike_tile_at(in->touch_ui_x, in->touch_ui_y, count);
    default:
        if (over_back_button(in->touch_ui_x, in->touch_ui_y))
            return BR_TOUCH_BACK;
        return level_tile_at(in->touch_ui_x, in->touch_ui_y, count);
    }
}

br_menu_action br_menu_update(br_menu *menu, const br_input *in, float dt,
                              const br_level_pack *pack, const br_save *save)
{
    grid g = grid_for(menu, pack);
    int commit = 0, back = 0;

    if (*g.selection >= g.count)
        *g.selection = g.count - 1;
    if (*g.selection < 0)
        *g.selection = 0;

    if (repeated(menu, in, dt))
        move_selection(g.selection, g.count, g.columns, in->nav_x, in->nav_y);

    if (in->touch_active) {
        int target = touch_target_now(menu, in, g.count);

        if (in->touch_began)
            menu->touch_target = target;
        if (target >= 0 && target == menu->touch_target)
            *g.selection = target;
    } else if (in->touch_ended) {
        int target = touch_target_now(menu, in, g.count);

        if (target == menu->touch_target) {
            if (target == BR_TOUCH_BACK)
                back = 1;
            else if (target >= 0)
                commit = 1;
        }
        menu->touch_target = BR_TOUCH_NONE;
    }

    if (in->confirm_pressed)
        commit = 1;
    if (in->back_pressed)
        back = 1;

    switch (menu->screen) {
    case BR_MENU_BIKES:
        /* The choice applies as the cursor moves, so either button just
         * returns to wherever the list was opened from. */
        if (commit || back) {
            if (menu->came_from == BR_MENU_LEVELS)
                br_menu_open_levels(menu, menu->world);
            else
                br_menu_open_worlds(menu);
        }
        return BR_MENU_STAY;

    case BR_MENU_WORLDS:
        if (in->bikes_pressed || (commit && menu->world >= pack->world_count)) {
            if (menu->world >= pack->world_count)
                menu->world = pack->world_count - 1;
            br_menu_open_bikes(menu);
            return BR_MENU_STAY;
        }
        if (commit) {
            if (!br_save_world_unlocked(save, menu->world))
                return BR_MENU_STAY;      /* not enough stars yet */
            br_menu_open_levels(menu, menu->world);
            return BR_MENU_STAY;
        }
        return back ? BR_MENU_BACK : BR_MENU_STAY;

    default:
        if (in->bikes_pressed) {
            br_menu_open_bikes(menu);
            return BR_MENU_STAY;
        }
        if (commit)
            return BR_MENU_PLAY;
        if (back)
            br_menu_open_worlds(menu);
        return BR_MENU_STAY;
    }
}

/* ------------------------------------------------------------------ draw -- */

/* Written premultiplied, since that is what the blend mode expects. */
/* Colours all live in ui/theme.h so every screen agrees. Two are local
 * because they only mean anything against the level tiles. */
static const br_color TRACK        = { 0.93f, 0.87f, 0.76f, 1.00f };
static const br_color TRACK_SEL    = { 0.24f, 0.11f, 0.04f, 1.00f };
static const br_color STAR_OFF     = { 0.52f, 0.44f, 0.36f, 1.00f };
static const br_color STAR_OFF_SEL = { 0.42f, 0.24f, 0.10f, 1.00f };

#define SELECT_HALO 5.0f

static void draw_background(const br_menu *menu)
{
    if (br_ui_has(&menu->art->t_background)) {
        br_draw_rect(0.0f, 0.0f, SCREEN_W, SCREEN_H, &menu->art->t_background, NULL);
        br_fill_rect(0.0f, 0.0f, SCREEN_W, SCREEN_H, &BR_SCRIM);
    } else {
        br_fill_rect(0.0f, 0.0f, SCREEN_W, SCREEN_H, &BR_PANEL);
    }
}

/* Selection is shown by drawing the tile's own art again, larger and tinted,
 * behind it. That way the highlight has exactly the tile's rounded shape
 * rather than a square outline around it. */
static void draw_tile(const br_menu *menu, const br_texture *tex, const br_color *tint,
                      float x, float y, float w, float h, int selected)
{
    if (br_ui_has(tex)) {
        if (selected)
            br_draw_rect(x - SELECT_HALO, y - SELECT_HALO,
                         x + w + SELECT_HALO, y + h + SELECT_HALO,
                         tex, &BR_HIGHLIGHT);
        br_draw_rect(x, y, x + w, y + h, tex, tint);
    } else {
        if (selected)
            br_fill_round_rect(x - SELECT_HALO, y - SELECT_HALO,
                               w + SELECT_HALO * 2.0f, h + SELECT_HALO * 2.0f,
                               14.0f, &BR_HIGHLIGHT);
        br_fill_round_rect(x, y, w, h, 12.0f, selected ? &BR_TILE_SEL : &BR_PANEL);
    }
}

static void draw_lock(const br_menu *menu, float cx, float cy, float size)
{
    if (br_ui_has(&menu->art->t_lock))
        br_draw_rect(cx - size * 0.5f, cy - size * 0.5f,
                     cx + size * 0.5f, cy + size * 0.5f, &menu->art->t_lock, NULL);
    else
        br_fill_round_rect(cx - size * 0.3f, cy - size * 0.3f, size * 0.6f,
                           size * 0.6f, size * 0.15f, &BR_INK);
}

static void draw_stars(const br_menu *menu, float x, float y, float size,
                       int earned, const br_color *empty_tint)
{
    int i;

    for (i = 0; i < 3; i++) {
        int filled = i < earned;
        const br_texture *tex = filled ? &menu->art->t_star_on
                                       : &menu->art->t_star_off;
        float sx = x + (float)i * (size + 3.0f);

        if (br_ui_has(tex))
            br_draw_rect(sx, y, sx + size, y + size, tex, filled ? NULL : empty_tint);
        else
            br_fill_rect(sx, y, size, size, filled ? &BR_HIGHLIGHT : empty_tint);
    }
}

/* The bike as it sits in the world: body sprite with a wheel under each end,
 * placed from the same constants the race uses. Everything is measured in
 * world units first and scaled once, so the wheels are inside the box the
 * caller asked for rather than hanging out of the bottom of it. */
static void draw_bike_portrait(const br_menu *menu, br_bike_type type,
                               float cx, float cy, float box_w, float box_h)
{
    const br_bike_def *def = br_bike_def_for(type);
    const br_texture *body = &menu->bike_tex[type];
    const br_texture *wheel = &menu->art->t_wheel[def->wheel];
    float body_w, body_h, half_w, half_h;
    float front_dx, rear_dx, wheel_dy, radius = BR_BIKE_WHEEL_DIAMETER * 0.5f;
    float min_x, max_x, min_y, max_y, scale, origin_x, origin_y;

    if (!br_ui_has(body))
        return;

    /* The sprite's own proportions, at the width the race would draw it. */
    body_w = def->sprite_width;
    body_h = body_w * (br_texture_h(body) * (float)body->image->height) /
                      (br_texture_w(body) * (float)body->image->width);
    half_w = body_w * 0.5f;
    half_h = body_h * 0.5f;

    /* Wheels sit at y = 0.1 and the sprite is centred 0.11 below the head at
     * (0, 0.42), shifted by this bike's own offset. UI y grows downward. */
    front_dx = def->front_x - def->sprite_offset.x;
    rear_dx  = def->rear_x  - def->sprite_offset.x;
    wheel_dy = -(0.1f - (0.42f - 0.11f) - def->sprite_offset.y);

    min_x = -half_w; max_x = half_w;
    min_y = -half_h; max_y = half_h;
    if (rear_dx  - radius < min_x) min_x = rear_dx  - radius;
    if (front_dx - radius < min_x) min_x = front_dx - radius;
    if (rear_dx  + radius > max_x) max_x = rear_dx  + radius;
    if (front_dx + radius > max_x) max_x = front_dx + radius;
    if (wheel_dy - radius < min_y) min_y = wheel_dy - radius;
    if (wheel_dy + radius > max_y) max_y = wheel_dy + radius;

    scale = box_w / (max_x - min_x);
    if (box_h / (max_y - min_y) < scale)
        scale = box_h / (max_y - min_y);

    /* Centre the whole bike, not just its body. */
    origin_x = cx - (min_x + max_x) * 0.5f * scale;
    origin_y = cy - (min_y + max_y) * 0.5f * scale;

    if (br_ui_has(wheel)) {
        float r = radius * scale;
        float y = origin_y + wheel_dy * scale;
        float fx = origin_x + front_dx * scale;
        float rx = origin_x + rear_dx * scale;

        br_draw_rect(fx - r, y - r, fx + r, y + r, wheel, NULL);
        br_draw_rect(rx - r, y - r, rx + r, y + r, wheel, NULL);
    }

    br_draw_rect(origin_x - half_w * scale, origin_y - half_h * scale,
                 origin_x + half_w * scale, origin_y + half_h * scale, body, NULL);
}

static void draw_back_button(const br_menu *menu, const br_font *body,
                             const br_hint *hints, int count)
{
    if (br_ui_has(&menu->art->t_back))
        br_draw_rect(BACK_X, BACK_Y, BACK_X + BACK_SIZE, BACK_Y + BACK_SIZE,
                     &menu->art->t_back,
                     menu->touch_target == BR_TOUCH_BACK ? &BR_TILE_SEL : NULL);
    else
        br_fill_round_rect(BACK_X, BACK_Y, BACK_SIZE, BACK_SIZE, 12.0f, &BR_PANEL);

    br_hints_draw(hints, count, body, BACK_X + BACK_SIZE + 18.0f,
                  BACK_Y + (BACK_SIZE - 26.0f) * 0.5f, 26.0f, &BR_TEXT);
}

static void draw_worlds(const br_menu *menu, const br_level_pack *pack,
                        const br_save *save, const br_font *display,
                        const br_font *body)
{
    static const br_hint hints[] = {
        { BR_BUTTON_CROSS,  "select" },
        { BR_BUTTON_CIRCLE, "back" },
    };
    char text[64];
    int i;

    if (br_ui_has(&menu->art->t_logo)) {
        float logo_h = 62.0f;
        float logo_w = logo_h * (float)menu->art->logo.width /
                                (float)menu->art->logo.height;
        br_draw_rect(24.0f, 12.0f, 24.0f + logo_w, 12.0f + logo_h,
                     &menu->art->t_logo, NULL);
    } else {
        br_font_draw(display, "BIKE RACE", 24.0f, 20.0f, 40.0f, &BR_TEXT);
    }

    snprintf(text, sizeof(text), "%d of %d stars", br_save_total_stars(save),
             pack->world_count * pack->worlds[0].level_count * 3);
    br_font_draw_right(body, text, SCREEN_W - 24.0f, 34.0f, 26.0f, &BR_TEXT_DIM);
    br_ui_tiremarks(menu->art, SCREEN_W * 0.5f, 80.0f, SCREEN_W * 0.86f, 26.0f);

    for (i = 0; i < pack->world_count; i++) {
        int selected = i == menu->world;
        int open = br_save_world_unlocked(save, i);
        float x, y;

        tile_origin(i, WORLD_COLS, WORLD_TILE_W, WORLD_TILE_H, WORLD_GAP,
                    WORLD_TOP, &x, &y);
        draw_tile(menu, &menu->art->t_world_tile,
                  selected ? &BR_TILE_SEL : (open ? NULL : &BR_LOCKED),
                  x, y, WORLD_TILE_W, WORLD_TILE_H, selected);

        snprintf(text, sizeof(text), "%d", pack->worlds[i].id);
        br_font_draw(display, text, x + 12.0f, y + 8.0f, 30.0f, &BR_INK);
        br_font_draw_centered(body, br_world_name(i), x + WORLD_TILE_W * 0.5f,
                              y + 34.0f, 25.0f, &BR_INK);

        if (open) {
            snprintf(text, sizeof(text), "%d/%d", br_save_world_stars(save, i),
                     pack->worlds[i].level_count * 3);
            br_font_draw_centered(body, text, x + WORLD_TILE_W * 0.5f, y + 62.0f,
                                  23.0f, &BR_INK);
        } else {
            /* Say what it costs, not just that it is shut. */
            draw_lock(menu, x + WORLD_TILE_W * 0.5f - 32.0f, y + 70.0f, 30.0f);
            snprintf(text, sizeof(text), "%d", br_save_world_requirement(i));
            br_font_draw(body, text, x + WORLD_TILE_W * 0.5f - 12.0f, y + 58.0f,
                         23.0f, &BR_INK);
            if (br_ui_has(&menu->art->t_star_on))
                br_draw_rect(x + WORLD_TILE_W * 0.5f + 18.0f, y + 58.0f,
                             x + WORLD_TILE_W * 0.5f + 42.0f, y + 82.0f,
                             &menu->art->t_star_on, NULL);
        }
    }

    /* The cell after the last world shows the bike you are riding, and opens
     * the bike list. */
    {
        int selected = menu->world == pack->world_count;
        float x, y;

        tile_origin(pack->world_count, WORLD_COLS, WORLD_TILE_W, WORLD_TILE_H,
                    WORLD_GAP, WORLD_TOP, &x, &y);
        draw_tile(menu, &menu->art->t_world_tile, selected ? &BR_TILE_SEL : NULL,
                  x, y, WORLD_TILE_W, WORLD_TILE_H, selected);
        br_font_draw(body, "Bike", x + 12.0f, y + 6.0f, 22.0f, &BR_INK);
        draw_bike_portrait(menu, (br_bike_type)menu->bike,
                           x + WORLD_TILE_W * 0.5f, y + WORLD_TILE_H * 0.56f,
                           WORLD_TILE_W - 34.0f, WORLD_TILE_H - 34.0f);
    }

    br_hints_draw(hints, 2, body, 24.0f, SCREEN_H - 40.0f, 26.0f, &BR_TEXT);
}

static void draw_levels(const br_menu *menu, const br_level_pack *pack,
                        const br_save *save, const br_font *display,
                        const br_font *body)
{
    static const br_hint hints[] = {
        { BR_BUTTON_CROSS,    "play" },
        { BR_BUTTON_CIRCLE,   "back" },
        { BR_BUTTON_TRIANGLE, "bikes" },
    };
    const br_world *world = &pack->worlds[menu->world];
    char text[64];
    int i;

    br_font_draw(display, br_world_name(menu->world), 32.0f, 22.0f, 40.0f, &BR_TEXT);
    br_ui_tiremarks(menu->art, SCREEN_W * 0.5f, 80.0f, SCREEN_W * 0.86f, 26.0f);

    snprintf(text, sizeof(text), "%d of %d stars",
             br_save_world_stars(save, menu->world), world->level_count * 3);
    br_font_draw_right(body, text, SCREEN_W - 32.0f, 34.0f, 26.0f, &BR_TEXT_DIM);

    for (i = 0; i < world->level_count; i++) {
        const br_level *level = &world->levels[i];
        const br_level_progress *progress =
            br_save_level((br_save *)save, menu->world, i);
        int selected = i == menu->level;
        int open = br_save_level_unlocked(save, menu->world, i);
        float x, y;

        tile_origin(i, LEVEL_COLS, LEVEL_TILE_W, LEVEL_TILE_H, LEVEL_GAP,
                    LEVEL_TOP, &x, &y);
        draw_tile(menu, selected ? &menu->art->t_level_tile_active
                                 : &menu->art->t_level_tile,
                  open ? NULL : &BR_LOCKED, x, y, LEVEL_TILE_W, LEVEL_TILE_H,
                  selected);

        if (!open) {
            draw_lock(menu, x + LEVEL_TILE_W * 0.5f, y + LEVEL_TILE_H * 0.5f, 62.0f);
            snprintf(text, sizeof(text), "%d", i + 1);
            br_font_draw(display, text, x + 14.0f, y + 12.0f, 26.0f, &BR_TEXT_DIM);
            continue;
        }

        /* Header, then the track, then the stars: even bands rather than one
         * crowded at the top and one falling off the bottom. */
        snprintf(text, sizeof(text), "%d", i + 1);
        br_font_draw(display, text, x + 14.0f, y + 12.0f, 26.0f, &BR_TEXT);

        if (progress && progress->best_time > 0.0f) {
            snprintf(text, sizeof(text), "%.2fs", progress->best_time);
            br_font_draw_right(body, text, x + LEVEL_TILE_W - 14.0f, y + 14.0f,
                               21.0f, &BR_TEXT);
        }

        br_preview_draw(&level->boards, x + 16.0f, y + 48.0f,
                        LEVEL_TILE_W - 32.0f, 70.0f, 3.0f,
                        selected ? &TRACK_SEL : &TRACK);

        draw_stars(menu, x + LEVEL_TILE_W * 0.5f - 37.5f, y + 122.0f, 25.0f,
                   progress ? (int)progress->stars : 0,
                   selected ? &STAR_OFF_SEL : &STAR_OFF);
    }

    draw_back_button(menu, body, hints, 3);
}

static void draw_bikes(const br_menu *menu, const br_font *display,
                       const br_font *body)
{
    static const br_hint hints[] = {
        { BR_BUTTON_CROSS,  "pick" },
        { BR_BUTTON_CIRCLE, "back" },
    };
    const br_bike_def *chosen = br_bike_def_for((br_bike_type)menu->bike);
    int i;

    br_font_draw(display, "BIKES", 32.0f, 22.0f, 40.0f, &BR_TEXT);
    br_ui_tiremarks(menu->art, SCREEN_W * 0.5f, 80.0f, SCREEN_W * 0.86f, 26.0f);
    br_font_draw_right(body, chosen->label, SCREEN_W - 32.0f, 34.0f, 28.0f, &BR_TEXT);

    for (i = 0; i < BR_BIKE_TYPE_COUNT; i++) {
        int selected = i == menu->bike;
        float x, y;

        tile_origin(i, BIKE_COLS, BIKE_TILE_W, BIKE_TILE_H, BIKE_GAP, BIKE_TOP,
                    &x, &y);
        draw_tile(menu, selected ? &menu->art->t_level_tile_active
                                 : &menu->art->t_level_tile,
                  NULL, x, y, BIKE_TILE_W, BIKE_TILE_H, selected);
        draw_bike_portrait(menu, (br_bike_type)i,
                           x + BIKE_TILE_W * 0.5f, y + BIKE_TILE_H * 0.5f,
                           BIKE_TILE_W - 22.0f, BIKE_TILE_H - 30.0f);
    }

    draw_back_button(menu, body, hints, 2);
}

void br_menu_draw(const br_menu *menu, const br_level_pack *pack,
                  const br_save *save, const br_font *display, const br_font *body)
{
    br_ui_begin();
    draw_background(menu);

    switch (menu->screen) {
    case BR_MENU_WORLDS: draw_worlds(menu, pack, save, display, body); break;
    case BR_MENU_BIKES:  draw_bikes(menu, display, body);              break;
    default:             draw_levels(menu, pack, save, display, body); break;
    }
}
