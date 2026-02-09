package com.game.Overworld

import com.example.ffclone.game.*
import com.example.ffclone.game.overworld.GameMap
import com.example.ffclone.game.overworld.TileType
import com.example.ffclone.game.overworld.checkEnemyCollision
import com.example.ffclone.game.overworld.movePosition
import com.example.ffclone.game.overworld.updateOverworldStateAfterMove
import org.junit.Assert.*
import org.junit.Test

class OverworldUtilsTest {

    @Test
    fun movePosition_withinBounds() {
        val pos = GridPosition(1, 1)
        val newPos = movePosition(5, 5, pos, 2, 3)
        assertEquals(GridPosition(3, 4), newPos)
    }

    @Test
    fun movePosition_exceedBounds() {
        val pos = GridPosition(4, 4)
        val newPos = movePosition(5, 5, pos, 3, 3)
        assertEquals(GridPosition(4, 4), newPos) // clamped to max
    }

    @Test
    fun checkEnemyCollision_hitsEnemy() {
        val playerPos = GridPosition(2, 2)
        val enemy = Enemy("e1", "Goblin", 10, 10, 5, 2, GridPosition(2, 2))
        val collided = checkEnemyCollision(playerPos, listOf(enemy))
        assertNotNull(collided)
        assertEquals("e1", collided?.id)
    }

    @Test
    fun checkEnemyCollision_noEnemy() {
        val playerPos = GridPosition(0, 0)
        val enemy = Enemy("e1", "Goblin", 10, 10, 5, 2, GridPosition(2, 2))
        val collided = checkEnemyCollision(playerPos, listOf(enemy))
        assertNull(collided)
    }

    @Test
    fun updateOverworldStateAfterMove_noCollision() {
        val map = GameMap(5, 5, List(5) { List(5) { TileType.EMPTY } })
        val player = Player("p1", "Hero", 100, 100, 10, 10, GridPosition(0,0))
        val state = GameScreenState.OVERWORLD(map, player, emptyList())

        val newPlayer = player.copy(position = GridPosition(1,1))
        val newState = updateOverworldStateAfterMove(state, newPlayer)
        assertTrue(newState is GameScreenState.OVERWORLD)
        assertEquals(GridPosition(1,1), (newState as GameScreenState.OVERWORLD).player.position)
    }

    @Test
    fun updateOverworldStateAfterMove_withCollision() {
        val map = GameMap(5,5, List(5) { List(5) { TileType.EMPTY } })
        val player = Player("p1", "Hero", 100, 100, 10, 10, GridPosition(0,0))
        val enemy = Enemy("e1", "Goblin", 10, 10, 5, 2, GridPosition(1,1))
        val state = GameScreenState.OVERWORLD(map, player, listOf(enemy))

        val newPlayer = player.copy(position = GridPosition(1,1))
        val newState = updateOverworldStateAfterMove(state, newPlayer)
        assertTrue(newState is GameScreenState.BATTLE)
        val battle = newState as GameScreenState.BATTLE
        assertEquals("e1", battle.enemies.first().id)
    }
}