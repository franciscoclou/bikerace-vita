/* Host stand-in for the Vita's UDP logger: writes to stderr. */
#include <stdarg.h>
#include <stdio.h>
#include <string.h>

#include "../src/platform/log.h"

int br_test_log_verbose = 0;

void br_log_init(void) {}
void br_log_flush(void) {}
void br_log_shutdown(void) {}

void br_log_write(const char *level, const char *file, int line, const char *fmt, ...)
{
    va_list ap;
    const char *base = strrchr(file, '/');

    if (!br_test_log_verbose && level[0] == 'I')
        return;

    fprintf(stderr, "    [%s] %s:%d: ", level, base ? base + 1 : file, line);
    va_start(ap, fmt);
    vfprintf(stderr, fmt, ap);
    va_end(ap);
    fputc('\n', stderr);
}
