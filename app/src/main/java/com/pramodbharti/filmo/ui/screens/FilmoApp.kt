import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.pramodbharti.filmo.dummydata.dummyMovies
import com.pramodbharti.filmo.ui.navigation.FilmoDestination
import com.pramodbharti.filmo.ui.navigation.Movies
import com.pramodbharti.filmo.ui.screens.movies.MoviesScreen

private const val TAG = "FilmoApp"

@Composable
fun FilmoApp() {
    var currentScreen: FilmoDestination by remember { mutableStateOf(Movies) }
    val navController = rememberNavController()
    Scaffold(
        topBar = {

        },
        bottomBar = {

        }
    ) { paddingValues ->
        Box(Modifier.padding(paddingValues)) {
            currentScreen.screen()
        }

    }

}

@Preview(name = "DarkMode", uiMode = UI_MODE_NIGHT_YES, showSystemUi = true)
@Preview(name = "LightMode", uiMode = UI_MODE_NIGHT_NO, showSystemUi = true)
@Composable
fun FilmoAppPreview() {
    Surface(tonalElevation = 0.dp) {
        FilmoApp()
    }
}