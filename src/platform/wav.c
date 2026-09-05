/* WAV loading. Kept apart from the mixer because it is plain stdio, which lets
 * the host tests load the real sound files and run the real audio logic. */
#include "audio.h"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "log.h"

static unsigned le32(const unsigned char *p)
{
    return (unsigned)p[0] | ((unsigned)p[1] << 8) |
           ((unsigned)p[2] << 16) | ((unsigned)p[3] << 24);
}

static unsigned le16(const unsigned char *p)
{
    return (unsigned)p[0] | ((unsigned)p[1] << 8);
}

/* Reads the 16-bit mono PCM that the asset script produces. Anything else is
 * rejected loudly rather than played as noise. */
int br_sound_load(br_sound *sound, const char *path)
{
    unsigned char header[12], chunk[8], fmt[16];
    FILE *f;
    int have_fmt = 0;

    memset(sound, 0, sizeof(*sound));

    f = fopen(path, "rb");
    if (!f) {
        LOGW("audio: no sound at %s", path);
        return -1;
    }
    if (fread(header, 1, sizeof(header), f) != sizeof(header) ||
        memcmp(header, "RIFF", 4) != 0 || memcmp(header + 8, "WAVE", 4) != 0) {
        LOGE("audio: %s is not a WAV", path);
        fclose(f);
        return -1;
    }

    while (fread(chunk, 1, sizeof(chunk), f) == sizeof(chunk)) {
        unsigned size = le32(chunk + 4);

        if (memcmp(chunk, "fmt ", 4) == 0) {
            if (size < sizeof(fmt) || fread(fmt, 1, sizeof(fmt), f) != sizeof(fmt))
                break;
            if (le16(fmt) != 1 || le16(fmt + 2) != 1 || le16(fmt + 14) != 16) {
                LOGE("audio: %s is format %u, %u channels, %u bits -- want PCM "
                     "mono 16-bit", path, le16(fmt), le16(fmt + 2), le16(fmt + 14));
                fclose(f);
                return -1;
            }
            sound->sample_rate = (int)le32(fmt + 4);
            have_fmt = 1;
            if (size > sizeof(fmt))
                fseek(f, (long)(size - sizeof(fmt)), SEEK_CUR);
        } else if (memcmp(chunk, "data", 4) == 0) {
            if (!have_fmt)
                break;
            sound->frame_count = (int)(size / 2);
            sound->samples = malloc(size);
            if (!sound->samples) {
                LOGE("audio: out of memory for %s (%u bytes)", path, size);
                fclose(f);
                return -1;
            }
            if (fread(sound->samples, 1, size, f) != size) {
                LOGE("audio: %s ended early", path);
                free(sound->samples);
                memset(sound, 0, sizeof(*sound));
                fclose(f);
                return -1;
            }
            fclose(f);
            LOGI("audio: loaded %s (%.2fs, %d Hz)", path,
                 br_sound_seconds(sound), sound->sample_rate);
            return 0;
        } else {
            fseek(f, (long)(size + (size & 1)), SEEK_CUR);
        }
    }

    LOGE("audio: %s has no usable data chunk", path);
    fclose(f);
    return -1;
}

void br_sound_free(br_sound *sound)
{
    free(sound->samples);
    memset(sound, 0, sizeof(*sound));
}

