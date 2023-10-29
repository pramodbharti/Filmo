package com.pramodbharti.filmo.ui.models

data class MediaDetails(
    val movie: MediaItem,
    val casts: List<Cast> = emptyList(),
    val similarMovies: List<MediaItem> = emptyList(),
    val recommendedMovies: List<MediaItem> = emptyList()
)
