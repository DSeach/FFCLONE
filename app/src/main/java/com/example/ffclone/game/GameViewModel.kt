package com.example.ffclone.game

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    var gameState = mutableStateOf(GameState.OVERWORLD) //What var is this?
        private set

    var player = mutableStateOf(Player())
        private set

    fun movePlayer(moveX: Int, moveY: Int) {
        val newX = (player.value.x + moveX).coerceIn(0, Constants.Grid.GRID_WIDTH - 1)
        val newy = (player.value.y + moveY).coerceIn(0, Constants.Grid.GRID_HEIGHT - 1)
        player.value = Player(newX , newy)
    }

    fun enterBattle() {
        gameState.value = GameState.BATTLE
    }

    fun exitBattle(){
        gameState.value = GameState.OVERWORLD
    }
}