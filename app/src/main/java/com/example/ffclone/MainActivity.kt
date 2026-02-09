package com.example.ffclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ffclone.game.GameViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ffclone.game.GameScreenState
import com.example.ffclone.ui.OverworldControls
import com.example.ffclone.ui.OverworldScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val viewModel: GameViewModel = viewModel() //controller to change viewState

            val state by viewModel.state

            when(val gameState = state){
                is GameScreenState.OVERWORLD -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        OverworldScreen(
                            state = gameState,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        OverworldControls(viewModel)
                    }
                }

                is GameScreenState.BATTLE -> {
                    Box(
                        modifier = Modifier.fillMaxSize()
                            .clickable{
                                viewModel.exitBattle()
                            },
                        contentAlignment = Alignment.Center
                    )
                    {
                        Text("BATTLE")
                    }
                }

                is GameScreenState.DIALOGUE -> {
                    Text("This is the Dialogue Screen!")
                }
            }
        }
    }
}