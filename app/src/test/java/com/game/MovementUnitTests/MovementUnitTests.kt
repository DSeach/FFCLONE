package com.game.MovementUnitTests

import com.example.ffclone.game.Constants
import com.example.ffclone.game.GameViewModel
import com.example.ffclone.game.Player
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MovementUnitTests {

    private lateinit var viewModel: GameViewModel

    @Before
    fun setup() {
        viewModel = GameViewModel()
    }

    @Test
    fun movePlayer_up() {
        viewModel.player.value = Player(x = 5, y = 5)
        viewModel.movePlayer(0, -1)
        assertEquals(5, viewModel.player.value.x)
        assertEquals(4, viewModel.player.value.y)
    }

    @Test
    fun movePlayer_down() {
        viewModel.player.value = Player(x = 5, y = 5)
        viewModel.movePlayer(0, 1)
        assertEquals(5, viewModel.player.value.x)
        assertEquals(6, viewModel.player.value.y)
    }

    @Test
    fun movePlayer_left() {
        viewModel.player.value = Player(x = 5, y = 5)
        viewModel.movePlayer(-1, 0)
        assertEquals(4, viewModel.player.value.x)
        assertEquals(5, viewModel.player.value.y)
    }

    @Test
    fun movePlayer_right() {
        viewModel.player.value = Player(x = 5, y = 5)
        viewModel.movePlayer(1, 0)
        assertEquals(6, viewModel.player.value.x)
        assertEquals(5, viewModel.player.value.y)
    }

    @Test
    fun movePlayer_cannotExceedBounds() {
        // Top-left corner
        viewModel.player.value = Player(x = 0, y = 0)
        viewModel.movePlayer(-1, 0)
        viewModel.movePlayer(0, -1)
        assertEquals(0, viewModel.player.value.x)
        assertEquals(0, viewModel.player.value.y)

        // Bottom-right corner
        viewModel.player.value = Player(x = Constants.Grid.GRID_WIDTH - 1,
            y = Constants.Grid.GRID_HEIGHT - 1)
        viewModel.movePlayer(1, 0)
        viewModel.movePlayer(0, 1)
        assertEquals(Constants.Grid.GRID_WIDTH - 1, viewModel.player.value.x)
        assertEquals(Constants.Grid.GRID_HEIGHT - 1, viewModel.player.value.y)
    }
}