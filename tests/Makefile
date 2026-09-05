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

SRC := \
  ../src/engine/geom.c ../src/engine/body.c ../src/engine/render.c \
  ../src/engine/texture.c \
  ../src/game/board.c ../src/game/level.c ../src/game/camera.c \
  ../src/game/bike.c ../src/game/track_mesh.c ../src/game/scene.c \
  ../src/game/game.c ../src/platform/fs.c \
  stub/gl_stub.c log_stub.c levels_blob_stub.c test_game.c

all: run

run: test_game
	cd .. && BR_ASSET_ROOT=assets_out tests/test_game $(ARGS)

test_game: $(SRC)
	$(CC) $(CFLAGS) -o $@ $(SRC) $(LDFLAGS)

clean:
	rm -f test_game

.PHONY: all run clean
