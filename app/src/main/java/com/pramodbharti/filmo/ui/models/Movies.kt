package com.pramodbharti.filmo.ui.models

data class Movies(
    val trending: List<MediaItem>,
    val nowPlaying: List<MediaItem>,
    val popular: List<MediaItem>,
    val topRated: List<MediaItem>,
    val upcoming: List<MediaItem>
)
