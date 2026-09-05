#include "audio.h"

#include <psp2/audioout.h>
#include <psp2/kernel/threadmgr.h>

#include <string.h>

#include "log.h"

#define OUT_RATE    48000
#define OUT_GRAIN   1024          /* frames per output call; must be a multiple of 64 */
#define MAX_VOICES  12
#define FIXED_ONE   65536         /* 16.16 resampling position */

typedef struct {
    const br_sound *sound;
    unsigned        position;     /* 16.16 frames into the sound */
    unsigned        step;         /* 16.16 source frames per output frame */
    int             volume;       /* 0..256 */
    int             loop;
    int             active;
    int             generation;
} voice;

static voice  s_voices[MAX_VOICES];
static short  s_mix[OUT_GRAIN * 2];
static int    s_port = -1;
static SceUID s_thread = -1;
static SceUID s_mutex = -1;
static int    s_running;
static int    s_generation = 1;

/* ---------------------------------------------------------------- mixing -- */

static void lock(void)   { if (s_mutex >= 0) sceKernelLockMutex(s_mutex, 1, NULL); }
static void unlock(void) { if (s_mutex >= 0) sceKernelUnlockMutex(s_mutex, 1); }

static void mix_grain(void)
{
    int i, f;

    memset(s_mix, 0, sizeof(s_mix));

    lock();
    for (i = 0; i < MAX_VOICES; i++) {
        voice *v = &s_voices[i];
        unsigned last;

        if (!v->active || !v->sound || !v->sound->samples)
            continue;
        last = (unsigned)v->sound->frame_count;

        for (f = 0; f < OUT_GRAIN; f++) {
            unsigned index = v->position >> 16;
            int sample, mixed;

            if (index >= last) {
                if (!v->loop) {
                    v->active = 0;
                    break;
                }
                v->position -= last << 16;
                index = v->position >> 16;
                if (index >= last) {
                    v->active = 0;
                    break;
                }
            }

            sample = (v->sound->samples[index] * v->volume) >> 8;

            mixed = s_mix[f * 2] + sample;
            if (mixed >  32767) mixed =  32767;
            if (mixed < -32768) mixed = -32768;
            s_mix[f * 2] = (short)mixed;
            s_mix[f * 2 + 1] = (short)mixed;

            v->position += v->step;
        }
    }
    unlock();
}

static int mixer_thread(SceSize args, void *argp)
{
    (void)args;
    (void)argp;

    while (s_running) {
        mix_grain();
        sceAudioOutOutput(s_port, s_mix);
    }
    return 0;
}

int br_audio_init(void)
{
    s_port = sceAudioOutOpenPort(SCE_AUDIO_OUT_PORT_TYPE_BGM, OUT_GRAIN,
                                 OUT_RATE, SCE_AUDIO_OUT_MODE_STEREO);
    if (s_port < 0) {
        LOGE("audio: could not open an output port (0x%08X)", (unsigned)s_port);
        return -1;
    }

    {
        int volume[2] = { SCE_AUDIO_VOLUME_0DB, SCE_AUDIO_VOLUME_0DB };
        sceAudioOutSetVolume(s_port,
                             SCE_AUDIO_VOLUME_FLAG_L_CH | SCE_AUDIO_VOLUME_FLAG_R_CH,
                             volume);
    }

    s_mutex = sceKernelCreateMutex("br_audio", 0, 0, NULL);
    s_running = 1;
    s_thread = sceKernelCreateThread("br_audio_mixer", mixer_thread,
                                     0x10000100, 0x10000, 0, 0, NULL);
    if (s_thread < 0) {
        LOGE("audio: could not create the mixer thread");
        s_running = 0;
        sceAudioOutReleasePort(s_port);
        s_port = -1;
        return -1;
    }
    sceKernelStartThread(s_thread, 0, NULL);

    LOGI("audio: %d Hz stereo out, %d frames per grain, %d voices",
         OUT_RATE, OUT_GRAIN, MAX_VOICES);
    return 0;
}

void br_audio_shutdown(void)
{
    if (s_port < 0)
        return;

    br_audio_stop_all();
    s_running = 0;
    if (s_thread >= 0) {
        sceKernelWaitThreadEnd(s_thread, NULL, NULL);
        sceKernelDeleteThread(s_thread);
        s_thread = -1;
    }
    sceAudioOutReleasePort(s_port);
    s_port = -1;
    if (s_mutex >= 0) {
        sceKernelDeleteMutex(s_mutex);
        s_mutex = -1;
    }
}

/* ---------------------------------------------------------------- voices -- */

static int clamp_volume(float volume)
{
    int v = (int)(volume * 256.0f + 0.5f);
    if (v < 0)   v = 0;
    if (v > 256) v = 256;
    return v;
}

static voice *resolve(br_voice handle)
{
    int index = (handle & 0xff) - 1;

    if (index < 0 || index >= MAX_VOICES)
        return NULL;
    if (s_voices[index].generation != (handle >> 8))
        return NULL;
    return &s_voices[index];
}

br_voice br_audio_play(const br_sound *sound, float volume, int loop)
{
    br_voice handle = 0;
    int i;

    if (!sound || !sound->samples || sound->frame_count <= 0 || s_port < 0)
        return 0;

    lock();
    for (i = 0; i < MAX_VOICES; i++) {
        voice *v = &s_voices[i];

        if (v->active)
            continue;
        v->sound      = sound;
        v->position   = 0;
        v->step       = (unsigned)((long long)sound->sample_rate * FIXED_ONE / OUT_RATE);
        v->volume     = clamp_volume(volume);
        v->loop       = loop;
        v->generation = s_generation++;
        v->active     = 1;
        handle = (i + 1) | (v->generation << 8);
        break;
    }
    unlock();

    if (!handle)
        LOGW("audio: all %d voices busy", MAX_VOICES);
    return handle;
}

void br_audio_stop(br_voice handle)
{
    voice *v;

    lock();
    v = resolve(handle);
    if (v)
        v->active = 0;
    unlock();
}

void br_audio_set_volume(br_voice handle, float volume)
{
    voice *v;

    lock();
    v = resolve(handle);
    if (v)
        v->volume = clamp_volume(volume);
    unlock();
}

int br_audio_playing(br_voice handle)
{
    voice *v;
    int playing;

    lock();
    v = resolve(handle);
    playing = v != NULL && v->active;
    unlock();
    return playing;
}

void br_audio_stop_all(void)
{
    int i;

    lock();
    for (i = 0; i < MAX_VOICES; i++)
        s_voices[i].active = 0;
    unlock();
}
