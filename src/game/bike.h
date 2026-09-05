#ifndef BR_BIKE_H
#define BR_BIKE_H

#include "../engine/body.h"
#include "board.h"

/* Port of com.topfreegames.bikerace.Bike.
 *
 * A bike is three point masses -- rider head, front wheel, rear wheel -- held
 * together by three springs, plus a fourth stiff spring used to push a wheel
 * out of the ground on contact. There is no general collision system: each
 * wheel is resolved against each nearby track segment directly. */

/* Every bike single player can ride. The shop and its currency are not
 * ported, so all of them are simply available. */
typedef enum {
    BR_BIKE_REGULAR = 0,
    BR_BIKE_SUPER,
    BR_BIKE_KIDS,
    BR_BIKE_GHOST,
    BR_BIKE_NINJA,
    BR_BIKE_COP,
    BR_BIKE_RETRO,
    BR_BIKE_BRONZE,
    BR_BIKE_SILVER,
    BR_BIKE_GOLD,
    BR_BIKE_GIRL,
    BR_BIKE_ACROBATIC,
    BR_BIKE_BEAT,
    BR_BIKE_SPAM,
    BR_BIKE_ULTRA,
    BR_BIKE_ZOMBIE,
    BR_BIKE_ARMY,
    BR_BIKE_HALLOWEEN,
    BR_BIKE_THANKSGIVING,
    BR_BIKE_SANTA,
    BR_BIKE_EASTER,
    BR_BIKE_TYPE_COUNT
} br_bike_type;

/* Three bikes carry their own wheel art. */
typedef enum {
    BR_WHEEL_STANDARD = 0,
    BR_WHEEL_ULTRA,
    BR_WHEEL_HALLOWEEN,
    BR_WHEEL_SANTA
} br_wheel_kind;

typedef enum {
    BR_BIKE_IDLE = 0,
    BR_BIKE_ACCELERATING,
    BR_BIKE_BRAKING,
    BR_BIKE_REVERSING,
    BR_BIKE_CRASHED
} br_bike_state;

typedef struct {
    const char *name;        /* internal, for logs */
    const char *label;       /* as the game itself names it */
    const char *sprite;      /* file under textures/ */
    br_wheel_kind wheel;
    float drive_cap;         /* Java field e */
    float drive;             /* Java field f */
    float brake;             /* Java field g */
    float lean;              /* Java field h */
    float wheel_spin;        /* Java field i, degrees/second */
    float front_x, rear_x;   /* wheel offsets from the rider */
    int   invulnerable;      /* Java field j: head impacts never crash */
    int   head_collides;     /* Java field k: head is pushed out of the ground */
    int   reverse_on_brake;  /* Java field l */
    float gravity_y;         /* per type, from Game's table */
    float sprite_width;      /* world units, from GameSceneDirector */
    vec2  sprite_offset;
    /* The sprite sits in the top-left of its 256x256 file; the rest is
     * padding. TextureCoordinates carries the region that bounds it, and
     * drawing the whole file instead floats the bike up and left. */
    float sprite_uv[4];
} br_bike_def;

typedef struct {
    const br_bike_def *def;
    br_bike_type       type;

    br_body          head;    /* Java field a */
    br_body          front;   /* Java field b */
    br_body          rear;    /* Java field c */
    br_composed_body chassis;

    br_spring head_front, head_rear, axle, contact;
    br_body   ground;         /* immovable anchor for the contact spring */

    br_bike_state state;
} br_bike;

const br_bike_def *br_bike_def_for(br_bike_type type);

void  br_bike_init(br_bike *bike, br_bike_type type, const vec2 *spawn);
void  br_bike_set_state(br_bike *bike, br_bike_state state);

void  br_bike_apply_gravity(br_bike *bike, const vec2 *gravity, float dt);
void  br_bike_apply_springs(br_bike *bike, float dt);
void  br_bike_step(br_bike *bike, float dt);
/* `blocked` stops backwards rotation, and is set when the front wheel is on
 * the ground. */
void  br_bike_apply_lean(br_bike *bike, float input, float dt, int blocked);

/* Each returns the contact force magnitude, or 0 when not touching. */
float br_bike_collide_rear(br_bike *bike, const br_board *board, float dt);
float br_bike_collide_front(br_bike *bike, const br_board *board, float dt);
float br_bike_collide_head(br_bike *bike, const br_board *board, float dt);

/* Does this board kill the rider? */
int   br_bike_head_hits(br_bike *bike, const br_board *board);

void  br_bike_bounds(const br_bike *bike, br_aabb *out, float margin);
float br_bike_angle_deg(const br_bike *bike);

static inline int br_bike_crashed(const br_bike *b) { return b->state == BR_BIKE_CRASHED; }

#endif /* BR_BIKE_H */
