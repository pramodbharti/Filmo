package com.pramodbharti.filmo.ui.models

data class MovieDetails(
    val movie: MediaItem,
    val casts: List<Cast>,
    val similarMovies: List<MediaItem>,
    val recommendedMovies: List<MediaItem>
)
