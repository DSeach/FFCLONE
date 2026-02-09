package com.example.ffclone.game

data class Player (
    val id: String = "player",
    val name : String = "Hero",

    val maxHp: Int = 100,
    val currentHp: Int = 100,
    val attack: Int = 10,
    val defense: Int = 5,

    //Overworld
    val position: GridPosition
)