package com.example.ffclone.game.overworld

import android.content.Context

fun parseMap(bitmap: List<String>): GameMap {
    val tiles = bitmap.map { row ->
        row.map { char ->
            when (char) {
                '.' -> TileType.EMPTY
                'D' -> TileType.DANGER
                'W' -> TileType.WALL
                else -> error("Unknown map char: $char")
            }
        }
    }
    return GameMap(tiles.first().size, tiles.size, tiles)
}

fun readMapFromAsset(fileName: String, context: Context): GameMap{

    val text = context.assets.open(fileName).bufferedReader().use { it.readText() }
    val lines = text.lines().filter { it.isNotBlank() }
    require(lines.all { it.length == lines.first().length }) {
        "All lines in the map file must have the same length"
    }
    return parseMap(lines)
}