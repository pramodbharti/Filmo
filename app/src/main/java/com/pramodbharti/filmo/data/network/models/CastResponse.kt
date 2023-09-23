package com.pramodbharti.filmo.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CastResponse(
    val id: Int,
    val name: String,
    val gender: Int,
    @SerialName("profile_path")
    val profilePath: String,
    val character: String
)
