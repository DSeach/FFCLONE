package com.example.ffclone.game.overworld

import com.example.ffclone.game.Enemy
import com.example.ffclone.game.GridPosition

class EnemySpawner {
    fun spawn(map: GameMap, playerStart: GridPosition): List<Enemy> {
        val positions = (0 until map.height).flatMap { y ->
            (0 until map.width).map { x -> GridPosition(x, y) }
        }.filter { position ->
            position != playerStart && map.tileAt(position.x, position.y) == TileType.DANGER
        }.take(3)

        return positions.map { position ->
            Enemy(
                id = "goblin-${position.x}-${position.y}",
                name = "Goblin",
                maxHp = 10,
                currentHp = 10,
                attack = 5,
                defense = 2,
                position = position
            )
        }
    }
}
