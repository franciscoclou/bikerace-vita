# Host build of the game, so behaviour can be checked without a Vita.
# Only the GL calls and the Vita logger are stubbed: the physics, level data,
# camera, broad phase, track mesh, texture loading and game loop are the same
# sources the Vita build compiles.
#
#   make -C tests run          run the checks
#   make -C tests run ARGS=-v  ... with the game's own log output

CC      ?= cc
PNG_CFLAGS := $(shell pkg-config --cflags libpng)
PNG_LIBS   := $(shell pkg-config --libs libpng)

CFLAGS  := -std=c99 -Wall -Wextra -Wno-unused-parameter -O1 -g \
           -DBR_HOST_TEST=1 -I stub -I ../src $(PNG_CFLAGS)
LDFLAGS := -lm $(PNG_LIBS)

CORE := \
  ../src/engine/geom.c ../src/engine/body.c ../src/engine/render.c \
  ../src/engine/texture.c \
  ../src/game/board.c ../src/game/level.c ../src/game/camera.c \
  ../src/game/bike.c ../src/game/track_mesh.c ../src/game/scene.c \
  ../src/game/game.c ../src/game/save.c ../src/game/audio.c \
  ../src/platform/fs.c ../src/platform/wav.c \
  ../src/ui/preview.c ../src/ui/art.c ../src/ui/glyphs.c \
  ../src/ui/optionlist.c ../src/ui/menu.c ../src/ui/pause.c \
  ../src/ui/start.c ../src/ui/settings.c ../src/ui/result.c ../src/app.c \
  ../src/ui/font.c \
  stub/gl_stub.c log_stub.c blobs_stub.c audio_stub.c

SRC   := $(CORE) test_game.c
SHOTS := $(CORE) screenshot.c

all: run

run: test_game
	cd .. && BR_ASSET_ROOT=assets_out tests/test_game $(ARGS)

test_game: $(SRC)
	$(CC) $(CFLAGS) -o $@ $(SRC) $(LDFLAGS)

# Renders a few frames to PNG so the scene can be looked at without a Vita.
shots: screenshot
	cd .. && mkdir -p tests/shots && BR_ASSET_ROOT=assets_out tests/screenshot tests/shots

screenshot: $(SHOTS)
	$(CC) $(CFLAGS) -o $@ $(SHOTS) $(LDFLAGS)

clean:
	rm -f test_game screenshot
	rm -rf shots

.PHONY: all run shots clean
