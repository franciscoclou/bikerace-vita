#ifndef BR_DEVICE_H
#define BR_DEVICE_H

/* What the game is running on.
 *
 * The PlayStation TV is the same hardware with two things taken away: there is
 * no touchscreen, and the picture goes to a television that may crop its own
 * edges. Everything the game does can be done with the pad already -- touch has
 * always been the alternative, never the only way -- so this is not about
 * making the game playable there. It is about not telling someone to tap a
 * screen they do not have, and not putting the clock where the set will cut it.
 *
 * Queried once and cached: the answer cannot change while the game runs. */

int br_device_is_tv(void);

/* How much of each edge to keep clear of interface. Zero on a handheld, where
 * the panel shows every pixel; a small inset on a television, where many sets
 * still overscan. Scene rendering ignores this and fills the screen. */
float br_device_ui_inset(void);

#endif /* BR_DEVICE_H */
