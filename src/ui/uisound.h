#ifndef BR_UI_SOUND_H
#define BR_UI_SOUND_H

#include "../game/audio.h"

/* The one way the interface reaches the mixer.
 *
 * Every widget on every screen wants to click, and none of them has -- or
 * should grow -- a br_game_audio pointer of its own: br_option_list_update()
 * and br_iconbar_update() take an input and a frame time, and that is the
 * whole of their contract. So the app binds the audio once at startup and the
 * widgets call these. It is a global, deliberately and only here. */

void br_ui_sound_bind(br_game_audio *audio);

void br_ui_sound_move(void);     /* the cursor stepped */
void br_ui_sound_select(void);   /* something was chosen */
void br_ui_sound_back(void);     /* a screen was left */

#endif /* BR_UI_SOUND_H */
