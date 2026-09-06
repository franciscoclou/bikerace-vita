#ifndef BR_SAVE_H
#define BR_SAVE_H

/* Progress lives in ux0:data/bikerace/save.bin.
 *
 * The original kept this in obfuscated, encrypted SharedPreferences; there is
 * nothing worth reproducing in that, so this stores the same facts in a plain
 * flat file.
 *
 * Gating follows the original: finishing a level unlocks the next, rolling
 * into the next world, and a world needs a running star total to enter. The
 * original also gated worlds 4, 5, 6 and 12 behind multiplayer wins, 7 and 8
 * behind invited friends, and 9, 10 and 11 behind bikes bought from the shop.
 * None of those exist in this port, so only the star totals survive. */

typedef struct {
    unsigned char stars;      /* 0..3 */
    unsigned char unlocked;
    float         best_time;  /* seconds; 0 means never finished */
} br_level_progress;

typedef struct {
    br_level_progress *levels;
    int world_count, levels_per_world;

    int last_world, last_level;   /* where the menu should open */
    int bike_type;
    int sound_on, music_on;
    /* Set by "unlock everything", which has to lift the star gate on worlds
     * as well as opening the levels. Not reversible, by design. */
    int every_world_open;

    int dirty;
} br_save;

int  br_save_init(br_save *save, int world_count, int levels_per_world);
void br_save_free(br_save *save);

/* A missing or unreadable file leaves the save empty and is not an error:
 * a first run looks exactly like that. */
void br_save_load(br_save *save);
int  br_save_flush(br_save *save);   /* writes only when something changed */

br_level_progress *br_save_level(br_save *save, int world, int level);

/* Keeps the better of the new result and what is already recorded. */
void br_save_record(br_save *save, int world, int level, int stars, float time);
void br_save_remember_place(br_save *save, int world, int level);

int  br_save_world_stars(const br_save *save, int world);
int  br_save_total_stars(const br_save *save);

/* Stars needed to enter a world, by index. World 16 is absent from the
 * original's table, so it costs nothing -- a quirk kept deliberately. */
int  br_save_world_requirement(int world);
int  br_save_world_unlocked(const br_save *save, int world);
int  br_save_level_unlocked(const br_save *save, int world, int level);
/* Opens the level after this one, rolling into the next world. */
void br_save_unlock_next(br_save *save, int world, int level);

void br_save_reset_progress(br_save *save);
void br_save_unlock_everything(br_save *save);

#endif /* BR_SAVE_H */
