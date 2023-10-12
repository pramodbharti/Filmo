package com.pramodbharti.filmo.ui.models

data class TvShows(
    val trending: List<MediaItem>,
    val airingToday: List<MediaItem>,
    val onTheAir: List<MediaItem>,
    val popular: List<MediaItem>,
    val topRated: List<MediaItem>
)
