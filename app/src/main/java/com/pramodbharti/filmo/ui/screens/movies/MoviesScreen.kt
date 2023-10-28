package com.pramodbharti.filmo.ui.screens.movies

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pramodbharti.filmo.dummydata.dummyMovies
import com.pramodbharti.filmo.ui.components.CarouselItem
import com.pramodbharti.filmo.ui.components.FilmoCarousel
import com.pramodbharti.filmo.ui.components.MediaItemsPosterRow
import com.pramodbharti.filmo.ui.components.MediaSlots
import com.pramodbharti.filmo.ui.components.ShimmerLoadingScreen
import com.pramodbharti.filmo.ui.components.carouselTransition
import com.pramodbharti.filmo.ui.models.MediaItem
import com.pramodbharti.filmo.ui.theme.FilmoTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoviesScreen(
    fmovies: List<MediaItem> = emptyList(),
    onSeeAllClick: (String) -> Unit = {},
    onMediaItemClick: (MediaItem) -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: MoviesViewModel = viewModel(factory = MoviesViewModel.Factory)
) {
    val uiState by viewModel.moviesUiState.collectAsStateWithLifecycle()

    when(val state = uiState){
        is MoviesUiState.Error -> {
            Log.e("TAG", "MoviesScreen: Error ${state.msg}", )
        }
        MoviesUiState.Loading -> {
            Log.e("TAG", "MoviesScreen: Loading", )
            ShimmerLoadingScreen()
        }
        is MoviesUiState.Success -> {
            Log.e("TAG", "MoviesScreen: Success ${state.movies.toString()}", )
            Column(modifier.verticalScroll(rememberScrollState())) {
                FilmoCarousel(itemsCount = state.movies.trending.size) { index, pagerState ->
                    CarouselItem(
                        movieItem = state.movies.trending[index],
                        modifier = modifier.carouselTransition(index, pagerState),
                        onMediaItemClick = onMediaItemClick
                    )
                }
                MediaSlots(title = "Now Playing", onSeeAllClick = onSeeAllClick) {
                    MediaItemsPosterRow(movies = state.movies.nowPlaying, onMediaItemClick = onMediaItemClick)
                }
                MediaSlots(title = "Top Rated", onSeeAllClick = onSeeAllClick) {
                    MediaItemsPosterRow(movies = state.movies.topRated, onMediaItemClick = onMediaItemClick)
                }
                MediaSlots(title = "Popular", onSeeAllClick = onSeeAllClick) {
                    MediaItemsPosterRow(movies = state.movies.popular, onMediaItemClick = onMediaItemClick)
                }
                MediaSlots(title = "Upcoming", onSeeAllClick = onSeeAllClick) {
                    MediaItemsPosterRow(movies = state.movies.upcoming, onMediaItemClick = onMediaItemClick)
                }
            }
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


