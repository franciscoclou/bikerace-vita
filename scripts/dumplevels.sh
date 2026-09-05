#!/usr/bin/env bash
# Regenerate data/levels.bin from the game's own level code.
#
# Levels in this game are Java, not data files: LevelFactoryWorld1..19 build
# each track by calling into LevelBoardsBuilder. Rather than transcribe ~1450
# builder calls by hand, this compiles the decompiled factories against small
# stubs for the handful of Android classes they touch, runs them, and writes
# the resulting geometry out flat.
#
#   ./scripts/dumplevels.sh
set -euo pipefail
. "$(dirname "${BASH_SOURCE[0]}")/env.sh"

cd "$REPO_ROOT/tools/leveldump"
command -v javac >/dev/null || { echo "!! javac not found -- install a JDK" >&2; exit 1; }

rm -rf classes
mkdir -p classes
javac -d classes -nowarn $(find src -name '*.java')
java -cp classes com.topfreegames.bikerace.h.Dump "$REPO_ROOT/data/levels.bin"

echo
echo ">> data/levels.bin is $(du -h "$REPO_ROOT/data/levels.bin" | cut -f1)"
echo ">> it is linked into eboot.bin, so only a rebuild is needed to pick it up"
