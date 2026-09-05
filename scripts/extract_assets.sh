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

# Music stays compressed -- 208 s of stereo PCM would be ~40 MB per track.
# Ogg Vorbis decodes cheaply on the Vita via stb_vorbis.
echo ">> music"
for f in apk/res/raw/*.mp3; do
  ffmpeg -v error -y -i "$f" -c:a libvorbis -q:a 4 \
         "$OUT/music/$(basename "${f%.mp3}").ogg"
done
echo "   $(ls "$OUT"/music | wc -l) tracks"

# --- menu art ---------------------------------------------------------------
# Only the pieces src/ui/menu.c draws, so the transfer stays small.
echo ">> menu art"
mkdir -p "$OUT/ui"
for f in fundo.png logo.png button_background_default.png \
         button_level_default.png button_level_pressed.png \
         star_fill_small.png star_empty_dark.png button_back_default.png; do
  cp "apk/res/drawable-xhdpi/$f" "$OUT/ui/$f"
done
echo "   $(ls "$OUT"/ui | wc -l) files"

# --- fonts ------------------------------------------------------------------
# Kept only as the source scripts/makefonts.sh bakes from.
echo ">> fonts"
cp apk/assets/fonts/*.ttf "$OUT/fonts/" 2>/dev/null || true
echo "   $(ls "$OUT"/fonts | wc -l) fonts"

echo
echo ">> assets_out/ is $(du -sh "$OUT" | cut -f1)"
echo ">> deploy it with option (C) from ./scripts/deploy.sh"
