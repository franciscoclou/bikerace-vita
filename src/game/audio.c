#include "audio.h"

#include <stdio.h>
#include <string.h>

#include "../platform/fs.h"
#include "../platform/log.h"

/* File names as the APK has them; the mapping is GameAudio's own. */
static const char *s_files[BR_SFX_COUNT] = {
    "rot_baixa.wav",            /* engine, idling         */
    "rot_media.wav",            /* engine, slow           */
    "rot_media_alta.wav",       /* engine, climbing       */
    "rot_alta.wav",             /* engine, fast           */
    "rot_alta_media.wav",       /* engine, dropping back  */
    "explosion.wav",
    "queda.wav",                /* fall impact            */
    "win.wav",
    "sino_1.wav",
    "corvo_1.wav",
    "grito_homem_1.wav",
    "evil_laugh_bruxa_3.wav",
    "roleta_start_botao.wav",   /* ui, cursor      */
    "collect.wav",              /* ui, chose one   */
};

#define ENGINE_IDLE_VOLUME   0.8f
#define ENGINE_VOLUME        1.0f
#define SPEED_TO_CLIMB       4.5f
#define SPEED_TO_DROP        2.8f
#define HANDOVER             0.95f
#define IMPACT_FORCE         300.0f
#define IMPACT_AIRBORNE      0.6f

int br_game_audio_init(br_game_audio *audio)
{
    char path[256];
    int i, loaded = 0;

    memset(audio, 0, sizeof(*audio));
    audio->engine_sfx = -1;
    audio->spooky_seed = 1;
    audio->sound_on = 1;
    audio->music_on = 1;

    for (i = 0; i < BR_SFX_COUNT; i++) {
        snprintf(path, sizeof(path), "%s/sfx/%s", br_asset_root(), s_files[i]);
        if (br_sound_load(&audio->sfx[i], path) == 0)
            loaded++;
    }

    /* Music is the one large asset and the game is fine without it. */
    snprintf(path, sizeof(path), "%s/music/musica_menu.wav", br_asset_root());
    br_sound_load(&audio->music, path);

    audio->ready = loaded > 0;
    audio->music_wanted = 0;
    LOGI("audio: %d of %d effects, music %s", loaded, BR_SFX_COUNT,
         audio->music.samples ? "loaded" : "absent");
    return 0;
}

void br_game_audio_free(br_game_audio *audio)
{
    int i;

    br_audio_stop_all();
    for (i = 0; i < BR_SFX_COUNT; i++)
        br_sound_free(&audio->sfx[i]);
    br_sound_free(&audio->music);
    memset(audio, 0, sizeof(*audio));
}

void br_game_audio_set_enabled(br_game_audio *audio, int sound_on, int music_on)
{
    audio->sound_on = sound_on;
    audio->music_on = music_on;
    if (!sound_on)
        br_game_audio_silence(audio);
    br_game_audio_music(audio, audio->music_wanted);
}

static void play_once(br_game_audio *audio, br_sfx which, float volume)
{
    if (!audio->sound_on)
        return;
    br_audio_play(&audio->sfx[which], volume, 0);
}

/* Starts `which` on the engine voice, unless it is already the one playing. */
static void engine_note(br_game_audio *audio, br_sfx which, int loop, float volume)
{
    if (audio->engine_sfx == (int)which && br_audio_playing(audio->engine_voice))
        return;

    br_audio_stop(audio->engine_voice);
    audio->engine_voice = br_audio_play(&audio->sfx[which], volume, loop);
    audio->engine_sfx = (int)which;
}

static float note_seconds(const br_game_audio *audio, br_sfx which)
{
    return br_sound_seconds(&audio->sfx[which]);
}

void br_game_audio_engine(br_game_audio *audio, int accelerating, float rear_speed,
                          int grounded, float dt)
{
    if (!audio->ready)
        return;
    if (!audio->sound_on) {
        br_game_audio_silence(audio);
        return;
    }

    audio->engine_time += dt;

    if (!accelerating) {
        audio->engine = BR_ENGINE_STOPPED;
    } else {
        switch (audio->engine) {
        case BR_ENGINE_STOPPED:
            audio->engine = BR_ENGINE_SLOW;
            audio->engine_time = 0.0f;
            break;
        case BR_ENGINE_SLOW:
            /* Leaving the ground counts as opening the throttle. */
            if (rear_speed >= SPEED_TO_CLIMB || !grounded) {
                audio->engine = BR_ENGINE_ACCELERATING;
                audio->engine_time = 0.0f;
            }
            break;
        case BR_ENGINE_ACCELERATING:
            if (audio->engine_time >= note_seconds(audio, BR_SFX_ENGINE_MEDIUM_HI) * HANDOVER) {
                audio->engine = BR_ENGINE_FAST;
                audio->engine_time = 0.0f;
            }
            break;
        case BR_ENGINE_FAST:
            if (rear_speed < SPEED_TO_DROP && grounded) {
                audio->engine = BR_ENGINE_DECELERATING;
                audio->engine_time = 0.0f;
            }
            break;
        case BR_ENGINE_DECELERATING:
            if (audio->engine_time >= note_seconds(audio, BR_SFX_ENGINE_HI_MEDIUM) * HANDOVER)
                audio->engine = BR_ENGINE_SLOW;
            break;
        }
    }

    switch (audio->engine) {
    case BR_ENGINE_STOPPED:
        engine_note(audio, BR_SFX_ENGINE_LOW, 1, ENGINE_IDLE_VOLUME);
        break;
    case BR_ENGINE_SLOW:
        engine_note(audio, BR_SFX_ENGINE_MEDIUM, 1, ENGINE_VOLUME);
        break;
    case BR_ENGINE_ACCELERATING:
        engine_note(audio, BR_SFX_ENGINE_MEDIUM_HI, 0, ENGINE_VOLUME);
        break;
    case BR_ENGINE_FAST:
        engine_note(audio, BR_SFX_ENGINE_HI, 1, ENGINE_VOLUME);
        break;
    case BR_ENGINE_DECELERATING:
        engine_note(audio, BR_SFX_ENGINE_HI_MEDIUM, 0, ENGINE_VOLUME);
        break;
    }
}

void br_game_audio_impact(br_game_audio *audio, float force, float ground_factor)
{
    if (!audio->ready || force < IMPACT_FORCE || ground_factor >= IMPACT_AIRBORNE)
        return;
    if (audio->impact_cooldown > 0.0f)
        return;

    play_once(audio, BR_SFX_FALL_IMPACT, 1.0f);
    audio->impact_cooldown = note_seconds(audio, BR_SFX_FALL_IMPACT);
}

void br_game_audio_ui(br_game_audio *audio, br_ui_sound kind)
{
    /* Moving is quieter than choosing, and backing out lands between the two:
     * the cursor should tick under the eye, not announce itself. */
    static const struct { br_sfx sfx; float volume; } s_ui[] = {
        { BR_SFX_UI_CLICK, 0.30f },   /* BR_UI_SOUND_MOVE   */
        { BR_SFX_UI_CHIME, 0.55f },   /* BR_UI_SOUND_SELECT */
        { BR_SFX_UI_CLICK, 0.62f },   /* BR_UI_SOUND_BACK   */
    };

    /* The enum has no signed values, and gcc warns on ARM if this pretends
     * otherwise, so only the upper bound is worth testing. */
    if (!audio->sound_on || (unsigned)kind >= sizeof(s_ui) / sizeof(s_ui[0]))
        return;

    br_audio_stop(audio->ui_voice);
    audio->ui_voice = br_audio_play(&audio->sfx[s_ui[kind].sfx],
                                    s_ui[kind].volume, 0);
}

void br_game_audio_crash(br_game_audio *audio)
{
    if (!audio->ready)
        return;
    br_audio_stop(audio->engine_voice);
    audio->engine_sfx = -1;
    audio->engine = BR_ENGINE_STOPPED;
    play_once(audio, BR_SFX_EXPLOSION, 1.0f);
}

void br_game_audio_win(br_game_audio *audio)
{
    if (!audio->ready)
        return;
    br_audio_stop(audio->engine_voice);
    audio->engine_sfx = -1;
    audio->engine = BR_ENGINE_STOPPED;
    play_once(audio, BR_SFX_WIN, 1.0f);
}

void br_game_audio_spooky(br_game_audio *audio)
{
    static const br_sfx choices[] = {
        BR_SFX_SPOOKY_BELL, BR_SFX_SPOOKY_RAVEN,
        BR_SFX_SPOOKY_SCREAM, BR_SFX_SPOOKY_LAUGH,
    };

    if (!audio->ready)
        return;
    audio->spooky_seed = audio->spooky_seed * 1103515245u + 12345u;
    play_once(audio, choices[(audio->spooky_seed >> 16) & 3], 1.0f);
}

void br_game_audio_music(br_game_audio *audio, int playing)
{
    audio->music_wanted = playing;
    if (!audio->music.samples)
        return;

    if (playing && audio->music_on) {
        if (!br_audio_playing(audio->music_voice))
            audio->music_voice = br_audio_play(&audio->music, 0.7f, 1);
    } else {
        br_audio_stop(audio->music_voice);
        audio->music_voice = 0;
    }
}

void br_game_audio_silence(br_game_audio *audio)
{
    br_audio_stop(audio->engine_voice);
    audio->engine_voice = 0;
    audio->engine_sfx = -1;
    audio->engine = BR_ENGINE_STOPPED;
    audio->engine_time = 0.0f;
}

/* Called by the game each frame so the impact rate limit runs on game time. */
void br_game_audio_tick(br_game_audio *audio, float dt)
{
    if (audio->impact_cooldown > 0.0f)
        audio->impact_cooldown -= dt;
}
