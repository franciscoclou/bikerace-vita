# The original game, and what porting it means

Everything below was read out of `decompiled/`. Class names are the
post-ProGuard ones; `docs/class_map.tsv` maps them back to original filenames
and `./scripts/find.sh <Name>` looks them up.

## Shape of the problem

`apk/` contains **no native code** — no `lib/`, no `.so`. The game is Java on
top of a `GLSurfaceView`, with its own engine. So there is nothing to wrap or
emulate: the port is a rewrite in C.

That is less daunting than it sounds, because the engine is tiny and the game
core is about 3,500 lines of Java:

| Class | Obfuscated | Lines |
|---|---|---|
| `Game.java` | `bikerace.f` | 914 |
| `GameSceneDirector.java` | `bikerace.ac` | 824 |
| `Bike.java` | `bikerace.a` | 794 |
| `GraphicsResources.java` | `bikerace.ah` | 455 |
| `OpenGLES10VideoDriver.java` | `engine.b.f` | 217 |
| whole `engine.d.*` scene graph | | ~500 |
| whole `engine.c.*` physics | | ~200 |

The rest of `classes.dex` — roughly 90% of it — is Facebook, AWS, Flurry,
Chartboost, AppLovin, Heyzap, Google Analytics, MQTT, Jackson and billing.
**None of it gets ported.** No ads, no analytics, no multiplayer backend, no
IAP; every bike and world is simply unlocked.

## `com.topfreegames.engine` — the engine

### `engine.a` — maths

| Obf | Original |
|---|---|
| `a.a` | `Utils.java` |
| `a.b` | `Vector2D.java` |
| `a.c` | `Vector3D.java` |

`Vector2D` is a mutable, chainable 2-vector. Ported verbatim to
[src/engine/vec2.h](../src/engine/vec2.h) — including its quirk of returning
exactly `0.0f` from `length()` for the zero vector instead of going through
`sqrtf`.

### `engine.c` — physics

| Obf | Original | Ported to |
|---|---|---|
| `c.a` | `Body.java` | `br_body` |
| `c.b` | `ComposedBody.java` | `br_composed_body` |
| `c.c` | `SpringShockAbsorber.java` | `br_spring` |

This is **not** a rigid-body solver — there is no Box2D here, and no contact
solver, no constraint islands, no broadphase.

- A `Body` is a **point mass**: position, velocity, mass, plus an `angle` in
  *degrees* and an angular velocity that exist only so sprites can be drawn
  rotated. Angle plays no part in the dynamics.
- A `ComposedBody` is a bag of point masses. It derives a centre of mass,
  a centre-of-mass velocity, a moment of inertia (`Σ m·|offset|²`) and an
  angular momentum, and can spin the whole assembly by adding a tangential
  velocity to each member.
- A `SpringShockAbsorber` is a damped spring between two `Body`s: Hooke along
  the separation vector, damping along the same axis only.

A bike is: two wheel bodies + a frame body, wired together with springs.
Collision against the track is resolved directly in `Bike.java` against the
track's line segments, not by any general mechanism.

**The feel of this game is the emergent behaviour of that specific, slightly
odd set of equations.** Port it exactly. Substituting a "proper" physics
engine, or tidying up the maths, changes the game.

### `engine.b` — rendering

| Obf | Original |
|---|---|
| `b.a` | `BitmapLoader.java` |
| `b.b` | `Buffer.java` |
| `b.c` | `Color.java` |
| `b.d` | `IVideoDriver.java` |
| `b.e` | `Matrix.java` |
| `b.f`, `b.g` | `OpenGLES10VideoDriver.java` |
| `b.h` | `Texture.java` |
| `b.i` | `Vertex2D.java` |

`IVideoDriver` is a ~20-method interface over **OpenGL ES 1.0 fixed function**:
upload a bitmap, draw a textured rect, draw a mesh buffer, push/pop a matrix,
set colour, set a scissor rect, swap.

This is the single luckiest fact about this port. vitaGL implements exactly
this API. The renderer becomes a thin `IVideoDriver` implementation in
`src/platform/` and the scene graph above it ports unchanged. **Do not write
GXM shaders** — the fixed-function path is both faithful and less work.

A `Texture` is an atlas page id plus a `RectF` of UVs into it, which is why the
game ships six 2048×2048 atlases rather than individual sprites.

### `engine.d` — scene graph

| Obf | Original |
|---|---|
| `d.e` | `ISceneNode.java` |
| `d.b` | `BaseSceneNode.java` |
| `d.j` | `VoidSceneNode.java` |
| `d.i` | `SpriteSceneNode.java` |
| `d.a` | `AnimatedSpriteSceneNode.java` |
| `d.d` | `CyclicSpriteSceneNode.java` |
| `d.c` | `CameraSceneNode.java` |
| `d.f` | `Mesh.java` |
| `d.g` | `MeshBuffer.java` |
| `d.h` | `MeshSceneNode.java` |

A conventional parent/child transform hierarchy. Nodes hold a position, a
rotation origin, a scale and a texture; `update()` walks down, `render()`
issues draws through the `IVideoDriver`. `CyclicSpriteSceneNode` is the
scrolling parallax background; `MeshSceneNode` draws the track ribbon.

## `com.topfreegames.bikerace` — the game

| Obf | Original | What it is |
|---|---|---|
| `bikerace.f` | `Game.java` | simulation: step, collision, timing, win/lose |
| `bikerace.a` | `Bike.java` | the bike — bodies, springs, wheels, rider |
| `bikerace.d` | `Board.java` | **one line segment** of track: two points |
| `bikerace.e` | `Camera.java` | follows the bike |
| `bikerace.ac` | `GameSceneDirector.java` | builds the scene graph for a level |
| `bikerace.af` | `GameUI.java` | HUD |
| `bikerace.ah` | `GraphicsResources.java` | atlas pages and the UV table |
| `bikerace.v` | `GameAudio.java` | sound effects and engine-note mixing |
| `bikerace.bj-bl` | `TextureCoordinates.java` | the atlas UV table |

### Level format

Tracks are **not data files** — they are code, in
`LevelFactoryWorld1..19.java` (`bikerace.h.d` … `bikerace.h.v`). 19 worlds, 8+
levels each, built by calls into `LevelBoardsBuilder` (`bikerace.h.c`):

```java
c.a(bVar, new Vector2D[]{ ... })            // run of segments from relative deltas
c.a(bVar, 15, c.a(0,0, 1,0), 1.0f, 1.41f)   // arc: n segments, spec, radius, sweep
c.a(bVar, 3, 0.6f, 0.17f)                   // fillet the last n joints
return new a(bVar, new float[]{20, 14, 10}) // Level: board + 3-star times
```

So a level is a polyline of `Board` segments plus three star thresholds in
seconds, and `World` (`bikerace.h.x`) is a list of levels.

This is very good news: the level data can be lifted mechanically. Port
`LevelBoardsBuilder` to C, then transcribe each factory — or generate the C
from the Java with a script.

`LevelMeshLoader` (`bikerace.h.w`) turns a polyline into the textured ribbon
mesh that gets drawn.

## Assets

| Source | Contents |
|---|---|
| `apk/res/drawable-nodpi/bikerace_textura*.png` | six 2048×2048 + 1024×1024 gameplay atlases, plus seasonal variants |
| `apk/res/drawable-xhdpi/` | UI art, 467 files |
| `apk/res/raw/*.wav` | 17 effects, already 22050 Hz mono PCM |
| `apk/res/raw/*.mp3` | 2 menu tracks, 48 kHz stereo |
| `apk/assets/fonts/*.ttf` | 2 fonts |

The atlases are already power-of-two and under GXM's 4096 limit, so they go
across untouched. `./scripts/extract_assets.sh` builds `assets_out/`; the UI
art is largely non-power-of-two and will need padding or re-atlasing when the
UI is built.

Vita screen is 960×544 (16:9-ish); the Android original ran at whatever the
device gave it and scaled a fixed world width, so aspect handling is mostly
a matter of choosing the world-units-across constant.

## What is deliberately dropped

Ads, analytics (Flurry, Google Analytics, Crashlytics, GameAnalytics),
Facebook, AWS/DynamoDB, MQTT multiplayer, in-app billing, push notifications,
user-generated level downloads, the World Cup gacha shop. Where game logic
consults these — unlock checks, currency — the port unlocks everything.
