package com.game.Overworld

import com.example.ffclone.game.Enemy
import com.example.ffclone.game.GameScreenState
import com.example.ffclone.game.GridPosition
import com.example.ffclone.game.Player
import com.example.ffclone.game.overworld.GameMap
import com.example.ffclone.game.overworld.TileType
import com.example.ffclone.game.overworld.updateOverworldStateAfterMove
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class WallMovementTests {
    @Test
    fun wallBlocksMovementBeforeEnemyCollision() {
        val destination = GridPosition(1, 0)
        val enemy = Enemy("wall-enemy", "Goblin", 10, 10, 5, 2, destination)
        val player = Player(position = GridPosition(0, 0))
        val map = GameMap(2, 1, listOf(listOf(TileType.EMPTY, TileType.WALL)))
        val state = GameScreenState.OVERWORLD(map, player, listOf(enemy))

        val result = updateOverworldStateAfterMove(state, player.copy(position = destination))

        assertSame(state, result)
    }

    @Test
    fun emptyAndDangerTilesRemainWalkable() {
        for (tile in listOf(TileType.EMPTY, TileType.DANGER)) {
            val player = Player(position = GridPosition(0, 0))
            val map = GameMap(2, 1, listOf(listOf(TileType.EMPTY, tile)))
            val state = GameScreenState.OVERWORLD(map, player)
            val movedPlayer = player.copy(position = GridPosition(1, 0))

            val result = updateOverworldStateAfterMove(state, movedPlayer)

            assertEquals(state.copy(player = movedPlayer), result)
        }
    }
}
