#ifndef BR_SETTINGS_H
#define BR_SETTINGS_H

#include "../platform/input.h"
#include "art.h"
#include "font.h"
#include "optionlist.h"

/* Sound and music toggles, plus a read-only page showing what every button
 * does. Both toggles live in the save file. */

typedef enum {
    BR_SETTINGS_NOTHING = 0,
    BR_SETTINGS_CLOSE
} br_settings_action;

typedef struct {
    const br_ui_art *art;
    br_option_list   list;
    int   showing_controls;

    int  *sound_on;      /* pointed at the save's fields */
    int  *music_on;
    int  *changed;
} br_settings;

void br_settings_init(br_settings *settings, const br_ui_art *art);
/* Binds the toggles to the caller's storage for as long as the screen is open. */
void br_settings_open(br_settings *settings, int *sound_on, int *music_on,
                      int *changed);

br_settings_action br_settings_update(br_settings *settings, const br_input *in,
                                      float dt);
void br_settings_draw(const br_settings *settings, const br_font *display,
                      const br_font *body);

#endif /* BR_SETTINGS_H */
