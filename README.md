# PlayerAnimator Compatibility Bridge (NeoForge 1.21.1)

Bridges old `playeranimator` mod id / legacy `dev.kosmx.playerAnim` API to the new
**Player Animation Library** (`player_animation_library`, `com.zigythebird.playeranim*`).

## Why
Latest logs show:
- Better Combat 2.4.0 requires `playeranimator [1,)`
- fape_compat requires `playeranimator [0.9.0,)`
- The installed library is `Player Animation Library 1.1.6`, whose mod id is
  `player_animation_library`, so NeoForge fails pre-loading with
  `playeranimator is not installed`.

This mod provides:
- A mod with mod id `playeranimator`
- Legacy `dev.kosmx.playerAnim.*` API classes used by Better Combat / fape_compat
- A client mixin that makes `AbstractClientPlayer` implement the legacy
  `IAnimatedPlayer`, backed by the new library's `PlayerAnimManager`
- A `PlayerAnimationRegistry` bridge to the new library's animation resources
- A `KeyframeAnimationPlayer` that drives the new `PlayerAnimationController`
  so Better Combat animations can still play through the new library
- A `SpeedModifier` bridge that applies the legacy `speed` field to the new
  library's `AnimationData` timeline. This keeps Better Combat's swing tick and
  animation tick in sync (fixes attacks feeling half a beat late).

## Install
- Put `playeranimator-compat-1.0.1.jar` into `mods/`
- Keep `PlayerAnimationLibNeoforge-1.1.6+mc.1.21.1.jar` installed
- Remove/do not install the old `playeranimator` mod (it would conflict by mod id)
