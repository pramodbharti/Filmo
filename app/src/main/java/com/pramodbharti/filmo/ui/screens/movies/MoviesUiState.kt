package com.pramodbharti.filmo.ui.screens.movies

import com.pramodbharti.filmo.ui.models.MediaItem
import com.pramodbharti.filmo.ui.models.Movies

sealed interface MoviesUiState {
    data class Success(val movies: Movies): MoviesUiState
    data class Error(val msg:String) : MoviesUiState
    data object Loading : MoviesUiState
}