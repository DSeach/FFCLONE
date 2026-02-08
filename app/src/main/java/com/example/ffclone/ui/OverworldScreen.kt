package com.example.ffclone.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.ffclone.game.Constants
import com.example.ffclone.game.GameViewModel

@Composable
fun OverworldScreen(viewModel: GameViewModel, modifier: Modifier = Modifier) {
    val player = viewModel.player.value

    BoxWithConstraints(modifier = modifier) {
        // Compute cell size based on available width
        val scope = this
        val cellSize = maxWidth / Constants.Grid.GRID_WIDTH

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            for (y in 0 until Constants.Grid.GRID_HEIGHT) {
                Row {
                    for (x in 0 until Constants.Grid.GRID_WIDTH) {
                        Box(
                            modifier = Modifier
                                .size(cellSize)
                                .background(
                                    if (x == player.x && y == player.y) Color.Blue
                                    //can put enemies here
                                    else Color.LightGray
                                )
                        )
                    }
                }
            }
        }
    }
}