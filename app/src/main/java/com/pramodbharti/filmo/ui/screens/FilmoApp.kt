import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pramodbharti.filmo.dummydata.dummyMovies
import com.pramodbharti.filmo.ui.screens.movies.MoviesScreen

private const val TAG = "FilmoApp"

@Composable
fun FilmoApp(modifier: Modifier = Modifier) {
    MoviesScreen(dummyMovies, onSeeAllClick = {
        Log.e(TAG, "FilmoApp: $it")
    },
        onMediaItemClick = {
            Log.e(TAG, "FilmoApp: ${it.toString()}")
        })
}

@Preview(name = "DarkMode", uiMode = UI_MODE_NIGHT_YES, showSystemUi = true)
@Preview(name = "LightMode", uiMode = UI_MODE_NIGHT_NO, showSystemUi = true)
@Composable
fun FilmoAppPreview() {
    Surface(tonalElevation = 0.dp) {
        FilmoApp()
    }
}