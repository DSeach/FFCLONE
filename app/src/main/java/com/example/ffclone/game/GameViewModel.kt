package com.example.ffclone.game

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.ffclone.game.overworld.*
import kotlin.collections.emptyList


class GameViewModel(app: Application) : AndroidViewModel(app) {

    // Initialize the overworld map at startup
    var state = mutableStateOf<GameScreenState>(
        loadOverworld("overworld/firstmap", GridPosition(0,0))
    )
        private set

    // Load a map from assets and create an overworld screen
    fun loadOverworld(fileName: String, playerStart: GridPosition): GameScreenState.OVERWORLD {
        val map = readMapFromAsset(fileName, getApplication()) // appContext must be supplied
        val player = Player(position = playerStart)
        val enemies = emptyList<Enemy>()
        return GameScreenState.OVERWORLD(
            map = map,
            player = player,
            enemies = enemies
        )
    }

    fun movePlayer(dx: Int, dy: Int) {
        val current = state.value
        if (current !is GameScreenState.OVERWORLD) return

        val newPos = movePosition(current.map.width, current.map.height, current.player.position, dx, dy)
        val updatedPlayer = current.player.copy(position = newPos)
        state.value = updateOverworldStateAfterMove(current, updatedPlayer)
    }

    fun exitBattle(){
        val current = state.value
        if (current is GameScreenState.BATTLE) {
            state.value = GameScreenState.OVERWORLD(
                player = current.player,
                enemies = emptyList(),
                map = current.map

            )
        }
    }
}
