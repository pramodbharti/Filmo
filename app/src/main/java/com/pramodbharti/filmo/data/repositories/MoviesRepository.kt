package com.pramodbharti.filmo.data.repositories

import com.pramodbharti.filmo.data.network.models.CreditsResponse
import com.pramodbharti.filmo.data.network.models.MediaResponse
import com.pramodbharti.filmo.data.network.models.MovieResponse

interface MoviesRepository {
    suspend fun getTrendingMovies(): MediaResponse<MovieResponse>
    suspend fun getNowPlayingMovies(): MediaResponse<MovieResponse>
    suspend fun getPopularMovies(): MediaResponse<MovieResponse>
    suspend fun getUpcomingMovies(): MediaResponse<MovieResponse>
    suspend fun getTopRatedMovies(): MediaResponse<MovieResponse>
    suspend fun getMovieDetails(movieId: Int): MovieResponse
    suspend fun getRecommendedMovies(movieId: Int):MediaResponse<MovieResponse>
    suspend fun getSimilarMovies(movieId: Int):MediaResponse<MovieResponse>
    suspend fun getAllCast(movieId: Int):CreditsResponse
}