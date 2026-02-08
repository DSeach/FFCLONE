package com.example.ffclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.example.ffclone.game.GameState
import com.example.ffclone.game.GameViewModel
import com.example.ffclone.ui.theme.FFCLONETheme
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: GameViewModel = viewModel<GameViewModel>() //controller to change viewState

            when(viewModel.gameState.value){
                GameState.OVERWORLD -> {
                    Box(
                        modifier = Modifier.fillMaxSize()
                            .clickable{
                            viewModel.enterBattle()
                        },
                        contentAlignment = Alignment.Center
                    )
                    {
                        Text(text = "Overworld (tap to battle!)")
                    }
                }

                GameState.BATTLE -> {
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

                GameState.DIALOGUE -> {
                    Text("This is the Dialogue Screen!")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FFCLONETheme {
        Greeting("Android")
    }
}