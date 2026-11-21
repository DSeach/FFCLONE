# Map input validation

`parseMap` now rejects an empty map, zero-width rows, and uneven row lengths with `IllegalArgumentException` before creating a `GameMap`. Both direct callers and asset loading use this validation.

Asset loading continues to ignore blank lines, including a trailing newline. Unknown tile characters retain their existing `IllegalStateException` behavior.

## Verification

`MapValidationTests` covers empty input, zero-width input, shorter rows, longer rows, and an empty interior row. Existing parser tests cover valid tiles and unknown characters.

Run `./gradlew testDebugUnitTest`.
