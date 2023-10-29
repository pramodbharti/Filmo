package com.pramodbharti.filmo.ui.screens.favs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pramodbharti.filmo.FilmoApplication
import com.pramodbharti.filmo.ui.screens.movies.MoviesUiState
import com.pramodbharti.filmo.ui.screens.movies.MoviesViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavItemsViewModel() : ViewModel() {
    private val _favUiState = MutableStateFlow<FavItemsUiState>(FavItemsUiState.Loading)
    val favUiState: StateFlow<FavItemsUiState> = _favUiState.asStateFlow()

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FilmoApplication)
                val moviesUseCase = application.container.moviesUseCase
                MoviesViewModel(moviesUseCase)
            }
        }
    }
}