package com.game.Overworld

import com.example.ffclone.game.overworld.TileType
import com.example.ffclone.game.overworld.parseMap
import org.junit.Assert.*
import org.junit.Test

class MapParserTests {

    @Test
    fun parseMap_basic() {
        val bitmap = listOf(
            "...",
            ".D.",
            "W.."
        )

        val gameMap = parseMap(bitmap)
        assertEquals(3, gameMap.width)
        assertEquals(3, gameMap.height)

        assertEquals(TileType.EMPTY, gameMap.tileAt(0,0))
        assertEquals(TileType.DANGER, gameMap.tileAt(1,1))
        assertEquals(TileType.WALL, gameMap.tileAt(0,2))
    }

    @Test(expected = IllegalStateException::class)
    fun parseMap_unknownChar_throws() {
        val bitmap = listOf(
            "X.."
        )
        parseMap(bitmap)
    }

    @Test
    fun parseMap_allSameLength_passes() {
        val bitmap = listOf(
            "...",
            "DDD",
            "WWW"
        )
        val gameMap = parseMap(bitmap)
        assertEquals(3, gameMap.width)
        assertEquals(3, gameMap.height)
    }
}
