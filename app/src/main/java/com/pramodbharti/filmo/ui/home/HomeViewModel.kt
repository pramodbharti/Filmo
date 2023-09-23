package com.pramodbharti.filmo.ui.home

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
import com.pramodbharti.filmo.data.repositories.MoviesRepository
import kotlinx.coroutines.launch
import java.io.IOException

class HomeViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    var movieUiState: MovieUiState by mutableStateOf(MovieUiState.Loading)
        private set

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            movieUiState = try {
                val movieList = moviesRepository.getDiscoverMovies()
                MovieUiState.Success(movieList.results.size)
            } catch (e: IOException) {
                MovieUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FilmoApplication)
                val moviesRepository = application.container.moviesRepository
                HomeViewModel(moviesRepository)
            }
        }
    }
}

