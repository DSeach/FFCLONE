package com.game.Overworld

import com.example.ffclone.game.Enemy
import com.example.ffclone.game.GameScreenState
import com.example.ffclone.game.GridPosition
import com.example.ffclone.game.Player
import com.example.ffclone.game.overworld.GameMap
import com.example.ffclone.game.overworld.TileType
import com.example.ffclone.game.overworld.returnToOverworld
import com.example.ffclone.game.overworld.updateOverworldStateAfterMove
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class BattleReturnTests {
    private val map = GameMap(3, 1, listOf(List(3) { TileType.EMPTY }))
    private val player = Player(position = GridPosition(0, 0))
    private val firstEnemy = Enemy("first", "Goblin", 10, 10, 5, 2, GridPosition(1, 0))
    private val secondEnemy = Enemy("second", "Goblin", 10, 7, 5, 2, GridPosition(2, 0))

    @Test
    fun consecutiveBattlesRemoveOnlyTheEncounteredEnemy() {
        val overworld = GameScreenState.OVERWORLD(map, player, listOf(firstEnemy, secondEnemy))
        val firstBattle = updateOverworldStateAfterMove(
            overworld, player.copy(position = firstEnemy.position)
        ) as GameScreenState.BATTLE

        assertEquals(listOf(firstEnemy), firstBattle.enemies)
        val afterFirstBattle = returnToOverworld(firstBattle)
        assertEquals(listOf(secondEnemy), afterFirstBattle.enemies)
        assertSame(map, afterFirstBattle.map)
        assertEquals(firstEnemy.position, afterFirstBattle.player.position)

        val secondBattle = updateOverworldStateAfterMove(
            afterFirstBattle, afterFirstBattle.player.copy(position = secondEnemy.position)
        ) as GameScreenState.BATTLE
        assertEquals(listOf(secondEnemy), secondBattle.enemies)
        assertEquals(emptyList<Enemy>(), returnToOverworld(secondBattle).enemies)
    }

    @Test
    fun returnPreservesPlayerChangesMadeDuringBattle() {
        val injuredPlayer = player.copy(currentHp = 25)
        val battle = GameScreenState.BATTLE(
            injuredPlayer, listOf(firstEnemy), map = map,
            remainingOverworldEnemies = listOf(secondEnemy)
        )

        assertEquals(injuredPlayer, returnToOverworld(battle).player)
    }

    @Test
    fun battleCreatedWithoutOverworldEnemiesStillReturns() {
        val battle = GameScreenState.BATTLE(player, listOf(firstEnemy), map = map)

        assertEquals(GameScreenState.OVERWORLD(map, player), returnToOverworld(battle))
    }
}
