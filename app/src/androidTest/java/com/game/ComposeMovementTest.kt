package com.game

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.ffclone.game.GameScreenState
import com.example.ffclone.game.GameViewModel
import com.example.ffclone.game.GridPosition
import com.example.ffclone.game.Player
import com.example.ffclone.game.overworld.GameMap
import com.example.ffclone.game.overworld.TileType
import com.example.ffclone.ui.OverworldControls
import com.example.ffclone.ui.OverworldScreen
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OverworldMovementTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var viewModel: GameViewModel

    @Before
    fun setUp() {
        composeTestRule.runOnUiThread {
            viewModel = GameViewModel(ApplicationProvider.getApplicationContext<Application>())
            viewModel.state.value = GameScreenState.OVERWORLD(
                map = GameMap(5, 5, List(5) { List(5) { TileType.EMPTY } }),
                player = Player(position = GridPosition(2, 2))
            )
        }
        composeTestRule.setContent {
            val state by viewModel.state
            Column(modifier = Modifier.fillMaxSize()) {
                val overworld = state as GameScreenState.OVERWORLD
                OverworldScreen(overworld, Modifier.weight(1f))
                OverworldControls(viewModel)
            }
        }
    }

    @Test
    fun movePlayer_right() {
        // Click Right Arrow
        composeTestRule.onNodeWithText("\u2192").performClick()

        // Verify player moved right
        assertPosition(GridPosition(3, 2))
    }

    @Test
    fun movePlayer_left() {
        // Click Left Arrow
        composeTestRule.onNodeWithText("\u2190").performClick()

        // Verify player moved left
        assertPosition(GridPosition(1, 2))
    }

    @Test
    fun movePlayer_up() {
        // Click Up Arrow
        composeTestRule.onNodeWithText("\u2191").performClick()

        // Verify player moved up
        assertPosition(GridPosition(2, 1))
    }

    @Test
    fun movePlayer_down() {
        // Click Down Arrow
        composeTestRule.onNodeWithText("\u2193").performClick()

        // Verify player moved down
        assertPosition(GridPosition(2, 3))
    }

    @Test
    fun movePlayer_boundaries() {
        // Set player to top-left corner
        composeTestRule.runOnIdle {
            val state = viewModel.state.value as GameScreenState.OVERWORLD
            viewModel.state.value = state.copy(player = state.player.copy(position = GridPosition(0, 0)))
        }

        // Attempt to move outside top-left
        composeTestRule.onNodeWithText("\u2191").performClick()
        composeTestRule.onNodeWithText("\u2190").performClick()
        assertPosition(GridPosition(0, 0))

        // Set player to bottom-right corner
        composeTestRule.runOnIdle {
            val state = viewModel.state.value as GameScreenState.OVERWORLD
            viewModel.state.value = state.copy(
                player = state.player.copy(position = GridPosition(state.map.width - 1, state.map.height - 1))
            )
        }

        // Attempt to move outside bottom-right
        composeTestRule.onNodeWithText("\u2193").performClick()
        composeTestRule.onNodeWithText("\u2192").performClick()
        assertPosition(GridPosition(4, 4))
    }

    @Test
    fun repeatedClicks_useUpdatedState() {
        repeat(2) { composeTestRule.onNodeWithText("\u2192").performClick() }
        composeTestRule.onNodeWithText("\u2193").performClick()
        composeTestRule.onNodeWithText("\u2190").performClick()
        assertPosition(GridPosition(3, 3))
    }

    private fun assertPosition(expected: GridPosition) {
        composeTestRule.runOnIdle {
            val state = viewModel.state.value as GameScreenState.OVERWORLD
            assertEquals(expected, state.player.position)
        }
    }
}
