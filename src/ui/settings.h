#ifndef BR_SETTINGS_H
#define BR_SETTINGS_H

#include "../platform/input.h"
#include "art.h"
#include "font.h"
#include "../game/save.h"
#include "optionlist.h"

/* Sound and music toggles, plus a read-only page showing what every button
 * does. Both toggles live in the save file.
 *
 * Every state here can also be left by touch alone, through the same back
 * button the menus use (ui/art.c), because the screen is reachable from a
 * start screen that can itself be driven entirely by touch. */

typedef enum {
    BR_SETTINGS_NOTHING = 0,
    BR_SETTINGS_CLOSE,
    /* The save has just been wiped. Ghosts are progress too, but they live in
     * their own file and the app owns it, so the app finishes the job. */
    BR_SETTINGS_RESET
} br_settings_action;

/* Reset and unlock-everything both change the save for good, so each asks
 * first, with the harmless answer selected. */
typedef enum {
    BR_CONFIRM_NONE = 0,
    BR_CONFIRM_UNLOCK,
    BR_CONFIRM_RESET
} br_settings_confirm;

typedef struct {
    const br_ui_art *art;
    br_option_list   list;
    br_option_list   confirm_list;
    int   showing_controls;
    br_settings_confirm confirming;
    /* Whether the current touch went down on the back button. */
    int   back_touch;

    br_save *save;
} br_settings;

void br_settings_init(br_settings *settings, const br_ui_art *art);
/* Works directly on the save for as long as the screen is open. */
void br_settings_open(br_settings *settings, br_save *save);

br_settings_action br_settings_update(br_settings *settings, const br_input *in,
                                      float dt);
void br_settings_draw(const br_settings *settings, const br_font *display,
                      const br_font *body);

#endif /* BR_SETTINGS_H */
