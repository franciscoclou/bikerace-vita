/* Renders frames on the host and writes them as PNGs, so the scene transforms,
 * atlas regions and draw order can be checked without a Vita.
 *
 *   make -C tests shots
 */

#include <png.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "../src/game/game.h"
#include "stub/vitaGL.h"

void br_test_load_blob(const char *path);
extern int br_test_log_verbose;

static int write_png(const char *path, const unsigned char *rgba, int w, int h)
{
    FILE *f = fopen(path, "wb");
    png_structp png;
    png_infop info;
    int y;

    if (!f) return -1;
    png = png_create_write_struct(PNG_LIBPNG_VER_STRING, NULL, NULL, NULL);
    info = png_create_info_struct(png);
    png_init_io(png, f);
    png_set_IHDR(png, info, (png_uint_32)w, (png_uint_32)h, 8, PNG_COLOR_TYPE_RGBA,
                 PNG_INTERLACE_NONE, PNG_COMPRESSION_TYPE_DEFAULT, PNG_FILTER_TYPE_DEFAULT);
    png_write_info(png, info);
    for (y = 0; y < h; y++)
        png_write_row(png, (png_bytep)(rgba + (size_t)y * w * 4));
    png_write_end(png, NULL);
    png_destroy_write_struct(&png, &info);
    fclose(f);
    return 0;
}

static void shoot(br_game *game, const char *path, unsigned time_ms)
{
    const unsigned char *fb;
    int w, h;

    br_game_draw(game, time_ms);
    fb = br_test_fb(&w, &h);
    if (write_png(path, fb, w, h) == 0)
        printf("  wrote %s\n", path);
    else
        printf("  FAILED to write %s\n", path);
}

/* Advance the simulation with a rider that keeps the bike level. */
static void run_for(br_game *game, float seconds)
{
    br_input in;
    int i;

    memset(&in, 0, sizeof(in));
    in.accelerate = 1;
    game->state = BR_STATE_RUNNING;

    for (i = 0; i < (int)(seconds * 60.0f); i++) {
        float want = br_bike_angle_deg(&game->bike) / 45.0f;
        in.lean = want > 1.0f ? 1.0f : (want < -1.0f ? -1.0f : want);
        br_game_update(game, &in, 1.0f / 60.0f);
    }
}

int main(int argc, char **argv)
{
    br_game game;
    char path[256];
    const char *dir = argc > 1 ? argv[1] : "shots";
    struct { int world, level; float at; } shots[] = {
        { 0,  0, 0.0f }, { 0,  0, 1.0f }, { 0,  0, 2.0f }, { 0,  0, 5.0f },
        { 0,  5, 3.0f },                       /* 1-6, the spiral */
        { 1,  0, 2.5f },                       /* arctic  */
        { 15, 0, 3.0f },                       /* halloween */
    };
    int i;

    br_test_log_verbose = 0;
    br_test_load_blob("data/levels.bin");

    if (br_game_init(&game) < 0) {
        printf("game init failed\n");
        return 1;
    }

    for (i = 0; i < (int)(sizeof(shots) / sizeof(shots[0])); i++) {
        if (br_game_load(&game, shots[i].world, shots[i].level) < 0) {
            printf("  could not load %d-%d\n", shots[i].world + 1, shots[i].level + 1);
            return 1;
        }
        if (shots[i].at > 0.0f)
            run_for(&game, shots[i].at);
        snprintf(path, sizeof(path), "%s/w%02d_l%d_t%.0f.png",
                 dir, shots[i].world + 1, shots[i].level + 1, shots[i].at);
        shoot(&game, path, (unsigned)(shots[i].at * 1000.0f));
    }

    br_game_free(&game);
    return 0;
}
