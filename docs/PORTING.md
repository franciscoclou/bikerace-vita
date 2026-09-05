# Porting status and plan

Status of each piece of the port. Update this as things land.

Legend: **done** · **wip** · **todo**

## Phase 0 — environment  ✅ done

- [x] Repo, `apk/` baseline, `.gitignore`
- [x] Dockerised vitasdk (host toolchain needs glibc 2.38, we have 2.35)
- [x] CMake + vitaGL project producing `BikeRace.vpk`
- [x] Live UDP debug log → `debug.log` (`scripts/log.sh`)
- [x] Crash dump fetch + symbolication (`scripts/getdump.sh`, `parsedump.sh`)
- [x] jadx decompile + obfuscation map (`scripts/decompile.sh`, `find.sh`)
- [x] Asset extraction (`scripts/extract_assets.sh`)
- [x] LiveArea icon/bg from the original app icon (8-bit palette PNG —
      truecolour makes the install fail with `0x8010113D`)

## Phase 1 — it boots and draws  ✅ done

- [x] `main.c` loop: vitaGL init, timing, input poll, swap
- [x] `Vector2D` → [src/engine/vec2.h](../src/engine/vec2.h)
- [x] `Body` / `ComposedBody` / `SpringShockAbsorber` → [src/engine/body.c](../src/engine/body.c)
- [x] `Utils` segment maths → [src/engine/geom.c](../src/engine/geom.c)
- [x] Texture loading: PNG → GL texture, atlas page + UV rect (`engine.b.h`)
- [x] `IVideoDriver` on vitaGL fixed function (`engine.b.f`)

## Phase 2 — a level you can look at  ✅ done

- [x] `Board` and `LevelBoards`, including the x-slab broad phase
- [x] All 19 worlds' geometry, by running the original factories — see
      `scripts/dumplevels.sh`. `LevelBoardsBuilder` itself is never ported.
- [x] `LevelMeshLoader`: polyline → textured ribbon
- [x] `Camera`
- [x] Scene transforms: root, camera, sprite and the cyclic parallax layers

## Phase 3 — it plays  ⬅ you are here

- [x] `Bike` — bodies, springs, wheel/track collision, crash test
- [x] `Game` — substep order, timing, win/lose, restart
- [x] Controls: R/L triggers and touch halves → accelerate/brake, stick → lean
- [x] Scene assembly per level: backgrounds, track, finish pole and flag, bike
- [x] Star times and level completion
- [ ] **Play it on hardware and tune the feel**
- [ ] Bike sprite offsets are in place for six bikes; the rest of the shop
      bikes need their rows from `GameSceneDirector`'s tables

## Phase 4 — the game around the game  ✅ done

- [x] Save data in `ux0:data/bikerace/save.bin` (replaces `SharedPreferences`)
- [x] World and level selection, with track previews drawn from the real
      geometry — something the Android version had nothing to draw from
- [x] Text, from the two faces the APK ships, baked by `scripts/makefonts.sh`
- [x] A race overlay: clock, level name, star targets, finish and crash states
- [x] Touch in the menus, alongside the d-pad
- [x] A pause screen: resume, restart, level list
- [x] `GameAudio` — engine notes, landings, crash, win, Halloween, menu music
- [x] Bike selection — all 21 single-player bikes, no shop, no currency

Nothing is gated: every world, level and bike can be picked from the start, and
stars are recorded and shown rather than spent.

### The audio mapping, as ported

Engine notes are five samples crossfaded by a state machine (stopped → slow →
accelerating → fast, and hi→medium on the way back down), handing over when the
current sample is 95% through:

| Sound | File |
|---|---|
| engine low / medium / medium-hi / hi / hi-medium | `rot_baixa`, `rot_media`, `rot_media_alta`, `rot_alta`, `rot_alta_media` |
| explosion, fall impact, win | `explosion`, `queda`, `win` |
| Halloween ambience | `sino_1`, `corvo_1`, `grito_homem_1`, `evil_laugh_bruxa_3` |
| menu music | `musica_menu` |

Fall impact only fires when the contact force is at least 300 and the bike was
airborne, rate-limited to one per sample length.

## Where the numbers came from

Every per-bike figure in [src/game/bike.c](../src/game/bike.c) is the
original's: handling from `Bike.java`'s factory methods, gravity from `Game`'s
table, sprite width and offset from `GameSceneDirector`, the atlas region from
`TextureCoordinates`, and the names from the APK's own string table by way of
`BikeUnlockDialog`'s bike-ordinal switch. That last one is how "Spam" turned
out to be the High Tech Bike.

Reading names out of `resources.arsc` needs the locale field at the right
offset — it sits after the 4-byte size and the 4-byte IMSI block. Get it wrong
and the parser happily returns a translated table instead of the default one.

## What the menus are, and are not

`GameUI` in the original is a message bridge to Android Activities, and the
menus are XML layouts, so there was nothing to translate — only the artwork
carried over. The screens here are new: a world grid and a level grid, drawn
with the game's own buttons, stars, background and logo, navigated with the
d-pad or stick.

World names come from the APK's own string table, cross-checked against the
achievement identifiers.

## Not being ported

Ads, analytics, Facebook, AWS/DynamoDB, MQTT multiplayer, in-app billing, push
notifications, downloadable user levels, the World Cup gacha shop. Anywhere the
original gates content behind these, the port unlocks it.

## Verifying without a Vita

`make -C tests run` compiles the game's own sources on the host with GL and
the logger stubbed, then exercises the physics, level data, camera and broad
phase. It checks that 1-1 can actually be completed and that all 152 levels
simulate without diverging.

`make -C tests shots` goes further: the GL stub has a small software
rasteriser, so it renders real frames to `tests/shots/*.png`. That is how the
scene transforms, atlas regions and draw order were checked before anything
was flashed.

Neither replaces running it on hardware — they say nothing about GXM,
performance or memory — but they catch the class of bug that is miserable to
diagnose from a UDP log.

## Open questions

- **Non-power-of-two UI art.** 466 of 524 extracted textures are NPOT. Pad at
  build time, or build a real atlas packer? Decide before Phase 4.
- **Accelerometer tilt.** The original had a tilt control scheme. `sceMotion`
  could reproduce it on the Vita, but the stick is probably better. Ship the
  stick; revisit if it feels wrong.
- **Frame pacing.** Physics substeps at a fixed 3 ms as the original did, but
  the number of substeps still follows the frame time. At a locked 60 Hz this
  matches Android closely; 1-1 completes in 7.85 s against a 10 s three-star
  threshold, which is about right.
- **The broad phase bins each board by the x of its first endpoint only**, so a
  long board is indexed only where it starts. That is a quirk of the original
  and is reproduced deliberately. If collisions ever go missing on a long flat
  run, this is the first place to look.
- **Star times are not always descending** in the imported worlds (11, 15, 16),
  which makes the middle tier unreachable there. That is the shipped data.
