package com.pramodbharti.filmo.ui.screens.tvshows

import com.pramodbharti.filmo.ui.models.Movies
import com.pramodbharti.filmo.ui.models.TvShows
import com.pramodbharti.filmo.ui.screens.movies.MoviesUiState

sealed interface TvShowsUiState{
    data class Success(val tvShows: TvShows) : TvShowsUiState
    data class Error(val msg: String) : TvShowsUiState
    data object Loading : TvShowsUiState
}