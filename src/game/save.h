#ifndef BR_SAVE_H
#define BR_SAVE_H

/* Progress lives in ux0:data/bikerace/save.bin.
 *
 * The original kept this in obfuscated, encrypted SharedPreferences; there is
 * nothing worth reproducing in that, so this stores the same facts in a plain
 * flat file. Nothing is gated on it -- every world and level is selectable
 * from the start -- so it only records what has been achieved. */

typedef struct {
    unsigned char stars;      /* 0..3 */
    float         best_time;  /* seconds; 0 means never finished */
} br_level_progress;

typedef struct {
    br_level_progress *levels;
    int world_count, levels_per_world;

    int last_world, last_level;   /* where the menu should open */
    int bike_type;

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

#endif /* BR_SAVE_H */
