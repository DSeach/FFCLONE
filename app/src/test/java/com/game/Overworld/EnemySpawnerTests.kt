package com.game.Overworld

import com.example.ffclone.game.GridPosition
import com.example.ffclone.game.overworld.EnemySpawner
import com.example.ffclone.game.overworld.parseMap
import org.junit.Assert.*
import org.junit.Test

class EnemySpawnerTests {
    @Test
    fun spawnsAtMostThreeDistinctEnemiesOnDangerTilesExcludingPlayer() {
        val map = parseMap(listOf(".WDDD", "DD..."))
        val enemies = EnemySpawner().spawn(map, GridPosition(2, 0))
        assertEquals(listOf(GridPosition(3, 0), GridPosition(4, 0), GridPosition(0, 1)), enemies.map { it.position })
        assertEquals(3, enemies.map { it.id }.distinct().size)
        assertTrue(enemies.all { it.currentHp == it.maxHp && it.currentHp > 0 })
    }

    @Test
    fun safeMapHasNoEnemies() {
        assertTrue(EnemySpawner().spawn(parseMap(listOf(".W.")), GridPosition(0, 0)).isEmpty())
    }

    @Test
    fun loadingSameMapProducesSameEnemies() {
        val map = parseMap(listOf(".DD"))
        assertEquals(EnemySpawner().spawn(map, GridPosition(0, 0)), EnemySpawner().spawn(map, GridPosition(0, 0)))
    }
}
