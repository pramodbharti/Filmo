package com.pramodbharti.filmo.ui.screens.movies

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pramodbharti.filmo.dummydata.dummyMovies
import com.pramodbharti.filmo.ui.components.CarouselItem
import com.pramodbharti.filmo.ui.components.FilmoCarousel
import com.pramodbharti.filmo.ui.components.MediaItemsPosterRow
import com.pramodbharti.filmo.ui.components.MediaSlots
import com.pramodbharti.filmo.ui.components.ShimmerLoadingMainScreen
import com.pramodbharti.filmo.ui.components.carouselTransition
import com.pramodbharti.filmo.ui.models.MediaItem
import com.pramodbharti.filmo.ui.models.Movies
import com.pramodbharti.filmo.ui.theme.FilmoTheme


@Composable
fun MoviesScreen(
    onSeeAllClick: (String) -> Unit,
    onMediaItemClick: (MediaItem) -> Unit,
    viewModel: MoviesViewModel = viewModel(factory = MoviesViewModel.Factory)
) {
    val uiState by viewModel.moviesUiState.collectAsStateWithLifecycle()
    MoviesScreen(
        uiState = uiState,
        onMediaItemClick = onMediaItemClick,
        onSeeAllClick = onSeeAllClick
    )

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoviesScreen(
    uiState: MoviesUiState,
    modifier: Modifier = Modifier,
    onMediaItemClick: (MediaItem) -> Unit = {},
    onSeeAllClick: (String) -> Unit = {}
) {

    when (uiState) {
        is MoviesUiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = uiState.msg)
            }
        }

        is MoviesUiState.Loading -> {
            ShimmerLoadingMainScreen()
        }

        is MoviesUiState.Success -> {
            Column(modifier.verticalScroll(rememberScrollState())) {
                FilmoCarousel(
                    itemsCount = uiState.movies.trending.size,
                    indicatorPadding = PaddingValues(0.dp),
                    indicatorSize = 8.dp,
                    indicatorShape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 4.dp,
                        bottomEnd = 0.dp,
                        bottomStart = 4.dp
                    )
                ) { index, pagerState ->
                    CarouselItem(
                        mediaItem = uiState.movies.trending[index],
                        modifier = modifier.carouselTransition(index, pagerState),
                        onMediaItemClick = onMediaItemClick
                    )
                }
                AnimatedVisibility(visible = uiState.movies.nowPlaying.isNotEmpty()) {
                    MediaSlots(title = "Now Playing", onSeeAllClick = onSeeAllClick) {
                        MediaItemsPosterRow(
                            movies = uiState.movies.nowPlaying,
                            onMediaItemClick = onMediaItemClick
                        )
                    }
                }

                AnimatedVisibility(visible = uiState.movies.topRated.isNotEmpty()) {
                    MediaSlots(title = "Top Rated", onSeeAllClick = onSeeAllClick) {
                        MediaItemsPosterRow(
                            movies = uiState.movies.topRated,
                            onMediaItemClick = onMediaItemClick
                        )
                    }
                }

                AnimatedVisibility(visible = uiState.movies.popular.isNotEmpty()) {
                    MediaSlots(title = "Popular", onSeeAllClick = onSeeAllClick) {
                        MediaItemsPosterRow(
                            movies = uiState.movies.popular,
                            onMediaItemClick = onMediaItemClick
                        )
                    }
                }

                AnimatedVisibility(visible = uiState.movies.upcoming.isNotEmpty()) {
                    MediaSlots(title = "Upcoming", onSeeAllClick = onSeeAllClick) {
                        MediaItemsPosterRow(
                            movies = uiState.movies.upcoming,
                            onMediaItemClick = onMediaItemClick
                        )
                    }
                }
            }
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_YES,
    name = "SuccessDark"
)
@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_NO,
    name = "SuccessLight"
)

@Composable
fun MoviesScreenSuccessPreview() {
    FilmoTheme {
        Surface(tonalElevation = 5.dp) {
            MoviesScreen(
                uiState = MoviesUiState.Success(
                    Movies(
                        dummyMovies,
                        dummyMovies,
                        dummyMovies
                    )
                )
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES, name = "Loading")
@Composable
fun MoviesScreenLoadingPreview() {
    FilmoTheme {
        MoviesScreen(uiState = MoviesUiState.Loading)
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES, name = "Error")
@Composable
fun MoviesScreenErrorPreview() {
    FilmoTheme {
        MoviesScreen(uiState = MoviesUiState.Error("Something went wrong!"))
    }
}


