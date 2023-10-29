package com.pramodbharti.filmo.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvShowResponse(
    val id: Int,
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_name")
    val originalTitle: String,
    val overview: String,
    val popularity: String,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("first_air_date")
    val firstAirDate: String,
    @SerialName("vote_average")
    val voteAverage: Float = 0.0F,
    @SerialName("genre_ids")
    val genreIds: List<Int>? = null,
    val genres: List<Genre>? = null
)
