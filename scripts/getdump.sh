#!/usr/bin/env bash
# Pull the newest psp2core crash dump off the Vita over FTP.
#
#   ./scripts/getdump.sh          fetch newest dump -> dumps/psp2core-latest.psp2dmp
#   ./scripts/getdump.sh -a       also archive it under dumps/ with a timestamp
#   ./scripts/getdump.sh -l       just list the dumps on the Vita
#   ./scripts/getdump.sh -r       delete dumps from the Vita after fetching
set -euo pipefail
. "$(dirname "${BASH_SOURCE[0]}")/env.sh"

ARCHIVE=0; LIST_ONLY=0; REMOVE=0
for a in "$@"; do
  case "$a" in
    -a) ARCHIVE=1 ;;
    -l) LIST_ONLY=1 ;;
    -r) REMOVE=1 ;;
  esac
done

mkdir -p "$DUMP_DIR"

listing="$(curl -s --max-time 15 "$FTP_URL/ux0:/data/" || true)"
if [ -z "$listing" ]; then
  echo "!! no response from $FTP_URL -- is the Vita on and VitaShell's FTP running?" >&2
  exit 1
fi

dumps="$(printf '%s\n' "$listing" | awk '{print $NF}' \
         | grep '^psp2core-.*eboot\.bin\.psp2dmp$' | sort -V || true)"

if [ -z "$dumps" ]; then
  echo ">> no crash dumps in ux0:/data/ -- nothing has crashed since the last cleanup"
  exit 0
fi

if [ "$LIST_ONLY" = 1 ]; then
  printf '%s\n' "$dumps"
  exit 0
fi

latest="$(printf '%s\n' "$dumps" | tail -n 1)"
out="$DUMP_DIR/psp2core-latest.psp2dmp"

echo ">> fetching $latest"
curl --max-time 120 "$FTP_URL/ux0:/data/$latest" -o "$out"
echo ">> saved $out ($(du -h "$out" | cut -f1))"

if [ "$ARCHIVE" = 1 ]; then
  cp "$out" "$DUMP_DIR/$latest"
  echo ">> archived $DUMP_DIR/$latest"
fi

if [ "$REMOVE" = 1 ]; then
  printf '%s\n' "$dumps" | while read -r d; do
    curl -s -Q "DELE ux0:/data/$d" "$FTP_URL/ux0:/data/" >/dev/null && \
      echo ">> deleted $d from the Vita"
  done
fi

echo
echo ">> next: ./scripts/parsedump.sh"
