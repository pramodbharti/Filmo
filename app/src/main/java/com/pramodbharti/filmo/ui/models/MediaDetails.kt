package com.pramodbharti.filmo.ui.models

data class MediaDetails(
    val media: MediaItem,
    val casts: List<Cast> = emptyList(),
    val similarMedia: List<MediaItem> = emptyList(),
    val recommendedMedia: List<MediaItem> = emptyList()
)
