#!/usr/bin/env bash
# Build the VPK inside the vitasdk Docker image.
#
#   ./scripts/build.sh              incremental build
#   ./scripts/build.sh clean        wipe build/ first
#   ./scripts/build.sh verbose      compile in per-frame LOGV() output
#
# Outputs build/BikeRace.vpk and build/eboot.bin.
set -euo pipefail
. "$(dirname "${BASH_SOURCE[0]}")/env.sh"

CMAKE_EXTRA=()
for arg in "$@"; do
  case "$arg" in
    clean)   rm -rf "$BUILD_DIR" ;;
    verbose) CMAKE_EXTRA+=(-DBR_VERBOSE_LOG=ON) ;;
    *)       CMAKE_EXTRA+=("$arg") ;;
  esac
done

if ! docker image inspect "$DOCKER_IMAGE" >/dev/null 2>&1; then
  echo ">> building docker image $DOCKER_IMAGE (first run only)"
  docker build -t "$DOCKER_IMAGE" "$REPO_ROOT/docker"
fi

mkdir -p "$BUILD_DIR"

docker run --rm \
  -u "$(id -u):$(id -g)" \
  -e HOME=/tmp \
  -v "$REPO_ROOT:/src" \
  -w /src \
  "$DOCKER_IMAGE" \
  bash -c "
    set -e
    cmake -S /src -B /src/$(basename "$BUILD_DIR") \
      -DCMAKE_TOOLCHAIN_FILE=\$VITASDK/share/vita.toolchain.cmake \
      -DCMAKE_BUILD_TYPE=Release \
      -DBR_LOG_HOST=$PC_IP \
      -DBR_LOG_PORT=$LOG_PORT \
      ${CMAKE_EXTRA[*]:-}
    cmake --build /src/$(basename "$BUILD_DIR") -j\$(nproc)
  "

echo
echo "=========================================================="
echo " build ok"
ls -la "$BUILD_DIR"/*.vpk "$BUILD_DIR"/eboot.bin 2>/dev/null | sed 's/^/  /'
echo "=========================================================="
echo
"$REPO_ROOT/scripts/deploy.sh"
