package com.pramodbharti.filmo.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource

val list = listOf(Movies, TvShows, Favs)

@Composable
fun FilmoBottomNavigation(
    currentTab: BottomNaviagtionType,
    onTabPressed: ((BottomNaviagtionType) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationBarItem(
                selected = currentTab == navItem.navType,
                onClick = { onTabPressed(navItem.navType) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text
                    )
                })
        }
    }
}


data class NavigationItemContent(
    val navType: BottomNaviagtionType,
    val icon: ImageVector,
    val text: String
)
