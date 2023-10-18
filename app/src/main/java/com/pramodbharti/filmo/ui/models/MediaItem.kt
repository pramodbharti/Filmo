package com.pramodbharti.filmo.ui.models

import androidx.annotation.DrawableRes

data class MediaItem(
    val id: Int,
    val title: String,
    val poster: String,
    val backdrop: String,
    val overview: String,
    val releaseDate: String
)


