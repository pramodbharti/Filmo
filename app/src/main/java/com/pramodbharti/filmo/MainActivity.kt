package com.pramodbharti.filmo

import FilmoApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.pramodbharti.filmo.ui.components.LandingScreen
import com.pramodbharti.filmo.ui.theme.FilmoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilmoTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    var showLandingScreen by rememberSaveable { mutableStateOf(true) }
                    if (showLandingScreen) {
                        LandingScreen(onTimeOut = { showLandingScreen = false })
                    } else {
                        FilmoApp()
                    }
                }
            }
        }
    }
}
