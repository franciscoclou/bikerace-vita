#!/usr/bin/env bash
# Regenerate the VPK's sce_sys artwork from the original Android app icon.
#
#   ./scripts/make_livearea.sh
#
# These PNGs must be 8-bit PALETTE (PNG colour type 3, with a PLTE chunk).
# A truecolour RGB PNG makes scePromoterUtility reject the package at 99%
# with error 0x8010113D -- which is what "you forgot to run pngquant" means
# in every forum thread about that error. Verified against the sce_sys images
# in VitaShell's own VPK, which are all colour type 3.
#
# Sizes are fixed by Sony: icon0 128x128, startup 280x158, bg 840x500.
set -euo pipefail
. "$(dirname "${BASH_SOURCE[0]}")/env.sh"

cd "$REPO_ROOT"
SRC="apk/res/drawable-xhdpi/icon.png"
[ -f "$SRC" ] || { echo "!! missing $SRC" >&2; exit 1; }

mkdir -p sce_sys/livearea/contents

# PNG8: forces palette output; -strip drops colour-profile chunks we do not
# want the Vita's decoder to have to deal with.
convert "$SRC" -resize 128x128! -background black -alpha remove -alpha off \
        -strip -dither FloydSteinberg -colors 256 PNG8:sce_sys/icon0.png

convert "$SRC" -resize 280x158^ -gravity center -extent 280x158 \
        -background black -alpha remove -alpha off \
        -strip -dither FloydSteinberg -colors 256 PNG8:sce_sys/livearea/contents/startup.png

convert "$SRC" -resize 840x500^ -gravity center -extent 840x500 -blur 0x12 -modulate 70 \
        -background black -alpha remove -alpha off \
        -strip -dither FloydSteinberg -colors 256 PNG8:sce_sys/livearea/contents/bg.png

# Fail loudly rather than shipping a VPK that will not install.
python3 "$REPO_ROOT/scripts/check_sce_sys.py"

echo
echo ">> sce_sys artwork regenerated -- rebuild and reinstall the full VPK"
