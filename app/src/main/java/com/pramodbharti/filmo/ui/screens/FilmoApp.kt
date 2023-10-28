import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Send
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
import com.pramodbharti.filmo.ui.components.FilmoAppBar
import com.pramodbharti.filmo.ui.navigation.BottomNaviagtionType
import com.pramodbharti.filmo.ui.navigation.FilmoBottomNavigation
import com.pramodbharti.filmo.ui.navigation.FilmoDestination
import com.pramodbharti.filmo.ui.navigation.FilmoNavHost
import com.pramodbharti.filmo.ui.navigation.Movies
import com.pramodbharti.filmo.ui.navigation.NavigationItemContent

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
            FilmoBottomNavigation(currentTab = BottomNaviagtionType.Movies, onTabPressed = {}, navigationItemContentList = navigationItemContentList)
        }
    ) { paddingValues ->
        FilmoNavHost(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

val navigationItemContentList = listOf(
    NavigationItemContent(
        navType = BottomNaviagtionType.Movies,
        icon = Icons.Filled.PlayArrow,
        text = "Movies"
    ),
    NavigationItemContent(
        navType = BottomNaviagtionType.TvShows,
        icon = Icons.Filled.Send,
        text = "TvShows"
    ),
    NavigationItemContent(
        navType = BottomNaviagtionType.Favs,
        icon = Icons.Default.Favorite,
        text = "Favs"
    )
)

@Preview(name = "DarkMode", uiMode = UI_MODE_NIGHT_YES, showSystemUi = true)
@Preview(name = "LightMode", uiMode = UI_MODE_NIGHT_NO, showSystemUi = true)
@Composable
fun FilmoAppPreview() {
    Surface(tonalElevation = 0.dp) {
        FilmoApp()
    }
}