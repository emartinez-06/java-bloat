#!/usr/bin/env bash
set -euo pipefail

cd -- "$(dirname -- "${BASH_SOURCE[0]}")"

if [[ ! -d exam_two_frontend/node_modules ]]; then
  echo "Install frontend dependencies first: cd exam_two_frontend && npm ci" >&2
  exit 1
fi

# Stop each server and its descendants, including npm and Gradle children.
stop_tree() {
  local child
  for child in $(pgrep -P "$1" || true); do
    stop_tree "$child"
  done
  kill "$1" 2>/dev/null || true
}

cleanup() {
  trap '' INT TERM
  for pid in ${backend_pid:-} ${frontend_pid:-}; do
    stop_tree "$pid"
  done
  wait 2>/dev/null || true
}
trap cleanup EXIT
trap 'exit 130' INT
trap 'exit 143' TERM

echo "Frontend: http://localhost:3000"
echo "Backend:  http://localhost:8080"
echo "Press Ctrl+C to stop both."

./lab2exam/dev.sh "$@" &
backend_pid=$!
npm --prefix exam_two_frontend run dev -- --port 3000 &
frontend_pid=$!

# Compatible with the Bash version shipped with macOS (no wait -n).
while kill -0 "$backend_pid" 2>/dev/null && kill -0 "$frontend_pid" 2>/dev/null; do
  sleep 1
done

status=0
if ! kill -0 "$backend_pid" 2>/dev/null; then
  wait "$backend_pid" || status=$?
else
  wait "$frontend_pid" || status=$?
fi
exit "$status"
