#include "audio.h"

#include <psp2/audioout.h>
#include <psp2/kernel/threadmgr.h>

#include <string.h>

#include "log.h"
#include "mixer.h"

#define OUT_RATE  48000
#define OUT_GRAIN 1024          /* frames per output call; a multiple of 64 */

static short  s_mix[OUT_GRAIN * 2];
static int    s_port = -1;
static SceUID s_thread = -1;
static SceUID s_mutex = -1;
static int    s_running;

static void lock(void)   { if (s_mutex >= 0) sceKernelLockMutex(s_mutex, 1, NULL); }
static void unlock(void) { if (s_mutex >= 0) sceKernelUnlockMutex(s_mutex, 1); }

/* The mixer does not lock, so every entry point into it does. */
static int mixer_thread(SceSize args, void *argp)
{
    (void)args;
    (void)argp;

    while (s_running) {
        lock();
        br_mixer_render(s_mix, OUT_GRAIN);
        unlock();
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

    br_mixer_reset();
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
         OUT_RATE, OUT_GRAIN, BR_MIXER_VOICES);
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
    if (s_mutex >= 0) {
        sceKernelDeleteMutex(s_mutex);
        s_mutex = -1;
    }
    sceAudioOutReleasePort(s_port);
    s_port = -1;
    LOGI("audio: closed");
}

br_voice br_audio_play(const br_sound *sound, float volume, int loop)
{
    br_voice handle;

    lock();
    handle = br_mixer_play(sound, volume, loop, OUT_RATE);
    unlock();
    return handle;
}

void br_audio_stop(br_voice voice)
{
    lock();
    br_mixer_stop(voice);
    unlock();
}

void br_audio_set_volume(br_voice voice, float volume)
{
    lock();
    br_mixer_set_volume(voice, volume);
    unlock();
}

int br_audio_playing(br_voice voice)
{
    int playing;

    lock();
    playing = br_mixer_playing(voice);
    unlock();
    return playing;
}

void br_audio_stop_all(void)
{
    lock();
    br_mixer_stop_all();
    unlock();
}
