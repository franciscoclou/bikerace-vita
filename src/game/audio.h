#ifndef BR_GAME_AUDIO_H
#define BR_GAME_AUDIO_H

#include "../platform/audio.h"

/* Port of com.topfreegames.bikerace.GameAudio.
 *
 * The engine is not one pitch-shifted loop but five recorded notes crossfaded
 * by a small state machine: idling, slow, a one-shot climb, fast, and a
 * one-shot drop back down. The climb and drop hand over when they are 95%
 * played, which is what makes the change of note land on the beat of the
 * sample rather than cutting it off. */

typedef enum {
    BR_SFX_ENGINE_LOW = 0,
    BR_SFX_ENGINE_MEDIUM,
    BR_SFX_ENGINE_MEDIUM_HI,
    BR_SFX_ENGINE_HI,
    BR_SFX_ENGINE_HI_MEDIUM,
    BR_SFX_EXPLOSION,
    BR_SFX_FALL_IMPACT,
    BR_SFX_WIN,
    BR_SFX_SPOOKY_BELL,
    BR_SFX_SPOOKY_RAVEN,
    BR_SFX_SPOOKY_SCREAM,
    BR_SFX_SPOOKY_LAUGH,
    /* The interface. The original was a touch game and clicked at nothing, so
     * these are borrowed from its roulette: a dry button thunk for the cursor
     * and a brighter chime for a choice. */
    BR_SFX_UI_CLICK,
    BR_SFX_UI_CHIME,
    BR_SFX_COUNT
} br_sfx;

/* What a menu just did. The sample and level for each live in audio.c, so the
 * widgets that raise these do not have to know about either. */
typedef enum {
    BR_UI_SOUND_MOVE = 0,   /* the cursor stepped to another row or tile */
    BR_UI_SOUND_SELECT,     /* something was chosen */
    BR_UI_SOUND_BACK        /* a screen was left */
} br_ui_sound;

typedef enum {
    BR_ENGINE_STOPPED = 0,
    BR_ENGINE_SLOW,
    BR_ENGINE_ACCELERATING,
    BR_ENGINE_FAST,
    BR_ENGINE_DECELERATING
} br_engine_state;

typedef struct {
    br_sound sfx[BR_SFX_COUNT];
    br_sound music;

    br_engine_state engine;
    float           engine_time;
    br_voice        engine_voice;
    int             engine_sfx;     /* which sample the engine voice holds, or -1 */

    br_voice        ui_voice;       /* one for the whole interface; see below */
    br_voice        music_voice;
    int             music_wanted;   /* what the app asked for, before the toggle */
    float           impact_cooldown;
    unsigned        spooky_seed;

    int             ready;
    int             sound_on, music_on;
} br_game_audio;

int  br_game_audio_init(br_game_audio *audio);
void br_game_audio_free(br_game_audio *audio);

/* Once a frame while racing. `rear_speed` is the rear wheel's speed and
 * `grounded` whether it is touching, which is what the original fed in. */
void br_game_audio_engine(br_game_audio *audio, int accelerating, float rear_speed,
                          int grounded, float dt);
/* A hard landing: only fires above a force threshold, and only when the bike
 * has been off the ground, which `ground_factor` tracks. */
void br_game_audio_impact(br_game_audio *audio, float force, float ground_factor);

/* Menu feedback. Every click shares a single voice, so holding a direction
 * ticks along with the auto-repeat instead of stacking a dozen overlapping
 * copies onto the mixer. */
void br_game_audio_ui(br_game_audio *audio, br_ui_sound kind);

void br_game_audio_crash(br_game_audio *audio);
void br_game_audio_win(br_game_audio *audio);
void br_game_audio_spooky(br_game_audio *audio);

void br_game_audio_set_enabled(br_game_audio *audio, int sound_on, int music_on);
void br_game_audio_music(br_game_audio *audio, int playing);
void br_game_audio_silence(br_game_audio *audio);
/* Runs the impact rate limit off game time. */
void br_game_audio_tick(br_game_audio *audio, float dt);

#endif /* BR_GAME_AUDIO_H */
