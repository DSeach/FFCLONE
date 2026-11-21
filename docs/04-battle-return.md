# Preserving enemies after battle

Entering a battle now carries the remaining overworld enemies in the battle state. Exiting restores those enemies, the same map, and the player's latest battle state. Other enemies retain their positions and health.

The current placeholder battle screen treats a tap as completing the encounter. That action removes the encountered enemy. Combat outcomes such as fleeing are outside this change.

`remainingOverworldEnemies` defaults to an empty list so existing callers that construct a standalone battle keep working. `GameViewModel.exitBattle` calls the pure `returnToOverworld` function.

## Verification

`BattleReturnTests` covers two consecutive encounters, remaining enemy state, player health preservation, and standalone battles using the default enemy list. Run `./gradlew testDebugUnitTest`.
