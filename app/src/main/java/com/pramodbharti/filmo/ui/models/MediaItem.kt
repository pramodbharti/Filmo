package com.pramodbharti.filmo.ui.models

import androidx.annotation.DrawableRes

data class MediaItem(
    val id: Int,
    val title: String,
    @DrawableRes
    val poster: Int,
    @DrawableRes
    val backdrop: Int,
    val releaseDate: String
)


