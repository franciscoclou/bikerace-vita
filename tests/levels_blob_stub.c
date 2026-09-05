/* Host stand-in for the linked-in level blob: reads data/levels.bin instead. */
#include <stdio.h>
#include <stdlib.h>

const unsigned char *br_levels_blob_start;
const unsigned char *br_levels_blob_end;

void br_test_load_blob(const char *path)
{
    FILE *f = fopen(path, "rb");
    long n;
    unsigned char *buf;

    if (!f) { fprintf(stderr, "cannot open %s\n", path); exit(1); }
    fseek(f, 0, SEEK_END);
    n = ftell(f);
    fseek(f, 0, SEEK_SET);
    buf = malloc((size_t)n);
    if (fread(buf, 1, (size_t)n, f) != (size_t)n) { fprintf(stderr, "short read\n"); exit(1); }
    fclose(f);

    br_levels_blob_start = buf;
    br_levels_blob_end = buf + n;
}
