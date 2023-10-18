package com.pramodbharti.filmo.data.repositories

import com.pramodbharti.filmo.data.network.models.CreditsResponse
import com.pramodbharti.filmo.data.network.models.MovieResponse
import com.pramodbharti.filmo.data.network.models.MoviesResponse

interface MoviesRepository {
    suspend fun getTrendingMovies(): MoviesResponse
    suspend fun getNowPlayingMovies(): MoviesResponse
    suspend fun getPopularMovies(): MoviesResponse
    suspend fun getUpcomingMovies(): MoviesResponse
    suspend fun getTopRatedMovies(): MoviesResponse
    suspend fun getMovieDetails(movieId: Int): MovieResponse
    suspend fun getRecommendedMovies(movieId: Int):MoviesResponse
    suspend fun getSimilarMovies(movieId: Int):MoviesResponse
    suspend fun getAllCast(movieId: Int):CreditsResponse
}