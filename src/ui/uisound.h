#ifndef BR_UI_SOUND_H
#define BR_UI_SOUND_H

#include "../game/audio.h"

/* The one way the interface reaches the mixer.
 *
 * Every widget on every screen wants to tick, and none of them has -- or
 * should grow -- a br_game_audio pointer of its own: br_option_list_update()
 * and br_iconbar_update() take an input and a frame time, and that is the
 * whole of their contract. So the app binds the audio once at startup and the
 * widgets call this. It is a global, deliberately and only here.
 *
 * There is one call because there is one sound. Moving the cursor, choosing
 * and backing out all tick the same, and a press that is refused does not tick
 * at all -- that is the only distinction the interface makes. */

void br_ui_sound_bind(br_game_audio *audio);
void br_ui_click(void);

#endif /* BR_UI_SOUND_H */
