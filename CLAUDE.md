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
| Vita FTP port | `1337` (VitaShell) |
| Log port | UDP `18194` |
| Title ID | `BIKR00001` |

The IPs are **not** listed here, because they change with whatever network the
Vita is on and a table in a README goes stale the first time it does. They live
in one place — [scripts/env.sh](scripts/env.sh) — and every script reads them
from there. Run any script, or `./scripts/deploy.sh`, to see the ones in force.

`env.sh` carries a home-network default. To point at a different network
without touching a tracked file, create `scripts/env.local.sh` (gitignored):

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
curl -T build/eboot.bin "$FTP_URL/ux0:/app/BIKR00001/eboot.bin"
```

A full `.vpk` install is needed the first time, and whenever the LiveArea
assets or `param.sfo` change. Assets under `assets_out/` go to
`ux0:/data/bikerace/` separately — see option (C) in `deploy.sh`.

### `sce_sys` artwork must be 8-bit palette PNG

An install that reaches 99% and then fails with **`0x8010113D`** means
`scePromoterUtility` rejected the package's artwork. `icon0.png`, `bg.png` and
`startup.png` must be **PNG colour type 3** — 8-bit palette, with a `PLTE`
chunk — at exactly 128×128, 840×500 and 280×158. Truecolour RGB is rejected.
(This is what "run pngquant on your images" means in the forum threads; it is
confirmed by VitaShell's own VPK, whose three `sce_sys` PNGs are all type 3.)

`scripts/build.sh` runs `scripts/check_sce_sys.py` before every build so this
cannot reach the Vita again. Regenerate the artwork with
`./scripts/make_livearea.sh`, which writes `PNG8:` output and verifies it.

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
curl "$FTP_URL/ux0:/data/$(curl -s "$FTP_URL/ux0:/data/" \
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

## PlayStation TV

The port runs on a PSTV as well as a handheld. It is the same hardware with two
things taken away, and both are handled in
[src/platform/device.c](src/platform/device.c), which asks
`sceKernelIsPSVitaTV()` once and caches the answer.

**No touch panel.** Touch has always been the alternative rather than the only
way to do anything — every tap has a button beside it — so nothing is
unreachable. What changes is that the game stops offering it: the two touch
rows drop out of the controls reference, the "tap" hint leaves the start
screen, and the back button in the corner is not drawn, with the button hints
sliding over into its place. `br_input_poll` skips `sceTouchPeek` entirely.

**The picture goes to a television**, which may crop its own edges. `br_ui_begin`
applies a 2.5%-per-edge inset on a PSTV so no interface lands where a set that
overscans would cut it — the clock, the level name and the star times most of
all. The scene is not inset and still fills the screen; backdrops and dimming
layers use `br_fill_screen()` / `br_draw_screen()`, which cover the real screen
rather than the inset 960x544, or the inset would show as a black frame.

`make -C tests run` runs the whole suite twice, the second time with
`BR_FAKE_TV=1`, which is what `device.c` reads instead of the kernel call on
the host. `make -C tests shots` writes the TV layout to `tests/shots/tv/`. The
case that matters is **the whole game plays with the pad alone**, which drives
start, menus, race, pause, result, settings and exit with nothing but buttons:
a screen that could only be left by tapping it fails there and nowhere else.

Controller reads are `sceCtrlPeekBufferPositive` on port 0, which is right for
both machines — on a PSTV that is the first paired pad, and a DualShock's L1
and R1 arrive as `LTRIGGER` and `RTRIGGER`, which is what the lean reads. Do
not switch to the `Ext2` variants for this: they renumber the ports *and* move
the shoulder buttons to `L1`/`R1`.

## Controls

| | |
|---|---|
| Accelerate | Cross, or the right half of the touchscreen |
| Brake / reverse | Square, or the left half of the touchscreen |
| Lean forward (nose down) | R trigger, left stick right, or d-pad right |
| Lean back (wheelie) | L trigger, left stick left, or d-pad left |
| Reset the level | Circle |
| Pause | Start |
| After a run: next / repeat / level list | Cross / Circle / Start |
| Menus: move, choose, back | d-pad, stick or touch; Cross; Circle |
| Bike list | Triangle, or the cell after the last world |
| Quit | Exit, on the start screen |

Every touch entry above is an alternative, never the only way -- which is what
makes the PlayStation TV work. Nothing else closes the game: Start+Select does
not, and Circle backs out to the start screen rather than dropping you out.
Choosing Exit asks before it quits.

A run always begins with the throttle shut until Cross is released and pressed
again, so the press that dismissed a menu cannot also start the clock.

Holding brake for a quarter second once the bike has stopped engages reverse,
and it stays engaged until the button is released. The original had no reverse
except on the Santa bike, whose brake *was* reverse; that bike still behaves
that way.

Lean is clamped to ±0.5 before it reaches the bike, which is the range the
Android accelerometer produced. Positive torque rotates the bike backwards, so
`br_input.lean` (+1 = stick right) is negated on its way in.

## Verifying before you build

`make -C tests run` compiles the game's own sources natively with GL and the
logger stubbed, and runs everything twice -- once as a handheld, once as a
PlayStation TV (see that section). It runs the real physics over all 152
levels, plays 1-1 to the finish line, and checks the camera and broad-phase
invariants.

`make -C tests shots` renders real frames to `tests/shots/*.png` through a
small software rasteriser in the stub, so the scene transforms, atlas regions
and draw order can be looked at directly. The PlayStation TV layout lands in
`tests/shots/tv/`.

Anything that drives a race in the harness must **release the throttle for one
frame** before holding it, exactly as a player does. A run that holds Cross
from the first frame leaves the start-of-run latch engaged and the bike never
moves -- and because the camera follows the bike, the frame still looks
plausible. That silently invalidated every race screenshot once.

Run both before building a VPK. They are much faster than a flash-and-look
cycle and they catch the bugs a UDP log cannot explain.

## Screens

`src/app.c` owns the flow: start screen, settings, the world/level/bike menus,
the race, pause, and the end-of-run panel. Every screen is its own module under
`src/ui/`. Short, obvious choices -- resume, retry, next -- use the game's own
round buttons in a row via `src/ui/iconbar.c`; wordier lists use
`src/ui/optionlist.c`. Both share the same navigation contract rather than each
growing its own. The button reference lives once in `src/ui/controls.c`, since
settings shows all of it and pause shows only the race half. Anything that
cannot be taken back asks first, with "no" selected: reset, unlock everything,
and leaving the game. The way out is one button in one corner -- `br_ui_back_button_*` in `src/ui/art.c` -- shared
by the world, level and bike grids and by settings, so "back" is always in the
same place and every screen reachable by touch can be left by touch. Colours live in `src/ui/theme.h`; controller glyphs are drawn from
primitives in `src/ui/glyphs.c`, since a touch game shipped none.

Buttons are the game's own level tile nine-sliced (`br_draw_nine`), so the
bevel keeps its shape at any size. Modals are the game's paper set into a
mud-brown frame via `br_ui_panel`. The in-race HUD has no panels at all: it
draws straight onto the game with a dark ring behind the text
(`br_font_draw_outlined`), because a panel there covers artwork and plain text
disappears into a bright sky.

## Sound

`GameAudio`'s engine is five recorded notes crossfaded by a state machine, not
one pitch-shifted loop: idle, slow, a one-shot climb, fast, and a one-shot drop
back. The climb and drop hand over at 95% played so the change lands on the
sample's own beat. Landings sound only above a force threshold and only after
air time. All of that is ported as-is in [src/game/audio.c](src/game/audio.c).

**The menus are silent, deliberately.** The original made no interface sound,
and this port tried adding one twice — graded clicks, then a single tick for
everything — and settled on neither. A press that the gate refuses is answered
visibly instead, by shaking the tile. Do not add menu sounds back without being
asked for them.

The mixing itself is in [src/platform/mixer.c](src/platform/mixer.c) with no
platform in it, so the host tests run the real code;
[src/platform/audio.c](src/platform/audio.c) only owns the 48 kHz stereo output
port and its thread. Each voice resamples from its own rate, so the game's
22 kHz mono files play untouched.

A voice's position is a whole frame count plus a separate fraction, **not** one
16.16 number. A single 16.16 value only addresses 65535 frames -- three seconds
at 22 kHz -- so anything longer never reaches its own end and restarts for
ever. That is a real bug this port shipped once: it made the four-second win
sting repeat and the three-minute menu track loop its opening. WAV loading lives apart in `src/platform/wav.c` so
the host tests load the real files and run the real state machine.

## Fonts and save data

Text is drawn from the two faces the APK ships, baked into glyph atlases by
`./scripts/makefonts.sh` and linked into the executable next to the level data.
Keeping them in the binary means text works before anything has been copied to
`ux0:data`, which is what makes an on-screen error message possible at all.

Progress lives in `ux0:data/bikerace/save.bin` — stars, best time and unlock
state per level, plus where to reopen the menu. A missing or unrecognised file
is not an error; it just looks like a first run.

Which is exactly why **nothing is ever written in place**. A write caught
half-done by a flat battery would leave a file the loader cannot parse, and the
loader would call that a first run and start over. So a flush builds
`save.tmp`, closes it, and only then swaps it in — `br_fs_replace()` in
[src/platform/fs.c](src/platform/fs.c). That is not `rename(2)`: `sceIoRename`
refuses to overwrite an existing destination, so the old file has to go first,
which leaves a window of microseconds in which neither name resolves.
`br_fs_open_saved()` closes it by reading the temporary when the real file is
absent — it is already whole by the time the window opens. `ghosts.bin` is
written the same way.

A press on a locked tile shakes it for a third of a second. The gate used to
swallow the press entirely, which reads as the menu having missed it rather
than having refused it. It is the only feedback a refused press gets, since the
menus make no sound.

Gating follows the original: finishing a level opens the next, rolling into the
next world, and a world needs a running star total — 12, 28, 44 and so on to
228 for world 12, and 66 for the seasonal ones. **World 16 is absent from the
original's table**, so it costs nothing; that quirk is kept on purpose. The
original also gated worlds behind multiplayer wins, invited friends and bikes
bought from the shop, none of which exist here, so only the star totals
survive.

Settings can unlock everything or reset progress, each behind a confirmation
that defaults to "no". Reset clears stars, times, locks **and every recorded
ghost**: a ghost is a best time made visible, so leaving one behind would put
the old run on the track beside a save that no longer remembers the time it
set. The ghosts live in their own file, which `src/app.c` owns and the settings
screen has never seen, so the screen reports `BR_SETTINGS_RESET` and the app
finishes the job. Sound, music and the chosen bike survive a reset — those are
preferences, not something that was earned. Saves written before gating existed have their
unlocks worked out from which levels have stars.

## Ghosts

Your best run on a level replays beside you, faded. Only the rider's head
position and the frame angle are recorded, at 20 Hz; the wheels are derived
from those exactly as the original derived them for a replay bike, so a ghost
needs no physics of its own. Runs past 80 seconds are not recorded rather than
truncated.

The run never records a wheel angle, so the ghost's wheels are rolled from the
distance travelled, summed along the path as it records. Anything that changes
the level or the ghost has to call `refresh_ghost()`: the ghost's bike sprite
is loaded once, so a record set on a different bike would otherwise keep
drawing the old one until the level was re-entered.

Ghosts live in `ux0:data/bikerace/ghosts.bin`, apart from `save.bin`, so a
damaged ghost can never cost anyone their progress. Resetting the progress
clears them too.

A run is stored by its slot index, so the file also records the shape of the
level pack it was recorded against. A file for a pack of another shape is
dropped rather than loaded — ghosts are re-earnable, and one on the wrong track
is worse than none. Pause offers a switch to
hide it, and only when the level has one.

`br_game.ghost_hidden` is the player's own choice, deliberately **not** the
same thing as whether a ghost exists. One flag for both meant a ghost created
by a first completion inherited the "off" that only ever meant "there is
nothing to draw", so it stayed invisible until the level was left and
re-entered. The choice resets when the level changes; it survives a restart of
the same level.

## LiveArea

The gate -- the button you press to launch -- cannot be placed freely. Its
position comes from the `style` attribute, and of the documented styles `a1`
centres it and `psmobile` puts it on the right; **none place it on the left**.
So the background is mirrored and `psmobile` is used, which puts the rider on
the left facing the gate instead of being covered by it. Styles a2 to a5 exist
but are undocumented, and a wrong one costs a full reinstall to find out.

The bubble on the home screen keeps the game's own icon rather than a crop of
the scene -- that icon is what the app is recognised by.

Regenerate with `./scripts/make_livearea.sh`, which also writes the
template.xml so the style and the mirroring cannot drift apart.

## Level data

Levels are Java in the original — `LevelFactoryWorld1..19` build each track by
calling `LevelBoardsBuilder`. Rather than transcribe ~1450 builder calls,
`tools/leveldump` compiles those decompiled factories against stubs for the few
Android classes they touch, runs them, and writes the geometry to
`data/levels.bin` (19 worlds, 152 levels, 30284 segments, ~490 KB).

That file is linked into the executable with `.incbin`, so **a code-only patch
still only needs `eboot.bin`**. Regenerate it with `./scripts/dumplevels.sh`.

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
