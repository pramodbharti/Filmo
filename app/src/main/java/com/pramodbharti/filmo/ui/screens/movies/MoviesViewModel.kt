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
import com.pramodbharti.filmo.ui.models.MediaItem
import kotlinx.coroutines.launch
import java.io.IOException

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    var movieUiState: MoviesUiState by mutableStateOf(MoviesUiState.Loading)
        private set

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            movieUiState = try {
                val movieList = moviesRepository.getDiscoverMovies()
                MoviesUiState.Success(movieList.results.map { it.toMovieItem() })
            } catch (e: IOException) {
                MoviesUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FilmoApplication)
                val moviesRepository = application.container.moviesRepository
                MoviesViewModel(moviesRepository)
            }
        }
    }


    private fun MovieResponse.toMovieItem(): MediaItem =
        MediaItem(
            id = id,
            title = title,
            poster = R.drawable.poster1,
            backdrop = R.drawable.back_drop1,
            releaseDate = releaseDate
        )
}

