package com.example.ffclone.game.overworld

data class GameMap(
    val width: Int,
    val height: Int,
    val tiles: List<List<TileType>>
) {
    fun tileAt(x: Int, y: Int): TileType = tiles[y][x]
}