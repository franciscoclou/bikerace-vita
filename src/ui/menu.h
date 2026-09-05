#ifndef BR_MENU_H
#define BR_MENU_H

#include "../game/bike.h"
#include "../game/level.h"
#include "../game/save.h"
#include "../platform/input.h"
#include "art.h"
#include "font.h"

/* World and level selection.
 *
 * The Android version built these out of Activities and XML layouts, so there
 * is nothing to translate -- only the artwork carries over. Nothing is locked:
 * every world and level can be picked from the start, and stars are shown
 * rather than spent. */

typedef enum {
    BR_MENU_WORLDS = 0,
    BR_MENU_LEVELS,
    BR_MENU_BIKES
} br_menu_screen;

typedef enum {
    BR_MENU_STAY = 0,
    BR_MENU_PLAY,
    BR_MENU_QUIT
} br_menu_action;

typedef struct {
    br_menu_screen screen;
    br_menu_screen came_from;
    int   world, level;
    int   bike;          /* br_bike_type, kept as int so it can be navigated */

    const br_ui_art *art;

    /* Loaded only while the bike list is open. */
    br_image   bike_image[BR_BIKE_TYPE_COUNT];
    br_texture bike_tex[BR_BIKE_TYPE_COUNT];
    int        bikes_loaded;

    /* Directional auto-repeat. */
    int   held_x, held_y;
    float repeat_delay;

    /* What the current touch went down on, so a release only counts when it
     * comes up on the same thing. -1 for nothing, -2 for the back button. */
    int   touch_target;
} br_menu;

#define BR_TOUCH_NONE (-1)
#define BR_TOUCH_BACK (-2)

void br_menu_init(br_menu *menu, const br_ui_art *art);
void br_menu_free(br_menu *menu);

void br_menu_open_worlds(br_menu *menu);
void br_menu_open_levels(br_menu *menu, int world);
/* Loads the bike artwork; br_menu_close_bikes frees it again. */
void br_menu_open_bikes(br_menu *menu);
void br_menu_close_bikes(br_menu *menu);

br_menu_action br_menu_update(br_menu *menu, const br_input *in, float dt,
                              const br_level_pack *pack);
void br_menu_draw(const br_menu *menu, const br_level_pack *pack,
                  const br_save *save, const br_font *display, const br_font *body);

const char *br_world_name(int world_index);

/* Where a tile is drawn on the screen that is currently open, so a caller can
 * point at it. Returns 0 when the index is off the end. */
int  br_menu_tile_rect(const br_menu *menu, const br_level_pack *pack, int index,
                       float *x, float *y, float *w, float *h);
void br_menu_back_button_rect(float *x, float *y, float *size);

#endif /* BR_MENU_H */
