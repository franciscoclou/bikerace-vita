#ifndef BR_MENU_H
#define BR_MENU_H

#include "../game/level.h"
#include "../game/save.h"
#include "../platform/input.h"
#include "font.h"

/* World and level selection.
 *
 * The Android version built these out of Activities and XML layouts, so there
 * is nothing to translate -- only the artwork carries over. Nothing is locked:
 * every world and level can be picked from the start, and stars are shown
 * rather than spent. */

typedef enum {
    BR_MENU_WORLDS = 0,
    BR_MENU_LEVELS
} br_menu_screen;

typedef enum {
    BR_MENU_STAY = 0,
    BR_MENU_PLAY,
    BR_MENU_QUIT
} br_menu_action;

typedef struct {
    br_image   background, world_tile, level_tile, level_tile_active;
    br_image   star_on, star_off, logo;
    br_texture t_background, t_world_tile, t_level_tile, t_level_tile_active;
    br_texture t_star_on, t_star_off, t_logo;
    int        have_art;
} br_menu_art;

typedef struct {
    br_menu_screen screen;
    int   world, level;

    br_menu_art art;

    /* Directional auto-repeat. */
    int   held_x, held_y;
    float repeat_delay;
} br_menu;

int  br_menu_init(br_menu *menu);
void br_menu_free(br_menu *menu);

void br_menu_open_worlds(br_menu *menu);
void br_menu_open_levels(br_menu *menu, int world);

br_menu_action br_menu_update(br_menu *menu, const br_input *in, float dt,
                              const br_level_pack *pack);
void br_menu_draw(const br_menu *menu, const br_level_pack *pack,
                  const br_save *save, const br_font *display, const br_font *body);

const char *br_world_name(int world_index);

#endif /* BR_MENU_H */
