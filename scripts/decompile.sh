#!/usr/bin/env bash
# Decompile apk/classes.dex into decompiled/ and regenerate the obfuscation map.
#
# The APK was run through ProGuard, so class names are single letters. jadx
# keeps the original filename in a "compiled from:" comment on every class, and
# docs/class_map.tsv is built from those -- it is how you find the Java behind
# a given piece of game logic.
set -euo pipefail
. "$(dirname "${BASH_SOURCE[0]}")/env.sh"

cd "$REPO_ROOT"
[ -x tools/jadx/bin/jadx ] || { echo "!! run ./scripts/setup.sh first" >&2; exit 1; }

rm -rf decompiled
tools/jadx/bin/jadx -d decompiled --no-res -j "$(nproc)" --show-bad-code apk/classes.dex

grep -r "JADX INFO: compiled from:" decompiled/sources \
  | sed -E 's|^decompiled/sources/||; s|\.java:/\* JADX INFO: compiled from: |\t|; s| \*/$||' \
  | sed -E 's|/|.|g' | sed -E 's|\.java\t|\t|' | sort > docs/class_map.tsv

echo
echo ">> $(find decompiled -name '*.java' | wc -l) Java files in decompiled/"
echo ">> $(wc -l < docs/class_map.tsv) entries in docs/class_map.tsv"
echo
echo "   Find the class behind a filename:"
echo "     grep -i 'LevelFactoryWorld3' docs/class_map.tsv"
echo "   Then open decompiled/sources/<that path with / instead of .>.java"
