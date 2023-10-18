package com.pramodbharti.filmo.ui.screens.seeall

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pramodbharti.filmo.dummydata.dummyMovies
import com.pramodbharti.filmo.ui.components.MediaPoster
import com.pramodbharti.filmo.ui.models.MediaItem

@Composable
fun SeeAllScreen(
    items: List<MediaItem> = emptyList(),
    modifier: Modifier = Modifier,
    onMediaItemClick: (MediaItem) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 3),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(8.dp)
    ) {
        items(items) { item ->
            MediaPoster(movieItem = item, onMediaItemClick = { onMediaItemClick(item) })
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SeeAllScreenPreview() {
    SeeAllScreen(items = dummyMovies + dummyMovies, onMediaItemClick = {})
}