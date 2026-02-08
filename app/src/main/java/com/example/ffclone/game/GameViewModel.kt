package com.example.ffclone.game

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    var gameState = mutableStateOf(GameState.OVERWORLD) //What var is this?
        private set

    fun enterBattle() {
        gameState.value = GameState.BATTLE
    }

    fun exitBattle(){
        gameState.value = GameState.OVERWORLD
    }
}