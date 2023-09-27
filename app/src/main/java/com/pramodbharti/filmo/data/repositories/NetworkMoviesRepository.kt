package com.pramodbharti.filmo.data.repositories

import com.pramodbharti.filmo.data.network.MoviesApiService

class NetworkMoviesRepository(private val moviesApiService: MoviesApiService) : MoviesRepository {
    override suspend fun getDiscoverMovies() = moviesApiService.getDiscoverMovies()
}