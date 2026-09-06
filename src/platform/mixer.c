#include "mixer.h"

#include <string.h>

/* Playback position is a whole frame count plus a 16.16 fraction, deliberately
 * not one 16.16 number. A single 16.16 value can only address 65535 frames --
 * three seconds at 22 kHz -- so anything longer never reaches its own end: it
 * wraps at 32 bits and restarts, which made the four-second win sting repeat
 * for ever and the three-minute menu track play its first three seconds on a
 * loop. Splitting the two removes the ceiling entirely. */
typedef struct {
    const br_sound *sound;
    unsigned        frame;       /* whole frames played */
    unsigned        frac;        /* 0..65535, part of a frame */
    unsigned        step_frame;  /* whole frames per output frame */
    unsigned        step_frac;   /* and the fraction of one */
    int             volume;      /* 0..256 */
    int             loop;
    int             active;
    int             generation;
} voice;

static voice s_voices[BR_MIXER_VOICES];
static int   s_generation = 1;

static int voice_index(br_voice handle) { return (handle & 0xffff) - 1; }
static int voice_gen(br_voice handle)   { return handle >> 16; }

static voice *resolve(br_voice handle)
{
    int index = voice_index(handle);

    if (handle == 0 || index < 0 || index >= BR_MIXER_VOICES)
        return NULL;
    if (s_voices[index].generation != voice_gen(handle))
        return NULL;
    return &s_voices[index];
}

void br_mixer_reset(void)
{
    memset(s_voices, 0, sizeof(s_voices));
    s_generation = 1;
}

static int clamp_volume(float volume)
{
    int scaled = (int)(volume * 256.0f);

    if (scaled < 0)   scaled = 0;
    if (scaled > 256) scaled = 256;
    return scaled;
}

br_voice br_mixer_play(const br_sound *sound, float volume, int loop, int out_rate)
{
    unsigned step;
    int i;

    if (!sound || !sound->samples || sound->frame_count <= 0 || out_rate <= 0)
        return 0;

    for (i = 0; i < BR_MIXER_VOICES; i++)
        if (!s_voices[i].active)
            break;
    if (i == BR_MIXER_VOICES)
        return 0;

    /* How far to walk the source per output frame, as 16.16. */
    step = (unsigned)(((unsigned long long)sound->sample_rate << 16) /
                      (unsigned)out_rate);

    s_voices[i].sound      = sound;
    s_voices[i].frame      = 0;
    s_voices[i].frac       = 0;
    s_voices[i].step_frame = step >> 16;
    s_voices[i].step_frac  = step & 0xffff;
    s_voices[i].volume     = clamp_volume(volume);
    s_voices[i].loop       = loop;
    s_voices[i].active     = 1;
    s_voices[i].generation = s_generation++;
    if (s_generation > 0x7fff)
        s_generation = 1;

    return ((br_voice)s_voices[i].generation << 16) | (br_voice)(i + 1);
}

void br_mixer_stop(br_voice handle)
{
    voice *v = resolve(handle);
    if (v)
        v->active = 0;
}

void br_mixer_set_volume(br_voice handle, float volume)
{
    voice *v = resolve(handle);
    if (v)
        v->volume = clamp_volume(volume);
}

int br_mixer_playing(br_voice handle)
{
    voice *v = resolve(handle);
    return v && v->active;
}

void br_mixer_stop_all(void)
{
    int i;
    for (i = 0; i < BR_MIXER_VOICES; i++)
        s_voices[i].active = 0;
}

void br_mixer_render(short *out, int frames)
{
    int i, f;

    memset(out, 0, (size_t)frames * 2 * sizeof(short));

    for (i = 0; i < BR_MIXER_VOICES; i++) {
        voice *v = &s_voices[i];
        unsigned last;

        if (!v->active || !v->sound || !v->sound->samples)
            continue;
        last = (unsigned)v->sound->frame_count;

        for (f = 0; f < frames; f++) {
            int sample, mixed;

            if (v->frame >= last) {
                if (!v->loop) {
                    v->active = 0;
                    break;
                }
                /* A step is always well under one whole sound, so wrapping
                 * once is enough; the guard is for a pathological rate. */
                do {
                    v->frame -= last;
                } while (v->frame >= last);
            }

            sample = (v->sound->samples[v->frame] * v->volume) >> 8;

            mixed = out[f * 2] + sample;
            if (mixed >  32767) mixed =  32767;
            if (mixed < -32768) mixed = -32768;
            out[f * 2] = (short)mixed;
            out[f * 2 + 1] = (short)mixed;

            v->frac += v->step_frac;
            v->frame += v->step_frame + (v->frac >> 16);
            v->frac &= 0xffff;
        }
    }
}
