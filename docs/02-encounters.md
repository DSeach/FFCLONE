# Reachable encounters

Loading a map now creates up to three visible goblins on the first available danger tiles in row order. Spawns exclude the player's starting tile and use stable coordinate-based IDs. Maps without available danger tiles have no enemies.

This intentionally small deterministic population makes the existing collision-triggered battle screen reachable. Walking onto a red enemy enters battle. Tapping the battle screen resolves the prototype encounter. Full combat mechanics remain future work.

The initial map places enemies at (12, 2), (13, 2), and (14, 2). From the initial position, move right twelve times and down twice to enter the first encounter.

EnemySpawnerTests verifies placement, population limit, unique IDs, initial health, safe maps, and repeatable spawning. Device coverage exercises the normal movement-to-battle path.
