package com.example.ffclone.game

data class Enemy(
    val id: String,

    //type?
    val name: String,
    val maxHp: Int,
    val currentHp: Int,
    val attack: Int,
    val defense: Int,
    val position: GridPosition
)