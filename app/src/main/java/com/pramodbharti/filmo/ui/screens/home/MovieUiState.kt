package com.pramodbharti.filmo.ui.screens.home

import com.pramodbharti.filmo.ui.models.MovieItem

sealed interface MovieUiState {
    data class Success(val movies: List<MovieItem>): MovieUiState
    object Error : MovieUiState
    object Loading : MovieUiState
}