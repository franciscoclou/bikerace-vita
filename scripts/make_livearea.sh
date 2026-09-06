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
mkdir -p sce_sys/livearea/contents
tools/makelivearea.py sce_sys

# psmobile is the only documented style that does not centre the gate; a1
# centres it and nothing documented puts it on the left.
cat > sce_sys/livearea/contents/template.xml <<'XML'
<?xml version="1.0" encoding="utf-8"?>
<livearea style="psmobile" format-ver="01.00" content-rev="1">
  <livearea-background>
    <image>bg.png</image>
  </livearea-background>
  <gate>
    <startup-image>startup.png</startup-image>
  </gate>
</livearea>
XML

# Fail loudly rather than shipping a VPK that will not install.
python3 "$REPO_ROOT/scripts/check_sce_sys.py"

echo
echo ">> sce_sys artwork regenerated -- rebuild and reinstall the full VPK"
