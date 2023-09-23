package com.pramodbharti.filmo.data.repositories

import com.pramodbharti.filmo.data.network.models.MoviesResponse

interface MoviesRepository {
    suspend fun getDiscoverMovies(): MoviesResponse
}