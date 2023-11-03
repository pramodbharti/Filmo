package com.pramodbharti.filmo.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

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
    const val mediaId = "media_id"
    const val mediaType = "media_type"
    val routeWithArgs = "$route/{$mediaId}/{$mediaType}"
    val arguments = listOf(
        navArgument(mediaId) { type = NavType.IntType },
        navArgument(mediaType) { type = NavType.StringType }
    )
}