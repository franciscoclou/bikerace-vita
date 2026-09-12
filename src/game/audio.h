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
    /* The interface. One sample, borrowed from the original's roulette, for
     * every menu press there is: the original was a touch game and clicked at
     * nothing, and anything more than a tick starts performing. */
    BR_SFX_UI_CLICK,
    BR_SFX_COUNT
} br_sfx;

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

/* One tick, the same for moving the cursor, choosing and backing out. It has a
 * voice of its own that each tick takes back, so holding a direction ticks
 * along with the auto-repeat instead of stacking overlapping copies. */
void br_game_audio_ui_click(br_game_audio *audio);

void br_game_audio_crash(br_game_audio *audio);
void br_game_audio_win(br_game_audio *audio);
void br_game_audio_spooky(br_game_audio *audio);

void br_game_audio_set_enabled(br_game_audio *audio, int sound_on, int music_on);
void br_game_audio_music(br_game_audio *audio, int playing);
void br_game_audio_silence(br_game_audio *audio);
/* Runs the impact rate limit off game time. */
void br_game_audio_tick(br_game_audio *audio, float dt);

#endif /* BR_GAME_AUDIO_H */
