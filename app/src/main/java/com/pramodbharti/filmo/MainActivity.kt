package com.pramodbharti.filmo

import FilmoApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pramodbharti.filmo.ui.theme.FilmoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilmoTheme {
                // A surface container using the 'background' color from the theme
                Surface(tonalElevation = 5.dp) {
                    FilmoApp()
                }
            }
        }
    }
}
