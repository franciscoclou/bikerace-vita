#ifndef BR_MIXER_H
#define BR_MIXER_H

#include "audio.h"

/* The mixing itself, with no platform in it, so the host tests exercise the
 * real thing rather than a stand-in. src/platform/audio.c owns the output port
 * and the thread and calls in here; nothing in this file locks, so the caller
 * serialises access. */

#define BR_MIXER_VOICES 12

void     br_mixer_reset(void);
br_voice br_mixer_play(const br_sound *sound, float volume, int loop, int out_rate);
void     br_mixer_stop(br_voice voice);
void     br_mixer_set_volume(br_voice voice, float volume);
int      br_mixer_playing(br_voice voice);
void     br_mixer_stop_all(void);

/* Renders `frames` interleaved stereo frames, replacing whatever is in `out`. */
void     br_mixer_render(short *out, int frames);

#endif /* BR_MIXER_H */
