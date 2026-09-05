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
- [x] LiveArea icon/bg from the original app icon

## Phase 1 — it boots and draws  ⬅ you are here

- [x] `main.c` loop: vitaGL init, timing, input poll, swap
- [x] `Vector2D` → [src/engine/vec2.h](../src/engine/vec2.h)
- [x] `Body` / `ComposedBody` / `SpringShockAbsorber` → [src/engine/body.c](../src/engine/body.c)
- [ ] **Verify on hardware**: does the VPK install, boot, and log?
- [ ] Texture loading: PNG → GXM texture, atlas page + UV rect (`engine.b.h`)
- [ ] `IVideoDriver` implementation on vitaGL fixed-function (`engine.b.f`)

## Phase 2 — a level you can look at

- [ ] `Board` (one segment) and `LevelBoards`
- [ ] `LevelBoardsBuilder` — `deltas`, `arc`, `fillet` (`bikerace.h.c`)
- [ ] Transcribe `LevelFactoryWorld1` (8 levels) — by hand first, to pin the
      semantics, then script the remaining 18 worlds
- [ ] `LevelMeshLoader`: polyline → textured ribbon (`bikerace.h.w`)
- [ ] `Camera` (`bikerace.e`)
- [ ] Scene graph: `BaseSceneNode`, `SpriteSceneNode`, `MeshSceneNode`,
      `CyclicSpriteSceneNode` (parallax)

## Phase 3 — it plays

- [ ] `Bike` (`bikerace.a`, 794 lines) — bodies, springs, wheel/track collision
- [ ] `Game` (`bikerace.f`, 914 lines) — step order, timing, win/lose, restart
- [ ] Controls: R/L triggers and touch halves → accelerate/brake, stick → lean
- [ ] `GameSceneDirector` (`bikerace.ac`) — scene assembly per level
- [ ] Star times and level completion

## Phase 4 — the game around the game

- [ ] `GameAudio` (`bikerace.v`) — sceAudio, effects + engine-note mixing
- [ ] Save data in `ux0:data/bikerace/` (replaces `SharedPreferences`)
- [ ] World / level selection UI
- [ ] `GameUI` HUD (`bikerace.af`)
- [ ] Bike selection — all bikes unlocked, no shop, no currency
- [ ] Remaining 18 worlds

## Not being ported

Ads, analytics, Facebook, AWS/DynamoDB, MQTT multiplayer, in-app billing, push
notifications, downloadable user levels, the World Cup gacha shop. Anywhere the
original gates content behind these, the port unlocks it.

## Open questions

- **Non-power-of-two UI art.** 466 of 524 extracted textures are NPOT. Pad at
  build time, or build a real atlas packer? Decide before Phase 4.
- **Accelerometer tilt.** The original had a tilt control scheme. `sceMotion`
  could reproduce it on the Vita, but the stick is probably better. Ship the
  stick; revisit if it feels wrong.
- **Frame pacing.** The Android build was variable-timestep (`dt` straight into
  the integrator). At a locked 60 Hz the physics will not match Android
  exactly. If star times turn out unreachable, a fixed 60 Hz timestep with an
  accumulator is the fix.
