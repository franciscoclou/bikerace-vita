#ifndef BR_AUDIO_H
#define BR_AUDIO_H

/* A small mixer over sceAudioOut.
 *
 * The output port runs at 48 kHz stereo, which the hardware always accepts,
 * and every voice carries its own source rate so the game's 22 kHz mono effects
 * play without converting the files. Mixing happens on its own thread; the
 * game thread only starts, stops and re-levels voices. */

/* 16-bit mono PCM, as scripts/extract_assets.sh produces it. */
typedef struct {
    short *samples;
    int    frame_count;
    int    sample_rate;
} br_sound;

int  br_sound_load(br_sound *sound, const char *path);
void br_sound_free(br_sound *sound);
static inline float br_sound_seconds(const br_sound *s)
{
    return s->sample_rate > 0 ? (float)s->frame_count / (float)s->sample_rate : 0.0f;
}

int  br_audio_init(void);
void br_audio_shutdown(void);

/* Zero is "no voice", and every call tolerates it. Handles carry a generation
 * so a stale one cannot silence a voice that has since been reused. */
typedef int br_voice;

br_voice br_audio_play(const br_sound *sound, float volume, int loop);
void     br_audio_stop(br_voice voice);
void     br_audio_set_volume(br_voice voice, float volume);
int      br_audio_playing(br_voice voice);
void     br_audio_stop_all(void);

#endif /* BR_AUDIO_H */
