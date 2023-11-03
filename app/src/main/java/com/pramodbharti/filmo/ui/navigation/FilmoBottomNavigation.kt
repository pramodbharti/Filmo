package com.pramodbharti.filmo.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp

val list = listOf(Movies, TvShows, Favs)

@Composable
fun FilmoBottomNavigation(
    currentTab: BottomNaviagtionType,
    onTabPressed: ((BottomNaviagtionType) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                val strokeWidth = 1.dp.toPx() / 2
                drawLine(
                    color = Color.Gray,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = strokeWidth
                )
            },
        tonalElevation = 0.dp
    ) {
        for (navItem in navigationItemContentList) {
            NavigationBarItem(
                selected = currentTab == navItem.navType,
                onClick = { onTabPressed(navItem.navType) },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(navItem.icon),
                        contentDescription = navItem.text
                    )
                },
                label = {
                    Text(
                        text = navItem.text,
                        style = MaterialTheme.typography.bodySmall
                    )
                })
        }
    }
}


data class NavigationItemContent(
    val navType: BottomNaviagtionType,
    @DrawableRes
    val icon: Int,
    val text: String
)
