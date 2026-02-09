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
import com.example.ffclone.game.GameScreenState
import com.example.ffclone.game.overworld.TileType

@Composable
fun OverworldScreen(state: GameScreenState.OVERWORLD, modifier: Modifier = Modifier) {
    val player = state.player
    val map = state.map
    val enemies = state.enemies

    BoxWithConstraints(modifier = modifier) {

        val scope = this
        val cellSize = maxWidth / Constants.Grid.GRID_WIDTH

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            for (y in 0 until map.height) {
                Row {
                    for (x in 0 until map.width) {

                        val color = when {
                            player.position.x == x && player.position.y == y -> Color.Blue
                            enemies.any { it.position.x == x && it.position.y == y } -> Color.Red
                            map.tileAt(x, y) == TileType.DANGER -> Color.Magenta
                            map.tileAt(x, y) == TileType.WALL -> Color.DarkGray
                            else -> Color.LightGray
                        }
                        Box(

                            modifier = Modifier
                                .size(cellSize)
                                .background(color)
                        )
                    }
                }
            }
        }
    }
}