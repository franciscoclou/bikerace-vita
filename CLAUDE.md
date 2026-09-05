# Bike Race — PS Vita port

Porting **Bike Race Pro 4.3** (`com.topfreegames.bikeraceproworld`, Top Free
Games, 2014) from Android to the PS Vita.

The original is a **pure Java** game — there is no `lib/*.so` in the APK, so
there is no native code to hook or wrap. The port is a **rewrite in C** against
the decompiled Java, reusing the original's art, audio and level data.

---

## Rules for this repo

- **Commit as you go.** Small, focused commits with a plain description of the
  change. Do not add `Co-Authored-By` trailers, `🤖 Generated with…` footers, or
  any other attribution to Claude — commits are the user's.
- **Never push, never install to the Vita.** Building is yours; transferring is
  the user's. Every build ends by *printing* the `curl` command — see Deploying.
- `apk/` is source material. Treat it as read-only.
- `decompiled/`, `tools/`, `assets_out/`, `build/` are derived and gitignored.
  Anything you want kept must be regenerable by a script in `scripts/`.

## Layout

```
apk/           unzipped APK — read-only reference material
decompiled/    jadx output from apk/classes.dex   (scripts/decompile.sh)
docs/          class_map.tsv, ARCHITECTURE.md, PORTING.md
src/           the C port
  engine/      ports of com.topfreegames.engine.*
  game/        ports of com.topfreegames.bikerace.*
  platform/    Vita-specific: logging, input, audio, filesystem
sce_sys/       icon and LiveArea assets baked into the VPK
scripts/       every workflow lives here
docker/        the build image
build/         CMake output: bikerace (ELF), eboot.bin, BikeRace.vpk
dumps/         crash dumps pulled off the Vita
```

## Environment

| | |
|---|---|
| Vita | `192.168.1.177`, FTP on port `1337` (VitaShell) |
| This PC | `192.168.1.149` |
| Log port | UDP `18194` |
| Title ID | `BIKR00001` |

Those IPs change. They live in one place — [scripts/env.sh](scripts/env.sh) —
and every script reads them from there. To override without touching a tracked
file, create `scripts/env.local.sh` (gitignored):

```sh
PC_IP=192.168.1.42
VITA_IP=192.168.1.99
```

`PC_IP` is compiled into the binary (`-DBR_LOG_HOST`), so changing it needs a
rebuild. The game also broadcasts every log line to `255.255.255.255`, so
logging keeps working even when `PC_IP` is stale.

### Toolchain: always Docker

The vitasdk in `~/vitasdk` **cannot run on this machine** — its binaries need
glibc 2.38 and Ubuntu 22.04 has 2.35. Do not try to fix it, and do not invoke
`arm-vita-eabi-gcc` directly. All compilation goes through
`bikerace-vitasdk:latest`, built from [docker/Dockerfile](docker/Dockerfile).

First time on a fresh checkout:

```sh
./scripts/setup.sh     # jadx, vita-parse-core, docker image
```

## Building

```sh
./scripts/build.sh              # incremental
./scripts/build.sh clean        # from scratch
./scripts/build.sh verbose      # compiles in the per-frame LOGV() output
```

Produces `build/BikeRace.vpk`, `build/eboot.bin`, and `build/bikerace` — the
unstripped ELF, which is what symbolises crash dumps. **Keep it.** Once you
rebuild, dumps from the previous build can no longer be read.

The build prints the deploy commands when it finishes.

## Deploying

**You never send anything to the Vita.** Print the command and hand it over.
`./scripts/deploy.sh` does exactly that.

Most patches only change code, so only `eboot.bin` needs to move:

```sh
curl -T build/eboot.bin ftp://192.168.1.177:1337/ux0:/app/BIKR00001/eboot.bin
```

A full `.vpk` install is needed the first time, and whenever the LiveArea
assets or `param.sfo` change. Assets under `assets_out/` go to
`ux0:/data/bikerace/` separately — see option (C) in `deploy.sh`.

## Debugging

### Live log

The Vita has no console, so `LOGI/LOGW/LOGE` send each line as a UDP datagram
to `PC_IP:18194` *and* to the broadcast address, and mirror it to
`ux0:data/bikerace/debug.log` on the device (which survives a crash that kills
the network).

Start the listener before launching the game:

```sh
./scripts/log.sh          # captures to debug.log and tails it
./scripts/log.sh -c       # truncate debug.log first
```

which wraps:

```sh
stdbuf -oL nc -u -k -l 18194 >> /home/francisco/bike_race/debug.log 2>&1
```

Guidelines: log at boundaries (subsystem init, level load, state transitions),
not per frame. Use `LOGV` for per-frame output — it is compiled out unless you
build with `verbose`. UDP is lossy and unordered; never rely on a log line
being the last thing that happened.

### Crash dumps

A crash writes `ux0:/data/psp2core-*.psp2dmp`. To read it:

```sh
./scripts/getdump.sh       # fetch the newest one into dumps/
./scripts/parsedump.sh     # symbolise it against build/bikerace
```

`getdump.sh` wraps the FTP listing + `sort -V | tail -1` pick:

```sh
curl "ftp://192.168.1.177:1337/ux0:/data/$(curl -s ftp://192.168.1.177:1337/ux0:/data/ \
  | awk '{print $NF}' | grep '^psp2core-.*eboot\.bin\.psp2dmp$' | sort -V | tail -n 1)" \
  -o psp2core-latest.psp2dmp
```

Reading the report: the faulting thread's **stop reason** names the fault class
(data abort = bad pointer, prefetch abort = jumped into garbage, undefined
instruction = corrupted code path or a NULL function pointer). `PC` and `LR`
symbolise to `file:line` when the ELF matches. A `PC` far outside the module's
address range means the return address was smashed — look at the stack, not
at `PC`.

`parsedump.sh` warns when `build/bikerace` is newer than the dump. Heed it:
symbolising against the wrong build produces confident nonsense.

## Working with the decompiled Java

`apk/classes.dex` was run through ProGuard: every game class is one or two
letters. jadx keeps the original filename in a `compiled from:` comment, and
`docs/class_map.tsv` is built from those.

```sh
./scripts/find.sh LevelFactoryWorld3   # -> decompiled/sources/com/topfreegames/bikerace/h/p.java
./scripts/find.sh -o SpringShockAbsorber
```

Field and method names are *not* recoverable — `a`, `b`, `c` throughout. When
you port a class, record the mapping in a comment (see
[src/engine/body.h](src/engine/body.h) for the pattern) so the next person can
check the port against the original.

Read [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md) before porting anything;
[docs/PORTING.md](docs/PORTING.md) tracks what is done and what is next.

## Porting conventions

- **Match the original's behaviour, not its style.** The physics is a quirky
  point-mass-and-springs model, not a real solver. Reproducing it exactly is
  what makes the game feel like the game. Do not "fix" the maths.
- Keep the original's units: metres, y-up, radians for geometry, **degrees**
  for `Body.angle` (the Java did this, and level data depends on it).
- The engine targets OpenGL ES 1.0 fixed-function, which maps directly onto
  vitaGL. Prefer the fixed-function path over writing shaders.
- C99, 4-space indent, `br_`-prefixed public symbols, `snake_case`.
- No dynamic allocation in the frame loop.
