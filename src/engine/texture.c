#include "texture.h"

#include <png.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "../platform/log.h"

int br_image_load(br_image *img, const char *path)
{
    png_structp png = NULL;
    png_infop info = NULL;
    /* volatile: these are live across the setjmp libpng uses for errors. */
    png_bytep * volatile rows = NULL;
    unsigned char * volatile pixels = NULL;
    FILE *fp;
    png_uint_32 w, h;
    int depth, colour, y, stride;

    memset(img, 0, sizeof(*img));

    fp = fopen(path, "rb");
    if (!fp) {
        LOGE("texture: cannot open %s", path);
        return -1;
    }

    png = png_create_read_struct(PNG_LIBPNG_VER_STRING, NULL, NULL, NULL);
    info = png ? png_create_info_struct(png) : NULL;
    if (!info || setjmp(png_jmpbuf(png))) {
        LOGE("texture: libpng failed on %s", path);
        goto fail;
    }

    png_init_io(png, fp);
    png_read_info(png, info);
    png_get_IHDR(png, info, &w, &h, &depth, &colour, NULL, NULL, NULL);

    /* Normalise everything to 8-bit RGBA; the atlases are RGBA already but the
     * UI art includes palette and grey images. */
    if (colour == PNG_COLOR_TYPE_PALETTE)          png_set_palette_to_rgb(png);
    if (colour == PNG_COLOR_TYPE_GRAY && depth < 8) png_set_expand_gray_1_2_4_to_8(png);
    if (png_get_valid(png, info, PNG_INFO_tRNS))    png_set_tRNS_to_alpha(png);
    if (depth == 16)                                png_set_strip_16(png);
    if (colour == PNG_COLOR_TYPE_GRAY || colour == PNG_COLOR_TYPE_GRAY_ALPHA)
        png_set_gray_to_rgb(png);
    png_set_add_alpha(png, 0xFF, PNG_FILLER_AFTER);
    png_read_update_info(png, info);

    stride = (int)png_get_rowbytes(png, info);
    pixels = malloc((size_t)stride * h);
    rows   = malloc(sizeof(png_bytep) * h);
    if (!pixels || !rows) {
        LOGE("texture: out of memory for %s (%ux%u)", path, (unsigned)w, (unsigned)h);
        goto fail;
    }
    for (y = 0; y < (int)h; y++)
        rows[y] = pixels + (size_t)y * stride;
    png_read_image(png, rows);

    /* Android bitmaps are premultiplied, and the original picks its blend mode
     * to match (GL_ONE, GL_ONE_MINUS_SRC_ALPHA). Premultiply here so the same
     * blend mode gives the same result. */
    {
        size_t i, n = (size_t)stride * h;
        for (i = 0; i < n; i += 4) {
            unsigned a = pixels[i + 3];
            pixels[i + 0] = (unsigned char)((pixels[i + 0] * a + 127) / 255);
            pixels[i + 1] = (unsigned char)((pixels[i + 1] * a + 127) / 255);
            pixels[i + 2] = (unsigned char)((pixels[i + 2] * a + 127) / 255);
        }
    }

    glGenTextures(1, &img->id);
    glBindTexture(GL_TEXTURE_2D, img->id);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, (GLsizei)w, (GLsizei)h, 0,
                 GL_RGBA, GL_UNSIGNED_BYTE, pixels);

    img->width  = (int)w;
    img->height = (int)h;

    free((void *)rows);
    free((void *)pixels);
    png_destroy_read_struct(&png, &info, NULL);
    fclose(fp);

    LOGI("texture: loaded %s (%dx%d, id=%u)", path, img->width, img->height, img->id);
    return 0;

fail:
    free((void *)rows);
    free((void *)pixels);
    if (png) png_destroy_read_struct(&png, info ? &info : NULL, NULL);
    fclose(fp);
    return -1;
}

void br_image_free(br_image *img)
{
    if (img->id) {
        glDeleteTextures(1, &img->id);
        img->id = 0;
    }
}

br_texture br_texture_region(const br_image *img, float u0, float v0, float u1, float v1)
{
    br_texture t;
    t.image = img;
    t.u0 = u0; t.v0 = v0; t.u1 = u1; t.v1 = v1;
    return t;
}
