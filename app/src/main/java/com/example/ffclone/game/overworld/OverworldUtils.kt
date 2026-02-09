package com.example.ffclone.game.overworld

import com.example.ffclone.game.Enemy
import com.example.ffclone.game.GameScreenState
import com.example.ffclone.game.GridPosition
import com.example.ffclone.game.Player

fun movePosition(mapWidth: Int, mapHeight: Int, current: GridPosition, dx: Int, dy: Int): GridPosition {
    return GridPosition(
        x = (current.x + dx).coerceIn(0, mapWidth - 1),
        y = (current.y + dy).coerceIn(0, mapHeight - 1)
    )
}

fun checkEnemyCollision(playerPos: GridPosition, enemies: List<Enemy>): Enemy? {
    return enemies.firstOrNull { it.position == playerPos }
}

fun updateOverworldStateAfterMove(
    current: GameScreenState.OVERWORLD,
    newPlayer: Player
): GameScreenState {
    val collidedEnemy = checkEnemyCollision(newPlayer.position, current.enemies)
    return if (collidedEnemy != null) {
        GameScreenState.BATTLE(player = newPlayer, enemies = listOf(collidedEnemy), map = current.map)
    } else {
        current.copy(player = newPlayer)
    }
}