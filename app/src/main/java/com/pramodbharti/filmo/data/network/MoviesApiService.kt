package com.pramodbharti.filmo.data.network

import com.pramodbharti.filmo.data.DISCOVER_MOVIES
import com.pramodbharti.filmo.data.network.models.MoviesResponse
import retrofit2.http.GET

interface MoviesApiService {
    @GET(DISCOVER_MOVIES)
    suspend fun getDiscoverMovies(): MoviesResponse
}