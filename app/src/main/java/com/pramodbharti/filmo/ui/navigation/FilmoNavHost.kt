package com.pramodbharti.filmo.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pramodbharti.filmo.ui.screens.details.DetailsScreen
import com.pramodbharti.filmo.ui.screens.favs.FavItemsScreen
import com.pramodbharti.filmo.ui.screens.movies.MoviesScreen
import com.pramodbharti.filmo.ui.screens.seeall.SeeAllScreen
import com.pramodbharti.filmo.ui.screens.tvshows.TvShowsScreen

@Composable
fun FilmoNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Movies.route,
        modifier = modifier
    ) {
        composable(Movies.route) {
            MoviesScreen(onMediaItemClick = { item ->
                Log.e("TAG", "FilmoNavHost: ${item.toString()}")
                navController.navigateToDetailsScreen(item.id)
            })
        }

        composable(TvShows.route) {
            TvShowsScreen()
        }

        composable(Favs.route) {
            FavItemsScreen()
        }

        composable(SeeAll.route) {
            SeeAllScreen()
        }

        composable(
            route = Details.routeWithArgs,
            arguments = Details.arguments
        ) { navBackStack ->
            val movieId = navBackStack.arguments?.getInt(Details.movieId)
            DetailsScreen(movieId = movieId)
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route = route) {
        popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }

fun NavHostController.navigateToDetailsScreen(movieId: Int) {
    this.navigateSingleTopTo("${Details.route}/$movieId")
}