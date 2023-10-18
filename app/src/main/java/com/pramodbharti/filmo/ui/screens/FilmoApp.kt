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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pramodbharti.filmo.dummydata.dummyMovies
import com.pramodbharti.filmo.ui.components.FilmoAppBar
import com.pramodbharti.filmo.ui.navigation.Details
import com.pramodbharti.filmo.ui.navigation.Favs
import com.pramodbharti.filmo.ui.navigation.FilmoDestination
import com.pramodbharti.filmo.ui.navigation.FilmoNavHost
import com.pramodbharti.filmo.ui.navigation.Movies
import com.pramodbharti.filmo.ui.navigation.SeeAll
import com.pramodbharti.filmo.ui.navigation.TvShows
import com.pramodbharti.filmo.ui.screens.details.DetailsScreen
import com.pramodbharti.filmo.ui.screens.favs.FavItemsScreen
import com.pramodbharti.filmo.ui.screens.movies.MoviesScreen
import com.pramodbharti.filmo.ui.screens.seeall.SeeAllScreen
import com.pramodbharti.filmo.ui.screens.tvshows.TvShowsScreen

private const val TAG = "FilmoApp"

@Composable
fun FilmoApp() {
    var currentScreen: FilmoDestination by remember { mutableStateOf(Movies) }
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            FilmoAppBar(
                title = "Filmo",
                canNavigateBack = navController.previousBackStackEntry != null,
                onBackPressed = { navController.navigateUp() })
        },
        bottomBar = {

        }
    ) { paddingValues ->
        FilmoNavHost(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
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