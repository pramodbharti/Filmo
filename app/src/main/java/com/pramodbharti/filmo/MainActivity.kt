package com.pramodbharti.filmo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.BuildCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pramodbharti.filmo.ui.home.HomeViewModel
import com.pramodbharti.filmo.ui.home.MovieUiState
import com.pramodbharti.filmo.ui.theme.FilmoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilmoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
                    when (val movieUiState = homeViewModel.movieUiState) {
                        is MovieUiState.Success -> {
                            Greeting((movieUiState.moviesCount.toString()))
                        }

                        is MovieUiState.Error -> {
                            Greeting("Error")
                        }

                        is MovieUiState.Loading -> {
                            Greeting("Loading")
                        }
                    }
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
    FilmoTheme {
        Greeting("Android")
    }
}