package com.pramodbharti.filmo.ui.models

data class TvShows(
    val trendingTvShows: List<MediaItem> = emptyList(),
    val airingTodayTvShows: List<MediaItem> = emptyList(),
    val onTheAirTvShows: List<MediaItem> = emptyList(),
    val popularTvShows: List<MediaItem> = emptyList(),
    val topRatedTvShows: List<MediaItem> = emptyList()
)
