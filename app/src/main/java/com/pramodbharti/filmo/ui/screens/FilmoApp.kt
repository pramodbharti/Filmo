import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pramodbharti.filmo.R
import com.pramodbharti.filmo.ui.components.FilmoAppBar
import com.pramodbharti.filmo.ui.navigation.BottomNaviagtionType
import com.pramodbharti.filmo.ui.navigation.Details
import com.pramodbharti.filmo.ui.navigation.Favs
import com.pramodbharti.filmo.ui.navigation.FilmoBottomNavigation
import com.pramodbharti.filmo.ui.navigation.FilmoDestination
import com.pramodbharti.filmo.ui.navigation.FilmoNavHost
import com.pramodbharti.filmo.ui.navigation.Movies
import com.pramodbharti.filmo.ui.navigation.NavigationItemContent
import com.pramodbharti.filmo.ui.navigation.TvShows

private const val TAG = "FilmoApp"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmoApp() {
    var currentScreen: FilmoDestination by remember { mutableStateOf(Movies) }
    var topBarTitle by remember { mutableStateOf("Filmo") }
    val navController = rememberNavController()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    var tabSelected by remember { mutableStateOf(BottomNaviagtionType.Movies) }
    var showBottomBar by remember { mutableStateOf(true) }
    var showTopBar by remember { mutableStateOf(true) }
    val currentBackSack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackSack?.destination
    showBottomBar = when (currentDestination?.route) {
        Movies.route, TvShows.route, Favs.route -> true
        else -> false
    }
    showTopBar = when (currentDestination?.route) {
        Details.routeWithArgs -> false
        else -> true
    }
    Scaffold(
        topBar = {
            if (showTopBar) {
                FilmoAppBar(
                    title = topBarTitle,
                    canNavigateBack = !showBottomBar,
                    onBackPressed = {
                        navController.navigateUp()
                    },
                    scrollBehavior = scrollBehavior
                )
            }
        },
        bottomBar = {
            if (showBottomBar) {
                FilmoBottomNavigation(
                    currentTab = tabSelected,
                    onTabPressed = {
                        tabSelected = it
                        currentScreen = when (tabSelected) {
                            BottomNaviagtionType.Movies -> Movies
                            BottomNaviagtionType.TvShows -> TvShows
                            BottomNaviagtionType.Favs -> Favs
                        }
                    },
                    navigationItemContentList = navigationItemContentList
                )
            }
        }
    ) { paddingValues ->
        FilmoNavHost(
            navController = navController,
            modifier = Modifier.padding(paddingValues),
            currentScreen = currentScreen,
            topBarTitle = { topBarTitle = it }
        )
    }
}

val navigationItemContentList = listOf(
    NavigationItemContent(
        navType = BottomNaviagtionType.Movies,
        icon = R.drawable.baseline_local_movies,
        text = "Movies"
    ),
    NavigationItemContent(
        navType = BottomNaviagtionType.TvShows,
        icon = R.drawable.baseline_tv,
        text = "TvShows"
    ),
    NavigationItemContent(
        navType = BottomNaviagtionType.Favs,
        icon = R.drawable.baseline_favorite,
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