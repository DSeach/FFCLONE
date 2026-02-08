package com.example.ffclone.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ffclone.game.GameViewModel
@Composable
fun OverworldControls(viewModel: GameViewModel) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = { viewModel.movePlayer(0, -1) },
            modifier = Modifier.size(80.dp).padding(8.dp)
        ) { Text("↑") }

        Row {
            Button(
                onClick = { viewModel.movePlayer(-1, 0) },
                modifier = Modifier.size(80.dp).padding(8.dp)
            ) { Text("←") }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { viewModel.movePlayer(1, 0) },
                modifier = Modifier.size(80.dp).padding(8.dp)
            ) { Text("→") }
        }

        Button(
            onClick = { viewModel.movePlayer(0, 1) },
            modifier = Modifier.size(80.dp).padding(8.dp)
        ) { Text("↓") }
    }
}