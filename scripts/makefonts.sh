#!/usr/bin/env bash
# Bake the fonts the APK ships into data/font_*.bin, which are linked into the
# executable. Re-run only if the sizes below change.
set -euo pipefail
. "$(dirname "${BASH_SOURCE[0]}")/env.sh"

cd "$REPO_ROOT"
mkdir -p data

# The heavy italic is the face the game's own logo uses -- headings and level
# numbers. The plain sans reads better at small sizes for times and labels.
tools/makefont.py "apk/assets/fonts/Avengeance Mightiest Avenger.ttf" 48 data/font_display.bin
tools/makefont.py "apk/assets/fonts/estre.ttf"                        40 data/font_body.bin

echo
echo ">> rebuild to pick the fonts up"
