# Review fixes

Six branches implement the findings from the review of `a38d5a8`. Each branch builds on the preceding branch so overlapping changes can be reviewed separately. Review and merge in the order below, retargeting the next PR to `master` after its predecessor merges. If squash merging, rebase the remaining branches before retargeting to remove already-reviewed commits from their diffs.

| Task | Branch | Initial PR base | Change documentation |
| --- | --- | --- | --- |
| 1 | `codex/01-movement-tests` | `master` | [Movement tests and CI](01-movement-tests.md) |
| 2 | `codex/02-encounters` | `codex/01-movement-tests` | [Reachable encounters](02-encounters.md) |
| 3 | `codex/03-wall-collision` | `codex/02-encounters` | [Wall collision](03-wall-collision.md) |
| 4 | `codex/04-battle-return` | `codex/03-wall-collision` | [Battle return](04-battle-return.md) |
| 5 | `codex/05-map-layout` | `codex/04-battle-return` | [Map layout](05-map-layout.md) |
| 6 | `codex/06-map-validation` | `codex/05-map-layout` | [Map validation](06-map-validation.md) |

## Validation

Combined changes were checked on 21 November 2026 with Android Studio's JDK 21 and Android SDK 36.

```powershell
.\gradlew.bat testDebugUnitTest assembleDebug assembleDebugAndroidTest lintDebug
```

Result: 22 JVM tests passed, app and instrumentation APKs built, and lint completed with zero errors and 18 warnings. The instrumentation suite contains movement, encounter flow, and map layout tests.

Local instrumentation execution was attempted. The installed `Medium_Phone_API_36.1` emulator could not boot because the Android Emulator hypervisor driver is missing. Consequently, device tests have compiled but have not executed locally. The new GitHub Actions device job runs the suite on an API 35 emulator with KVM support and uploads reports. Its results must be checked before merging.

## Behavior decisions

The prototype spawns at most three visible goblins on danger tiles in row order, excluding the starting position. The existing tap-to-exit battle screen completes an encounter and removes its enemy. Other enemies persist. These choices make the existing prototype flow playable without adding combat rules.

## Publication status

All six changes are committed locally. Publication was attempted, but Git rejected the saved credentials and the GitHub connector returned HTTP 403 for creating a branch. Pull requests and cloud device tests await repository write access, including permission to update GitHub Actions workflows.
