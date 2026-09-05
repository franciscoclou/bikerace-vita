#!/usr/bin/env bash
# Symbolise a psp2core crash dump against the unstripped ELF from the build
# that produced it.
#
#   ./scripts/parsedump.sh                       newest dump + build/bikerace
#   ./scripts/parsedump.sh dumps/foo.psp2dmp     a specific dump
#   ./scripts/parsedump.sh foo.psp2dmp bar.elf   explicit pair
#
# Writes the report next to the dump as <dump>.txt and prints it.
set -euo pipefail
. "$(dirname "${BASH_SOURCE[0]}")/env.sh"

DUMP="${1:-$DUMP_DIR/psp2core-latest.psp2dmp}"
ELF="${2:-$BUILD_DIR/bikerace}"

[ -f "$DUMP" ] || { echo "!! no dump at $DUMP -- run ./scripts/getdump.sh first" >&2; exit 1; }
[ -f "$ELF" ]  || { echo "!! no ELF at $ELF -- run ./scripts/build.sh first" >&2; exit 1; }

# A dump older than the ELF was produced by different code; the line numbers
# would be fiction.
if [ "$ELF" -nt "$DUMP" ]; then
  echo "!! WARNING: $ELF is NEWER than $DUMP."
  echo "!!          You rebuilt after this crash, so addresses will not line up."
  echo "!!          Reflash that build and reproduce, or use the archived ELF."
  echo
fi

if [ ! -d "$REPO_ROOT/tools/vita-parse-core" ]; then
  echo ">> fetching vita-parse-core"
  git clone --depth 1 https://github.com/xyzz/vita-parse-core.git \
      "$REPO_ROOT/tools/vita-parse-core"
fi

OUT="${DUMP%.psp2dmp}.txt"

docker run --rm \
  -u "$(id -u):$(id -g)" \
  -e HOME=/tmp \
  -v "$REPO_ROOT:/src" \
  -w /src/tools/vita-parse-core \
  "$DOCKER_IMAGE" \
  python3 main.py "/src/${DUMP#$REPO_ROOT/}" "/src/${ELF#$REPO_ROOT/}" \
  > "$OUT" 2>&1 || true

cat "$OUT"
echo
echo ">> report saved to $OUT"
