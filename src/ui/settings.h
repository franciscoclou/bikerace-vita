#ifndef BR_SETTINGS_H
#define BR_SETTINGS_H

#include "../platform/input.h"
#include "art.h"
#include "font.h"
#include "../game/save.h"
#include "optionlist.h"

/* Sound and music toggles, plus a read-only page showing what every button
 * does. Both toggles live in the save file. */

typedef enum {
    BR_SETTINGS_NOTHING = 0,
    BR_SETTINGS_CLOSE
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
