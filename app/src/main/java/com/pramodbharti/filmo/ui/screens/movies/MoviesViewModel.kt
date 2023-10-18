package com.pramodbharti.filmo.ui.screens.movies

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pramodbharti.filmo.FilmoApplication
import com.pramodbharti.filmo.R
import com.pramodbharti.filmo.data.network.models.MovieResponse
import com.pramodbharti.filmo.data.repositories.MoviesRepository
import com.pramodbharti.filmo.domain.MoviesUseCase
import com.pramodbharti.filmo.ui.models.MediaItem
import com.pramodbharti.filmo.ui.models.Movies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException

class MoviesViewModel(private val moviesUseCase: MoviesUseCase) : ViewModel() {
    private val _moviesUiState = MutableStateFlow<MoviesUiState>(MoviesUiState.Loading)
    val moviesUiState: StateFlow<MoviesUiState> = _moviesUiState
    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            try {
                val movies = moviesUseCase.getMovies()
                _moviesUiState.value = MoviesUiState.Success(movies)
            } catch (e: IOException) {
                _moviesUiState.value =
                    MoviesUiState.Error("Something went wrong! ${e.localizedMessage}")
            }

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FilmoApplication)
                val moviesUseCase = application.container.moviesUseCase
                MoviesViewModel(moviesUseCase)
            }
        }
    }


}

