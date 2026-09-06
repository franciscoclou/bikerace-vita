#!/usr/bin/env bash
# Build assets_out/ -- the runtime data the Vita build loads from
# ux0:data/bikerace/.
#
#   ./scripts/extract_assets.sh
#
# Nothing here is committed: assets_out/ is gitignored and regenerated from
# apk/ on demand. Deploy it with option (C) of ./scripts/deploy.sh.
set -euo pipefail
. "$(dirname "${BASH_SOURCE[0]}")/env.sh"

cd "$REPO_ROOT"
OUT="$REPO_ROOT/assets_out"
rm -rf "$OUT"
mkdir -p "$OUT/textures" "$OUT/sfx" "$OUT/music" "$OUT/fonts"

# --- textures ---------------------------------------------------------------
# The gameplay atlases live in drawable-nodpi and are already 2048x2048 or
# 1024x1024, comfortably under the Vita's 4096 GXM limit, so they are copied
# verbatim. UI art comes from drawable-xhdpi (the densest set); the Vita's
# 960x544 screen is between Android's hdpi and xhdpi, and downscaling xhdpi
# looks better than upscaling hdpi.
echo ">> textures"
cp apk/res/drawable-nodpi/*.png "$OUT/textures/"
cp apk/res/drawable-xhdpi/*.png "$OUT/textures/" 2>/dev/null || true

# Anything not a power of two on both axes needs padding before it can be a GXM
# texture; report them so nothing surprises us at runtime.
npot="$(identify -format '%w %h\n' "$OUT"/textures/*.png 2>/dev/null | awk '
  function pot(n) { return n > 0 && and(n, n - 1) == 0 }
  { if (!pot($1) || !pot($2)) c++ } END { print c + 0 }')"
echo "   $(ls "$OUT"/textures | wc -l) textures ($npot non-power-of-two -- these need atlasing or padding)"

# --- audio ------------------------------------------------------------------
# Sound effects become 16-bit signed mono PCM at 22050 Hz, matching what the
# APK already shipped, so they can be handed straight to sceAudio.
echo ">> sound effects"
for f in apk/res/raw/*.wav; do
  ffmpeg -v error -y -i "$f" -ac 1 -ar 22050 -c:a pcm_s16le \
         "$OUT/sfx/$(basename "${f%.wav}").wav"
done
echo "   $(ls "$OUT"/sfx | wc -l) effects"

# The mixer plays 16-bit mono PCM, so the menu track is converted to match the
# effects rather than carrying an Ogg decoder for one file. It is the largest
# asset by far and the game runs fine without it, so only the track the menu
# actually uses is produced.
echo ">> music"
ffmpeg -v error -y -i apk/res/raw/musica_menu.mp3 -ac 1 -ar 22050 -c:a pcm_s16le \
       "$OUT/music/musica_menu.wav"
echo "   $(du -h "$OUT"/music/musica_menu.wav | cut -f1) menu track"

# --- menu art ---------------------------------------------------------------
# Only the pieces src/ui/menu.c draws, so the transfer stays small.
echo ">> menu art"
mkdir -p "$OUT/ui"
for f in fundo.png logo.png button_background_default.png \
         button_level_default.png button_level_pressed.png \
         star_fill_small.png star_empty_dark.png button_back_default.png \
         pause_fundo.png start_screen.png result_window.png \
         button_retry_default.png button_next_default.png \
         button_menu_default.png button_play_final_default.png \
         display_tiremarks.png display_label.png lock_level.png; do
  cp "apk/res/drawable-xhdpi/$f" "$OUT/ui/$f"
done
# The bike list shows wheels, but wheels live inside the world atlases. Crop
# the three variants out once so the menus never have to load an atlas. The
# regions are the same in every atlas, so world 1's will do.
ATLAS=apk/res/drawable-nodpi/bikerace_textura1b.png
crop_wheel() {  # name left top right bottom  (normalised, as TextureCoordinates has them)
  convert "$ATLAS" -crop \
    "$(python3 -c "
import sys
from PIL import Image
w,h = Image.open('$ATLAS').size
l,t,r,b = float('$2'),float('$3'),float('$4'),float('$5')
print('%dx%d+%d+%d' % (round((r-l)*w), round((b-t)*h), round(l*w), round(t*h)))")" \
    +repage "$OUT/ui/$1"
}
crop_wheel wheel.png           0.95654297 0.32763672 0.98583984 0.3569336
crop_wheel wheel_halloween.png 0.95654297 0.35888672 0.98583984 0.3876953
crop_wheel wheel_ultra.png     0.95703125 0.39160156 0.98535156 0.41992188
cp apk/res/drawable-nodpi/santa_wheel.png "$OUT/ui/wheel_santa.png"

echo "   $(ls "$OUT"/ui | wc -l) files"

# --- fonts ------------------------------------------------------------------
# Kept only as the source scripts/makefonts.sh bakes from.
echo ">> fonts"
cp apk/assets/fonts/*.ttf "$OUT/fonts/" 2>/dev/null || true
echo "   $(ls "$OUT"/fonts | wc -l) fonts"

echo
echo ">> assets_out/ is $(du -sh "$OUT" | cut -f1)"
echo ">> deploy it with option (C) from ./scripts/deploy.sh"
