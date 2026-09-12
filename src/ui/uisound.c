#include "uisound.h"

static br_game_audio *s_audio;

void br_ui_sound_bind(br_game_audio *audio)
{
    s_audio = audio;
}

/* Silence before the audio is bound, and after it is torn down, is not an
 * error: the host tests drive screens with no app around them. */
void br_ui_click(void)
{
    if (s_audio)
        br_game_audio_ui_click(s_audio);
}
