package com.game

import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.size
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.ffclone.game.GameScreenState
import com.example.ffclone.game.GridPosition
import com.example.ffclone.game.Player
import com.example.ffclone.game.overworld.GameMap
import com.example.ffclone.game.overworld.TileType
import com.example.ffclone.ui.OverworldScreen
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.math.floor

@RunWith(AndroidJUnit4::class)
class OverworldLayoutTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun shortViewportKeepsEveryRowVisible() {
        checkMapFits(15, 10, 240.dp, 100.dp, 10.dp)
    }

    @Test
    fun narrowViewportUsesActualMapWidth() {
        checkMapFits(6, 4, 120.dp, 240.dp, 20.dp)
    }

    @Test
    fun mapWiderThanDefaultGridKeepsLastColumnVisible() {
        checkMapFits(20, 2, 200.dp, 100.dp, 10.dp)
    }

    @Test
    fun fractionalColumnPixelsRoundDownToKeepEveryColumnVisible() {
        with(composeTestRule.density) {
            checkMapFits(15, 2, 101.toDp(), 100.toDp(), 6.toDp())
        }
    }

    @Test
    fun fractionalRowPixelsRoundDownToKeepEveryRowVisible() {
        with(composeTestRule.density) {
            checkMapFits(2, 10, 100.toDp(), 107.toDp(), 10.toDp())
        }
    }

    private fun checkMapFits(
        columns: Int,
        rows: Int,
        width: Dp,
        height: Dp,
        cellSize: Dp
    ) {
        val map = GameMap(columns, rows, List(rows) { List(columns) { TileType.EMPTY } })
        val state = GameScreenState.OVERWORLD(map, Player(position = GridPosition(0, 0)))
        composeTestRule.setContent {
            OverworldScreen(state, Modifier.size(width, height).testTag("viewport"))
        }
        val viewport = composeTestRule.onNodeWithTag("viewport").fetchSemanticsNode().boundsInRoot
        val expectedCellSize = with(composeTestRule.density) { floor(cellSize.toPx() + 0.0001f).toDp() }
        for (y in 0 until rows) {
            for (x in 0 until columns) {
                val tile = composeTestRule.onNodeWithTag("tile-$x-$y", useUnmergedTree = true)
                tile.assertWidthIsEqualTo(expectedCellSize).assertHeightIsEqualTo(expectedCellSize)
                val bounds = tile.fetchSemanticsNode().boundsInRoot
                assertTrue("Tile ($x,$y) must fit horizontally", bounds.left >= viewport.left && bounds.right <= viewport.right)
                assertTrue("Tile ($x,$y) must fit vertically", bounds.top >= viewport.top && bounds.bottom <= viewport.bottom)
            }
        }
    }
}
