package com.pramodbharti.filmo.ui.home

sealed interface MovieUiState {
    data class Success(val moviesCount: Int):MovieUiState
    object Error : MovieUiState
    object Loading : MovieUiState
}