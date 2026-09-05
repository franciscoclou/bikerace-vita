/* Host stand-in for the blobs blobs.S links in: read them off disk instead. */
#include <stdio.h>
#include <stdlib.h>

const unsigned char *br_levels_blob_start;
const unsigned char *br_levels_blob_end;
const unsigned char *br_font_display_start;
const unsigned char *br_font_display_end;
const unsigned char *br_font_body_start;
const unsigned char *br_font_body_end;

static void load(const char *path, const unsigned char **start, const unsigned char **end)
{
    FILE *f = fopen(path, "rb");
    long n;
    unsigned char *buf;

    if (!f) { fprintf(stderr, "cannot open %s\n", path); exit(1); }
    fseek(f, 0, SEEK_END);
    n = ftell(f);
    fseek(f, 0, SEEK_SET);
    buf = malloc((size_t)n);
    if (!buf || fread(buf, 1, (size_t)n, f) != (size_t)n) {
        fprintf(stderr, "short read on %s\n", path);
        exit(1);
    }
    fclose(f);
    *start = buf;
    *end = buf + n;
}

void br_test_load_blobs(void)
{
    load("data/levels.bin", &br_levels_blob_start, &br_levels_blob_end);
    load("data/font_display.bin", &br_font_display_start, &br_font_display_end);
    load("data/font_body.bin", &br_font_body_start, &br_font_body_end);
}
