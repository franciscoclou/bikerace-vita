#!/usr/bin/env bash
# Live debug log from the Vita.
#
#   ./scripts/log.sh          append to debug.log and tail it
#   ./scripts/log.sh -c       truncate debug.log first
#   ./scripts/log.sh -q       capture only, do not tail
#
# The game sends each log line as a UDP datagram to PC_IP:LOG_PORT and to the
# broadcast address, so this keeps working if the PC's IP changes.
set -uo pipefail
. "$(dirname "${BASH_SOURCE[0]}")/env.sh"

TAIL=1
for a in "$@"; do
  case "$a" in
    -c) : > "$DEBUG_LOG"; echo ">> truncated $DEBUG_LOG" ;;
    -q) TAIL=0 ;;
  esac
done

echo ">> listening on UDP *:$LOG_PORT  ->  $DEBUG_LOG"
echo ">> (Vita $VITA_IP, this PC should be $PC_IP -- check with 'hostname -I')"
echo ">> Ctrl-C to stop"
echo

stdbuf -oL nc -u -k -l "$LOG_PORT" >> "$DEBUG_LOG" 2>&1 &
NC_PID=$!
trap 'kill $NC_PID 2>/dev/null' EXIT INT TERM

if [ "$TAIL" = 1 ]; then
  tail -f -n 0 "$DEBUG_LOG"
else
  wait $NC_PID
fi
