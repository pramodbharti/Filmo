package com.pramodbharti.filmo.ui.models

import com.pramodbharti.filmo.dummydata.dummyMovies

data class Movies(
    val trending: List<MediaItem> = emptyList(),
    val nowPlaying: List<MediaItem> = emptyList(),
    val popular: List<MediaItem> = emptyList(),
    val topRated: List<MediaItem> = emptyList(),
    val upcoming: List<MediaItem> = emptyList()
)
