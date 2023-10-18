package com.pramodbharti.filmo.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val id: Int,
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: String,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
)
