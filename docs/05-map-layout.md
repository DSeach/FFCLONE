# Adaptive overworld layout

The overworld sizes square tiles using the smaller of the available width per map column and available height per map row. This keeps the complete map inside its allocated area when the screen is short or the map differs from the original 15-column grid.

The map remains centered in the available area. Tile test tags expose individual bounds to Compose instrumentation tests.

Cell dimensions are rounded down to whole physical pixels before conversion to density-independent units. This prevents repeated per-tile rounding from making the grid exceed the available width or height.

## Verification

`OverworldLayoutTest` covers a short viewport, a narrow viewport with a six-column map, and a map wider than the original grid. Each case checks every tile's square dimensions and containment in the viewport.

Two additional cases use viewport dimensions that divide into fractional pixel sizes. They verify the last row and column remain visible and every cell retains the same square size.

Run `./gradlew connectedDebugAndroidTest` with an Android device or emulator connected.
