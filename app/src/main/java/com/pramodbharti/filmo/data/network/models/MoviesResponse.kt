package com.pramodbharti.filmo.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    val results:List<MovieResponse>
)
