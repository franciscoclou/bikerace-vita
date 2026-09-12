# Bike Race — PS Vita

A from-scratch port of **Bike Race Pro 4.3** (Top Free Games, 2014) to the
PlayStation Vita and PlayStation TV.

The original is a pure Java Android game. There is no `lib/*.so` in the APK, so
there was no native library to hook or wrap — this is a **rewrite in C**,
written against the decompiled Java and running on vitaGL. The physics is
reproduced as the original had it, quirks included: a point-mass-and-springs
model that was never a real solver, and reproducing it exactly is what makes
the game feel like the game.

- **All 19 worlds, 152 levels**, built from the original's own level factories.
- **60 FPS**, vsync-locked.
- **Runs on PS Vita and PlayStation TV.** Touch is supported everywhere but
  required nowhere — every tap has a button beside it.
- **Ghost replays.** Your best run on a level plays back beside you, faded.
- **Progress, star gating and best times**, following the original's rules.
- A **custom interface** built for a controller, since the original shipped a
  touch-only one and no button glyphs at all.

## Controls

| | |
|---|---|
| Accelerate | Cross, or the right half of the touchscreen |
| Brake / reverse | Square, or the left half of the touchscreen |
| Lean forward (nose down) | R, left stick right, or d-pad right |
| Lean back (wheelie) | L, left stick left, or d-pad left |
| Reset the level | Circle |
| Pause | Start |
| Menus: move, choose, back | d-pad or stick; Cross; Circle |
| Bike list | Triangle |
| Quit | Exit, on the start screen |

There is **no tilt or gyro control**. The Android original steered with the
accelerometer; on Vita that range is mapped to the triggers and the left stick
instead, which is both more precise and the only thing a PlayStation TV can do.

---

## ⚖️ Legal

**This repository contains no copyrighted game code, artwork, audio, APK or
`classes.dex`.** It is the C source of an independent reimplementation, plus
the scripts that extract data from a copy of the original game that *you* must
supply.

Specifically, this repository does **not** contain and will never contain:

- the APK, or any part of it
- `classes.dex`, or any decompiled Java from it
- the game's textures, sprites, audio or fonts
- `data/levels.bin` or `data/font_*.bin` — level geometry and glyph atlases
  baked out of the original. These are required to build and are generated
  locally by `scripts/dumplevels.sh` and `scripts/makefonts.sh` from your own
  copy of the APK.

For completeness, two things here **are** derived from the original and are
deliberately included, because a Vita homebrew package cannot function or be
documented without them:

- `sce_sys/` — the LiveArea icon and background, built from the game's art.
  These are baked into the `.vpk` so the app has an icon on the home screen.
- `tests/shots/` — screenshots of the running game, used as visual regression
  references by `make -C tests shots`.

This project is not affiliated with, endorsed by, or connected to Top Free
Games. *Bike Race* and all associated artwork remain their property. You must
own the original game to use this port. No game data is distributed here; the
port is useless without a copy you provide yourself.

---

## Installing

You need the original 2014 APK. The port reads the game's art and audio from
the memory card at runtime — it ships none of it.

### 1. Install the VPK

Download `BikeRace.vpk` from the [Releases](../../releases) tab, copy it to
your Vita (VitaShell over FTP or USB), press **Cross** on it and confirm the
install.

No kernel plugins are needed. This is a native build on vitaGL — it is **not**
an `.so` loader port, so `kubridge` and `fd_fix` are **not** required.

### 2. Get the original APK

Download **Bike Race 4.3** (`com.topfreegames.bikeraceproworld`, 2014):

<https://bike-race.en.uptodown.com/android/download/122951516>

Rename `.apk` to `.zip` and extract it.

### 3. Convert the assets

The game's files cannot be copied across as they are. The APK has no usable
`assets/` folder — its art lives in `res/drawable-nodpi` and
`res/drawable-xhdpi`, its audio in `res/raw` as MP3 and as WAVs at rates the
Vita mixer does not take, and three of the wheel sprites only exist as regions
inside a larger atlas. A script does the conversion:

```sh
git clone https://github.com/franciscoclou/bikerace-vita.git
cd bikerace-vita
mkdir apk && unzip /path/to/bike-race.zip -d apk
./scripts/extract_assets.sh
```

This needs `ffmpeg`, `imagemagick` and `python3` with Pillow. It writes
`assets_out/` — about 24 MB.

### 4. Copy the assets to the Vita

Copy the **contents** of `assets_out/` into `ux0:data/bikerace/` with
VitaShell, so you end up with:

```
ux0:data/bikerace/textures/
ux0:data/bikerace/ui/
ux0:data/bikerace/sfx/
ux0:data/bikerace/music/      <- optional, 8.8 MB menu track
```

The menu music is the largest single file and the game runs without it.

Launch it from the LiveArea. Progress is saved to
`ux0:data/bikerace/save.bin`, and ghost replays to `ghosts.bin` beside it.

---

## Building

The toolchain runs in Docker, so nothing needs installing beyond it:

```sh
./scripts/setup.sh      # jadx, vita-parse-core, the vitasdk image
./scripts/decompile.sh  # jadx over apk/classes.dex
./scripts/dumplevels.sh # -> data/levels.bin   (19 worlds, 152 levels)
./scripts/makefonts.sh  # -> data/font_*.bin
./scripts/build.sh      # -> build/BikeRace.vpk
```

The first four steps need `apk/` in place — see step 2 above.

`make -C tests run` compiles the game's own sources natively, with GL and the
logger stubbed, and runs the real physics over all 152 levels. It runs twice:
once as a handheld, once as a PlayStation TV.

---

## Credits

- **[VitaSDK](https://vitasdk.org/)** — the open toolchain the whole thing is
  built with.
- **[Rinnegatamante](https://github.com/Rinnegatamante)** for
  **[vitaGL](https://github.com/Rinnegatamante/vitaGL)**, the OpenGL
  implementation over GXM that this port renders through. The fixed-function
  path maps almost directly onto what the original's engine expected.
- **[TheOfficialFloW](https://github.com/TheOfficialFloW)** and Rinnegatamante
  more broadly, whose Android-to-Vita ports established the conventions this
  one follows for packaging, asset layout and debugging — even though the
  `.so` loader method they are best known for does not apply here, the
  original having no native library to load.
- **Top Free Games** for the original game.

## Licence

The C source in this repository is released under the MIT Licence. This covers
the port only. It does not extend to *Bike Race* or to any asset extracted
from it.
