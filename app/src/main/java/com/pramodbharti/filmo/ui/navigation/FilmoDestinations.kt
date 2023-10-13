package com.pramodbharti.filmo.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.pramodbharti.filmo.ui.screens.details.DetailsScreen
import com.pramodbharti.filmo.ui.screens.favs.FavItemsScreen
import com.pramodbharti.filmo.ui.screens.movies.MoviesScreen
import com.pramodbharti.filmo.ui.screens.seeall.SeeAllScreen
import com.pramodbharti.filmo.ui.screens.tvshows.TvShowsScreen

interface FilmoDestination {
    val icon: ImageVector?
    val route: String
    val screen: @Composable () -> Unit
}

object Movies : FilmoDestination {
    override val icon = null
    override val route = "movies"
    override val screen: @Composable () -> Unit = { MoviesScreen() }
}

object TvShows : FilmoDestination {
    override val icon = null
    override val route = "tv_shows"
    override val screen: @Composable () -> Unit = { TvShowsScreen() }
}

object Favs : FilmoDestination {
    override val icon = null
    override val route = "favs"
    override val screen: @Composable () -> Unit = { FavItemsScreen() }
}


object Details : FilmoDestination {
    override val icon = Icons.Filled.KeyboardArrowLeft
    override val route = "details"
    override val screen: @Composable () -> Unit = { DetailsScreen() }
}

object SeeAll : FilmoDestination {
    override val icon = Icons.Filled.KeyboardArrowLeft
    override val route = "see_all"
    override val screen: @Composable () -> Unit = { SeeAllScreen() }
}