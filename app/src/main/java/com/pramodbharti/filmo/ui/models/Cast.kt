package com.pramodbharti.filmo.ui.models

import androidx.annotation.DrawableRes

data class Cast(
    val name: String,
    @DrawableRes
    val photo: Int
)
