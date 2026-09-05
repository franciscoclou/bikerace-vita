/* Host stand-in for the sceAudioOut mixer. Sounds still load for real through
 * src/platform/wav.c, so the engine state machine is exercised properly; only
 * the playback is accounted for rather than heard. */
#include <string.h>

#include "../src/platform/audio.h"

#define MAX_VOICES 12

static struct {
    const br_sound *sound;
    int             loop;
    int             active;
    int             generation;
} s_voices[MAX_VOICES];

static int s_generation = 1;
static int s_started;
static int s_initialised;

int  br_test_audio_started(void)     { return s_started; }
int  br_test_audio_initialised(void) { return s_initialised; }
void br_test_audio_reset(void)   { memset(s_voices, 0, sizeof(s_voices)); s_started = 0; }

const br_sound *br_test_audio_looping(void)
{
    int i;
    for (i = 0; i < MAX_VOICES; i++)
        if (s_voices[i].active && s_voices[i].loop)
            return s_voices[i].sound;
    return NULL;
}

int br_test_audio_active(void)
{
    int i, n = 0;
    for (i = 0; i < MAX_VOICES; i++)
        if (s_voices[i].active)
            n++;
    return n;
}

int  br_audio_init(void)     { s_initialised = 1; return 0; }
void br_audio_shutdown(void) { s_initialised = 0; }

br_voice br_audio_play(const br_sound *sound, float volume, int loop)
{
    int i;

    (void)volume;
    if (!sound || !sound->samples)
        return 0;
    for (i = 0; i < MAX_VOICES; i++) {
        if (s_voices[i].active)
            continue;
        s_voices[i].sound = sound;
        s_voices[i].loop = loop;
        s_voices[i].active = 1;
        s_voices[i].generation = s_generation++;
        s_started++;
        return (i + 1) | (s_voices[i].generation << 8);
    }
    return 0;
}

static int index_of(br_voice handle)
{
    int i = (handle & 0xff) - 1;
    if (i < 0 || i >= MAX_VOICES || s_voices[i].generation != (handle >> 8))
        return -1;
    return i;
}

void br_audio_stop(br_voice handle)
{
    int i = index_of(handle);
    if (i >= 0)
        s_voices[i].active = 0;
}

void br_audio_set_volume(br_voice handle, float volume) { (void)handle; (void)volume; }

int br_audio_playing(br_voice handle)
{
    int i = index_of(handle);
    return i >= 0 && s_voices[i].active;
}

void br_audio_stop_all(void)
{
    int i;
    for (i = 0; i < MAX_VOICES; i++)
        s_voices[i].active = 0;
}
