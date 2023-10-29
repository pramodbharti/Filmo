package com.pramodbharti.filmo.ui.screens.details

import com.pramodbharti.filmo.ui.models.MediaDetails

sealed interface MediaDetailUiState {
    data class Success(val movies: MediaDetails) : MediaDetailUiState
    data class Error(val msg: String) : MediaDetailUiState
    data object Loading : MediaDetailUiState
}