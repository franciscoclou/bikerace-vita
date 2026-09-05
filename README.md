# Bike Race — PS Vita

A port of **Bike Race Pro 4.3** (Top Free Games, Android, 2014) to the
PlayStation Vita.

The original ships no native code — it is Java on a `GLSurfaceView` with its own
small engine — so this is a rewrite in C against the decompiled sources,
reusing the original art, audio and level data.

## Quick start

```sh
./scripts/setup.sh          # once: jadx, vita-parse-core, docker image
./scripts/decompile.sh      # once: apk/classes.dex -> decompiled/
./scripts/extract_assets.sh # once: apk/res -> assets_out/
./scripts/build.sh          # build/BikeRace.vpk (+ the deploy command)
```

The build ends by printing the `curl` command that copies it to the Vita. It
never transfers anything itself.

## Controls

Accelerate with **Cross**, brake with **Square** (keep it held once stopped to
reverse). Lean forward with **R** and back with **L**, or use the **left stick**
/ **d-pad**. Reset with **Circle**, quit with **Start + Select**.

## While developing

```sh
./scripts/log.sh            # live UDP debug log -> debug.log
./scripts/getdump.sh        # pull the newest crash dump off the Vita
./scripts/parsedump.sh      # symbolise it against build/bikerace
./scripts/find.sh <Class>   # locate decompiled Java by original class name
```

Network addresses, title id and paths all live in
[scripts/env.sh](scripts/env.sh). Override them in `scripts/env.local.sh`
(gitignored) rather than editing tracked files.

## Documentation

- [CLAUDE.md](CLAUDE.md) — workflow, build, deploy, debugging
- [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md) — how the original is built and
  what porting each part involves
- [docs/PORTING.md](docs/PORTING.md) — what is done, what is next
- `docs/class_map.tsv` — ProGuard-obfuscated name → original filename

## Requirements

Docker, `curl`, `netcat`, `ffmpeg`, ImageMagick, `unzip`, a JRE (for jadx).
The vitasdk in `~/vitasdk` is **not** used — its binaries need glibc 2.38 and
this machine has 2.35, so all compilation happens inside the Docker image.

## Legal

Game code, art and audio are © Top Free Games. This repository contains porting
work only; the extracted APK is present as reference material for that work and
nothing here is distributable as a game.
