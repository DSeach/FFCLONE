# Wall collision

Movement now rejects a destination marked `WALL` before checking for an enemy encounter. A blocked move preserves the current overworld state, including the player's position and every enemy. Empty and danger tiles remain walkable.

The check lives in `updateOverworldStateAfterMove`, which is used by `GameViewModel.movePlayer`. Existing map-boundary clamping remains in place.

## Verification

`WallMovementTests` checks that a wall blocks movement even when an enemy occupies that tile. It also checks movement onto empty and danger tiles.

Run `./gradlew testDebugUnitTest` to execute these regression tests.
