package com.pramodbharti.filmo.ui.screens.movies

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pramodbharti.filmo.dummydata.dummyMovies
import com.pramodbharti.filmo.ui.components.CarouselItem
import com.pramodbharti.filmo.ui.components.FilmoCarousel
import com.pramodbharti.filmo.ui.components.MediaItemsPosterRow
import com.pramodbharti.filmo.ui.components.MediaSlots
import com.pramodbharti.filmo.ui.components.carouselTransition
import com.pramodbharti.filmo.ui.models.MediaItem
import com.pramodbharti.filmo.ui.theme.FilmoTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoviesScreen(
    movies: List<MediaItem>,
    onSeeAllClick: (String) -> Unit,
    onMediaItemClick: (MediaItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        FilmoCarousel(itemsCount = movies.size) { index, pagerState ->
            CarouselItem(
                movieItem = movies[index],
                modifier = modifier.carouselTransition(index, pagerState),
                onMediaItemClick = onMediaItemClick
            )
        }
        MediaSlots(title = "Trending", onSeeAllClick = onSeeAllClick) {
            MediaItemsPosterRow(movies = movies, onMediaItemClick = onMediaItemClick)
        }
        MediaSlots(title = "Popular", onSeeAllClick = onSeeAllClick) {
            MediaItemsPosterRow(movies = movies, onMediaItemClick = onMediaItemClick)
        }
        MediaSlots(title = "Trending", onSeeAllClick = onSeeAllClick) {
            MediaItemsPosterRow(movies = movies, onMediaItemClick = onMediaItemClick)
        }
        MediaSlots(title = "Popular", onSeeAllClick = onSeeAllClick) {
            MediaItemsPosterRow(movies = movies, onMediaItemClick = onMediaItemClick)
        }
    }
}


@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES, name = "name")
@Composable
fun MoviesScreenPreview() {
    FilmoTheme {
        MoviesScreen(dummyMovies, onSeeAllClick = {}, onMediaItemClick = {})
    }
}


