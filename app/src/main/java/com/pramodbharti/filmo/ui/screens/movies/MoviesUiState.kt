package com.pramodbharti.filmo.ui.screens.movies

import com.pramodbharti.filmo.ui.models.MediaItem

sealed interface MoviesUiState {
    data class Success(val movies: List<MediaItem>): MoviesUiState
    object Error : MoviesUiState
    object Loading : MoviesUiState
}