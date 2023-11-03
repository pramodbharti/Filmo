package com.pramodbharti.filmo.ui.screens.tvshows

import android.content.res.Configuration
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
import com.pramodbharti.filmo.ui.components.ErrorScreen
import com.pramodbharti.filmo.ui.components.FilmoCarousel
import com.pramodbharti.filmo.ui.components.MediaItemsPosterRow
import com.pramodbharti.filmo.ui.components.MediaSlots
import com.pramodbharti.filmo.ui.components.ShimmerLoadingMainScreen
import com.pramodbharti.filmo.ui.components.carouselTransition
import com.pramodbharti.filmo.ui.models.MediaItem
import com.pramodbharti.filmo.ui.models.TvShows
import com.pramodbharti.filmo.ui.theme.FilmoTheme

@Composable
fun TvShowsScreen(
    onSeeAllClick: (String) -> Unit,
    onMediaItemClick: (MediaItem) -> Unit,
    viewModel: TvShowsViewModel = viewModel(factory = TvShowsViewModel.Factory)
) {
    val uiState by viewModel.tvShowsUiState.collectAsStateWithLifecycle()
    TvShowsScreen(
        uiState = uiState,
        onMediaItemClick = onMediaItemClick,
        onSeeAllClick = onSeeAllClick
    )

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TvShowsScreen(
    uiState: TvShowsUiState,
    modifier: Modifier = Modifier,
    onMediaItemClick: (MediaItem) -> Unit = {},
    onSeeAllClick: (String) -> Unit = {}
) {

    when (uiState) {
        is TvShowsUiState.Error -> {
            ErrorScreen(msg = uiState.msg)
        }

        is TvShowsUiState.Loading -> {
            ShimmerLoadingMainScreen()
        }

        is TvShowsUiState.Success -> {
            Column(modifier.verticalScroll(rememberScrollState())) {
                FilmoCarousel(
                    itemsCount = uiState.tvShows.trendingTvShows.size,
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
                        mediaItem = uiState.tvShows.trendingTvShows[index],
                        modifier = modifier.carouselTransition(index, pagerState),
                        onMediaItemClick = onMediaItemClick
                    )
                }
                AnimatedVisibility(visible = uiState.tvShows.airingTodayTvShows.isNotEmpty()) {
                    MediaSlots(title = "Airing Today", onSeeAllClick = onSeeAllClick) {
                        MediaItemsPosterRow(
                            medias = uiState.tvShows.airingTodayTvShows,
                            onMediaItemClick = onMediaItemClick
                        )
                    }
                }

                AnimatedVisibility(visible = uiState.tvShows.onTheAirTvShows.isNotEmpty()) {
                    MediaSlots(title = "On The Air", onSeeAllClick = onSeeAllClick) {
                        MediaItemsPosterRow(
                            medias = uiState.tvShows.onTheAirTvShows,
                            onMediaItemClick = onMediaItemClick
                        )
                    }
                }

                AnimatedVisibility(visible = uiState.tvShows.popularTvShows.isNotEmpty()) {
                    MediaSlots(title = "Popular", onSeeAllClick = onSeeAllClick) {
                        MediaItemsPosterRow(
                            medias = uiState.tvShows.popularTvShows,
                            onMediaItemClick = onMediaItemClick
                        )
                    }
                }

                AnimatedVisibility(visible = uiState.tvShows.topRatedTvShows.isNotEmpty()) {
                    MediaSlots(title = "Top Rated", onSeeAllClick = onSeeAllClick) {
                        MediaItemsPosterRow(
                            medias = uiState.tvShows.topRatedTvShows,
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
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "SuccessDark"
)
@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "SuccessLight"
)

@Composable
fun MoviesScreenSuccessPreview() {
    FilmoTheme {
        Surface(tonalElevation = 5.dp) {
            TvShowsScreen(
                uiState = TvShowsUiState.Success(
                    TvShows(
                        dummyMovies,
                        dummyMovies,
                        dummyMovies
                    )
                )
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Loading"
)
@Composable
fun MoviesScreenLoadingPreview() {
    FilmoTheme {
        TvShowsScreen(uiState = TvShowsUiState.Loading)
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Error"
)
@Composable
fun MoviesScreenErrorPreview() {
    FilmoTheme {
        TvShowsScreen(uiState = TvShowsUiState.Error("Something went wrong!"))
    }
}