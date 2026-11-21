package com.game.Overworld

import com.example.ffclone.game.overworld.parseMap
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class MapValidationTests {
    @Test
    fun emptyMapIsRejectedBeforeConstructingGameMap() {
        val error = assertThrows(IllegalArgumentException::class.java) {
            parseMap(emptyList())
        }
        assertEquals("Map must contain at least one row", error.message)
    }

    @Test
    fun zeroWidthMapIsRejected() {
        assertThrows(IllegalArgumentException::class.java) {
            parseMap(listOf("", ""))
        }
    }

    @Test
    fun shorterRowIsRejected() {
        assertThrows(IllegalArgumentException::class.java) {
            parseMap(listOf("...", ".."))
        }
    }

    @Test
    fun longerRowIsRejected() {
        assertThrows(IllegalArgumentException::class.java) {
            parseMap(listOf("..", "..."))
        }
    }

    @Test
    fun emptyInteriorRowIsRejected() {
        assertThrows(IllegalArgumentException::class.java) {
            parseMap(listOf("..", "", ".."))
        }
    }
}
