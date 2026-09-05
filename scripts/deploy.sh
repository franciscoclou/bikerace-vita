#!/usr/bin/env bash
# Prints the commands to copy the build onto the Vita.
# It deliberately does NOT transfer anything -- you run these yourself.
set -euo pipefail
. "$(dirname "${BASH_SOURCE[0]}")/env.sh"

VPK="$BUILD_DIR/$VPK_NAME"
EBOOT="$BUILD_DIR/eboot.bin"

cat <<TXT
------------------------------------------------------------------
 SEND TO VITA   (Vita $VITA_IP:$VITA_FTP_PORT, title $TITLE_ID)
------------------------------------------------------------------

 A) Fast path -- code changes only (usual case).
    Replaces just the executable of an already-installed app:

    curl -T "$EBOOT" "$FTP_URL/ux0:/app/$TITLE_ID/eboot.bin"

    Then close and relaunch the app on the Vita.

 B) Full install -- first time, or when assets / LiveArea / the
    param.sfo changed:

    curl -T "$VPK" "$FTP_URL/ux0:/data/$VPK_NAME"

    Then on the Vita open VitaShell, browse to ux0:/data/,
    press X on $VPK_NAME and confirm the install.

 C) Assets -- needed once, and again whenever
    ./scripts/extract_assets.sh changes what it produces.

    Everything the game loads, minus the optional menu track (~8 MB):
    the ten world atlases, all 21 bikes, the menu art and the sounds.

    find assets_out/textures assets_out/ui assets_out/sfx -type f \\
      | while read -r f; do
          curl --ftp-create-dirs -T "\$f" \\
               "$FTP_URL/ux0:/data/bikerace/\${f#assets_out/}"
        done

    The menu music is a further 8.8 MB on its own, and the game runs
    without it:

    curl --ftp-create-dirs -T assets_out/music/musica_menu.wav \\
         "$FTP_URL/ux0:/data/bikerace/music/musica_menu.wav"

------------------------------------------------------------------
TXT
