package com.pramodbharti.filmo.ui.screens.tvshows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pramodbharti.filmo.FilmoApplication
import com.pramodbharti.filmo.domain.MoviesUseCase
import com.pramodbharti.filmo.domain.TvShowsUseCase
import com.pramodbharti.filmo.ui.screens.movies.MoviesUiState
import com.pramodbharti.filmo.ui.screens.movies.MoviesViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class TvShowsViewModel(private val tvShowsUseCase: TvShowsUseCase) : ViewModel() {
    private val _tvShowsUiState = MutableStateFlow<TvShowsUiState>(TvShowsUiState.Loading)
    val tvShowsUiState: StateFlow<TvShowsUiState> = _tvShowsUiState.asStateFlow()

    init {
        getTvShows()
    }

    private fun getTvShows() {
        viewModelScope.launch {
            try {
                val tvShows = tvShowsUseCase.getTvShows()
                _tvShowsUiState.value = TvShowsUiState.Success(tvShows)
            } catch (e: IOException) {
                _tvShowsUiState.value =
                    TvShowsUiState.Error("Something went wrong! ${e.localizedMessage}")
            }

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FilmoApplication)
                val tvShowsUseCase = application.container.tvShowsUseCase
                TvShowsViewModel(tvShowsUseCase)
            }
        }
    }
}