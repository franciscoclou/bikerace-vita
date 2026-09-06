#ifndef BR_THEME_H
#define BR_THEME_H

#include "../engine/render.h"

/* One palette for every screen, so panels, rows and text stay consistent.
 * All of these are premultiplied, which is what the blend mode expects. */

#define BR_UI_W 960.0f
#define BR_UI_H 544.0f

static const br_color BR_DIM          = { 0.0f, 0.0f, 0.0f, 0.62f };
static const br_color BR_SCRIM        = { 0.06f * 0.55f, 0.06f * 0.55f, 0.09f * 0.55f, 0.55f };
static const br_color BR_PAPER        = { 0.93f, 0.89f, 0.80f, 1.00f };
static const br_color BR_PANEL        = { 0.10f * 0.94f, 0.08f * 0.94f, 0.06f * 0.94f, 0.94f };
static const br_color BR_ROW          = { 0.20f * 0.30f, 0.13f * 0.30f, 0.07f * 0.30f, 0.30f };
static const br_color BR_ROW_SELECTED = { 1.00f, 0.72f, 0.16f, 1.00f };
static const br_color BR_HIGHLIGHT    = { 1.00f, 0.72f, 0.16f, 1.00f };
static const br_color BR_TILE_SEL     = { 1.00f, 0.74f, 0.34f, 1.00f };
/* Dims a shut tile without hiding what is on it. */
static const br_color BR_LOCKED       = { 0.42f, 0.40f, 0.38f, 1.00f };
static const br_color BR_INK          = { 0.28f, 0.15f, 0.06f, 1.00f };
static const br_color BR_TEXT         = { 0.99f, 0.97f, 0.92f, 1.00f };
static const br_color BR_TEXT_DIM     = { 0.74f, 0.71f, 0.66f, 1.00f };

#endif /* BR_THEME_H */
