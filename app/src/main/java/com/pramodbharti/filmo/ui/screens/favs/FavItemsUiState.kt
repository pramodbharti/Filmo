package com.pramodbharti.filmo.ui.screens.favs

import com.pramodbharti.filmo.ui.models.MediaItem
import com.pramodbharti.filmo.ui.models.Movies

sealed interface FavItemsUiState {
    data class Success(val medias: List<MediaItem>) : FavItemsUiState
    data class Error(val msg: String) : FavItemsUiState
    data object Loading : FavItemsUiState
}