#include "uisound.h"

static br_game_audio *s_audio;

void br_ui_sound_bind(br_game_audio *audio)
{
    s_audio = audio;
}

/* Silence before the audio is bound, and after it is torn down, is not an
 * error: the host tests drive screens with no app around them. */
static void play(br_ui_sound kind)
{
    if (s_audio)
        br_game_audio_ui(s_audio, kind);
}

void br_ui_sound_move(void)   { play(BR_UI_SOUND_MOVE); }
void br_ui_sound_select(void) { play(BR_UI_SOUND_SELECT); }
void br_ui_sound_back(void)   { play(BR_UI_SOUND_BACK); }
