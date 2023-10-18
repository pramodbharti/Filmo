package com.pramodbharti.filmo.ui.screens.details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pramodbharti.filmo.FilmoApplication
import com.pramodbharti.filmo.domain.MoviesUseCase
import com.pramodbharti.filmo.ui.navigation.Details
import com.pramodbharti.filmo.ui.screens.movies.MoviesUiState
import com.pramodbharti.filmo.ui.screens.movies.MoviesViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class MovieDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val moviesUseCase: MoviesUseCase
) : ViewModel() {
    private val _movieDetailsUiState =
        MutableStateFlow<MovieDetailUiState>(MovieDetailUiState.Loading)
    val movieDetailsUiState: StateFlow<MovieDetailUiState> = _movieDetailsUiState

    init {
        val movieId = savedStateHandle.get<Int>(Details.movieId)
        Log.e("TAG", ": movieIddd: $movieId")
        movieId?.let {
            getMovieDetails(movieId)
        }
    }

    private fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                val movieDetail = moviesUseCase.getMovieDetails(movieId)
                _movieDetailsUiState.value = MovieDetailUiState.Success(movieDetail)
            } catch (e: IOException) {
                _movieDetailsUiState.value =
                    MovieDetailUiState.Error("Something went wrong! ${e.localizedMessage}")
            }

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FilmoApplication)
                val moviesUseCase = application.container.moviesUseCase
                MovieDetailsViewModel(createSavedStateHandle(), moviesUseCase = moviesUseCase)
            }
        }
    }
}