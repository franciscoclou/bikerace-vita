#ifndef BR_APP_H
#define BR_APP_H

#include "game/game.h"
#include "game/save.h"
#include "platform/input.h"
#include "ui/font.h"
#include "ui/menu.h"

/* Ties the menu, the race and the save file together. main.c only boots the
 * hardware and runs the loop; everything about what is on screen lives here. */

typedef enum {
    BR_APP_MENU = 0,
    BR_APP_RACING
} br_app_screen;

typedef struct {
    br_font  display, body;
    br_save  save;
    br_menu  menu;
    br_game  game;

    br_app_screen screen;
    br_game_state last_race_state;
    int      quit;
} br_app;

int  br_app_init(br_app *app);
void br_app_free(br_app *app);
void br_app_update(br_app *app, const br_input *in, float dt);
void br_app_draw(br_app *app, unsigned time_ms);

static inline int br_app_should_quit(const br_app *app) { return app->quit; }

#endif /* BR_APP_H */
