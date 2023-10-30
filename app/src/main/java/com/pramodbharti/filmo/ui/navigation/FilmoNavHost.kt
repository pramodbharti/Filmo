package com.pramodbharti.filmo.ui.navigation

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
    navController: NavHostController = rememberNavController(),
    currentScreen: FilmoDestination,
    topBarTitle: (String) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = currentScreen.route,
        modifier = modifier
    ) {
        composable(Movies.route) {
            MoviesScreen(
                onMediaItemClick = { item ->
                    topBarTitle(item.title)
                    navController.navigateToDetailsScreen(item.id, item.mediaType)
                },
                onSeeAllClick = {
                    topBarTitle(it)
                })
        }

        composable(TvShows.route) {
            TvShowsScreen(
                onMediaItemClick = { item ->
                    topBarTitle(item.title)
                    navController.navigateToDetailsScreen(item.id, item.mediaType)
                },
                onSeeAllClick = {
                    topBarTitle(it)
                }
            )
        }

        composable(Favs.route) {
            FavItemsScreen(onDeleteClick = {
                // TODO: delete this item from database
            })
        }

        composable(SeeAll.route) {
            SeeAllScreen()
        }

        composable(
            route = Details.routeWithArgs,
            arguments = Details.arguments
        ) {
            DetailsScreen(
                onMediaClick = { item ->
                    topBarTitle(item.title)
                    navController.navigate("${Details.route}/${item.id}/${item.mediaType}")
                },
                onFavClicked = {
                    // TODO: Save favorite movie to Room database
                },
                onBackPressed = {
                    navController.navigateUp()
                })
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route = route) {
        popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }

fun NavHostController.navigateToDetailsScreen(mediaId: Int, mediaType: String) {
    this.navigateSingleTopTo("${Details.route}/$mediaId/$mediaType")
}