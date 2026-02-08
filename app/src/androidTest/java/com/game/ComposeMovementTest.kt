package com.game

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.ffclone.game.Constants
import com.example.ffclone.game.GameViewModel
import com.example.ffclone.ui.OverworldControls
import com.example.ffclone.ui.OverworldScreen
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OverworldMovementTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun movePlayer_right() {
        val viewModel = GameViewModel()
        composeTestRule.setContent {
            OverworldScreen(viewModel)
            OverworldControls(viewModel)
        }

        val initialX = viewModel.player.value.x
        val initialY = viewModel.player.value.y

        // Click Right Arrow
        composeTestRule.onNodeWithText("→").performClick()

        // Verify player moved right
        assertEquals(initialX + 1, viewModel.player.value.x)
        assertEquals(initialY, viewModel.player.value.y)
    }

    @Test
    fun movePlayer_left() {
        val viewModel = GameViewModel()
        composeTestRule.setContent {
            OverworldScreen(viewModel)
            OverworldControls(viewModel)
        }

        val initialX = viewModel.player.value.x
        val initialY = viewModel.player.value.y

        // Click Left Arrow
        composeTestRule.onNodeWithText("←").performClick()

        // Verify player moved left
        val expectedX = if (initialX > 0) initialX - 1 else 0
        assertEquals(expectedX, viewModel.player.value.x)
        assertEquals(initialY, viewModel.player.value.y)
    }

    @Test
    fun movePlayer_up() {
        val viewModel = GameViewModel()
        composeTestRule.setContent {
            OverworldScreen(viewModel)
            OverworldControls(viewModel)
        }

        val initialX = viewModel.player.value.x
        val initialY = viewModel.player.value.y

        // Click Up Arrow
        composeTestRule.onNodeWithText("↑").performClick()

        // Verify player moved up
        val expectedY = if (initialY > 0) initialY - 1 else 0
        assertEquals(initialX, viewModel.player.value.x)
        assertEquals(expectedY, viewModel.player.value.y)
    }

    @Test
    fun movePlayer_down() {
        val viewModel = GameViewModel()
        composeTestRule.setContent {
            OverworldScreen(viewModel)
            OverworldControls(viewModel)
        }

        val initialX = viewModel.player.value.x
        val initialY = viewModel.player.value.y

        // Click Down Arrow
        composeTestRule.onNodeWithText("↓").performClick()

        // Verify player moved down
        val expectedY = if (initialY < Constants.Grid.GRID_HEIGHT - 1) initialY + 1 else Constants.Grid.GRID_HEIGHT - 1
        assertEquals(initialX, viewModel.player.value.x)
        assertEquals(expectedY, viewModel.player.value.y)
    }

    @Test
    fun movePlayer_boundaries() {
        val viewModel = GameViewModel()

        // Set player to top-left corner
        viewModel.player.value = viewModel.player.value.copy(x = 0, y = 0)
        composeTestRule.setContent {
            OverworldScreen(viewModel)
            OverworldControls(viewModel)
        }

        // Attempt to move outside top-left
        composeTestRule.onNodeWithText("↑").performClick()
        composeTestRule.onNodeWithText("←").performClick()

        assertEquals(0, viewModel.player.value.x)
        assertEquals(0, viewModel.player.value.y)

        // Set player to bottom-right corner
        viewModel.player.value = viewModel.player.value.copy(
            x = Constants.Grid.GRID_WIDTH - 1,
            y = Constants.Grid.GRID_HEIGHT - 1
        )

        // Attempt to move outside bottom-right
        composeTestRule.onNodeWithText("↓").performClick()
        composeTestRule.onNodeWithText("→").performClick()

        assertEquals(Constants.Grid.GRID_WIDTH - 1, viewModel.player.value.x)
        assertEquals(Constants.Grid.GRID_HEIGHT - 1, viewModel.player.value.y)
    }
}
