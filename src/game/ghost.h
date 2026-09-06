#ifndef BR_GHOST_H
#define BR_GHOST_H

#include "../engine/vec2.h"
#include "bike.h"

/* Your best run, played back beside you.
 *
 * The original recorded runs the same way -- BikePositionSample, and a Bike
 * method that places a bike from a pose rather than from its bodies. Only the
 * head's position and the frame angle are stored; the wheels are derived from
 * them exactly as the original derived them, so a ghost needs no physics.
 *
 * Ghosts live in ux0:data/bikerace/ghosts.bin, apart from the save, so a
 * damaged ghost can never cost anyone their progress. */

#define BR_GHOST_HZ          20
#define BR_GHOST_MAX_SAMPLES 1600      /* 80 seconds; longer runs stop recording */

typedef struct {
    vec2  pos;         /* the rider's head, as Bike stores it */
    float angle_deg;
    /* How far the run has travelled by this sample. Not stored in the file --
     * it is summed after loading, and it is what spins the ghost's wheels. */
    float distance;
} br_ghost_sample;

typedef struct {
    br_ghost_sample *samples;
    int              count;
    br_bike_type     bike;
    float            time;             /* the run this came from */
} br_ghost;

typedef struct br_ghost_store br_ghost_store;

br_ghost_store *br_ghost_store_open(int world_count, int levels_per_world);
void            br_ghost_store_close(br_ghost_store *store);

/* NULL when nothing has been recorded for that level. */
const br_ghost *br_ghost_get(const br_ghost_store *store, int world, int level);
/* Keeps the run only when it beats what is stored. */
void            br_ghost_put(br_ghost_store *store, int world, int level,
                             const br_ghost *run);
int             br_ghost_store_flush(br_ghost_store *store);

/* Recording, driven from the race. */
typedef struct {
    br_ghost_sample samples[BR_GHOST_MAX_SAMPLES];
    int             count;
    float           next_sample_at;
    int             overflowed;
} br_ghost_recorder;

void br_ghost_record_begin(br_ghost_recorder *rec);
void br_ghost_record_tick(br_ghost_recorder *rec, float elapsed,
                          const vec2 *head, float angle_deg);

/* Where the ghost is at `time`, interpolated, and how far its wheels have
 * rolled by then. Returns 0 once the run has finished. */
int  br_ghost_pose_at(const br_ghost *ghost, float time, vec2 *pos,
                      float *angle_deg, float *wheel_deg);

#endif /* BR_GHOST_H */
