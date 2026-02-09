package com.example.ffclone.game

import com.example.ffclone.game.overworld.GameMap

sealed class GameScreenState {
    data class OVERWORLD(
        val map: GameMap,
        val player: Player,
        val enemies: List<Enemy> = emptyList()
    ) : GameScreenState()

    data class BATTLE(
        val player: Player,
        val enemies: List<Enemy>,
        val selectedMenuIndex: Int = 0,
        val map: GameMap
    ) : GameScreenState()

    data class DIALOGUE(
        val text: String,
    ) : GameScreenState()
}