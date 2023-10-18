package com.pramodbharti.filmo.ui.screens.details

import com.pramodbharti.filmo.ui.models.MovieDetails
import com.pramodbharti.filmo.ui.models.Movies

sealed interface MovieDetailUiState {
    data class Success(val movies: MovieDetails) : MovieDetailUiState
    data class Error(val msg: String) : MovieDetailUiState
    data object Loading : MovieDetailUiState
}