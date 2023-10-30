package com.pramodbharti.filmo.ui.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pramodbharti.filmo.FilmoApplication
import com.pramodbharti.filmo.domain.MoviesUseCase
import com.pramodbharti.filmo.domain.TvShowsUseCase
import com.pramodbharti.filmo.ui.navigation.Details
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class MediaDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val moviesUseCase: MoviesUseCase,
    private val tvShowsUseCase: TvShowsUseCase
) : ViewModel() {
    private val _mediaDetailsUiState =
        MutableStateFlow<MediaDetailUiState>(MediaDetailUiState.Loading)
    val mediaDetailsUiState: StateFlow<MediaDetailUiState> = _mediaDetailsUiState.asStateFlow()

    init {
        val mediaId = savedStateHandle.get<Int>(Details.mediaId)
        val mediaType = checkNotNull(savedStateHandle.get<String>(Details.mediaType))
        mediaId?.let {
            getMovieDetails(mediaId, mediaType)
        }
    }

    private fun getMovieDetails(mediaId: Int, mediaType: String) {
        viewModelScope.launch {
            try {
                val mediaDetails =
                    if (mediaType == "movie") moviesUseCase.getMovieDetails(mediaId) else tvShowsUseCase.getTvShowDetails(mediaId)
                _mediaDetailsUiState.value = MediaDetailUiState.Success(mediaDetails)
            } catch (e: IOException) {
                _mediaDetailsUiState.value =
                    MediaDetailUiState.Error("Something went wrong! ${e.localizedMessage}")
            }

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FilmoApplication)
                val moviesUseCase = application.container.moviesUseCase
                val tvShowsUseCase = application.container.tvShowsUseCase
                MediaDetailsViewModel(
                    createSavedStateHandle(),
                    moviesUseCase = moviesUseCase,
                    tvShowsUseCase = tvShowsUseCase
                )
            }
        }
    }
}