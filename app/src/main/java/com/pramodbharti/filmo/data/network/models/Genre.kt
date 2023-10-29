package com.pramodbharti.filmo.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val id: Int,
    val name: String
)
