package com.pramodbharti.filmo.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.pramodbharti.filmo.ui.screens.details.DetailsScreen
import com.pramodbharti.filmo.ui.screens.favs.FavItemsScreen
import com.pramodbharti.filmo.ui.screens.movies.MoviesScreen
import com.pramodbharti.filmo.ui.screens.seeall.SeeAllScreen
import com.pramodbharti.filmo.ui.screens.tvshows.TvShowsScreen

interface FilmoDestination {
    val icon: ImageVector?
    val route: String
}

object Movies : FilmoDestination {
    override val icon = null
    override val route = "movies"
}

object TvShows : FilmoDestination {
    override val icon = null
    override val route = "tv_shows"
}

object Favs : FilmoDestination {
    override val icon = null
    override val route = "favs"
}

object Details : FilmoDestination {
    override val icon = Icons.Filled.KeyboardArrowLeft
    override val route = "details"
    val movieId = "movie_id"
    val routeWithArgs = "$route/{$movieId}"
    val arguments = listOf(
        navArgument(movieId) { type = NavType.IntType }
    )
}

object SeeAll : FilmoDestination {
    override val icon = Icons.Filled.KeyboardArrowLeft
    override val route = "see_all"
}