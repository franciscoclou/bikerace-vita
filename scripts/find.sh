#!/usr/bin/env bash
# Locate the decompiled Java for an original (pre-obfuscation) class name.
#
#   ./scripts/find.sh LevelFactoryWorld3     prints matching paths
#   ./scripts/find.sh -o Body                opens the first match in $PAGER
set -euo pipefail
. "$(dirname "${BASH_SOURCE[0]}")/env.sh"

OPEN=0
if [ "${1:-}" = "-o" ]; then OPEN=1; shift; fi
[ $# -ge 1 ] || { echo "usage: $0 [-o] <OriginalClassName>" >&2; exit 1; }

MATCHES="$(grep -i -- "$1" "$REPO_ROOT/docs/class_map.tsv" || true)"
[ -n "$MATCHES" ] || { echo "no match for '$1' in docs/class_map.tsv" >&2; exit 1; }

printf '%s\n' "$MATCHES" | while IFS=$'\t' read -r obf orig; do
  path="decompiled/sources/$(printf '%s' "$obf" | tr '.' '/').java"
  printf '%-24s %s\n' "$orig" "$path"
done

if [ "$OPEN" = 1 ]; then
  first="$(printf '%s\n' "$MATCHES" | head -1 | cut -f1 | tr '.' '/')"
  ${PAGER:-less} "$REPO_ROOT/decompiled/sources/$first.java"
fi
