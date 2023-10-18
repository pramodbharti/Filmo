package com.pramodbharti.filmo.data.repositories

import com.pramodbharti.filmo.data.network.MoviesApiService
import com.pramodbharti.filmo.data.network.models.MovieResponse
import com.pramodbharti.filmo.data.network.models.MoviesResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkMoviesRepository(private val moviesApiService: MoviesApiService, private val dispatcher: CoroutineDispatcher) : MoviesRepository {
    override suspend fun getTrendingMovies(): MoviesResponse {
        return withContext(dispatcher) {
            moviesApiService.getTrendingMovies()
        }
    }

    override suspend fun getNowPlayingMovies(): MoviesResponse {
        return withContext(dispatcher) {
            moviesApiService.getNowPlayingMovies()
        }
    }

    override suspend fun getPopularMovies(): MoviesResponse {
        return withContext(dispatcher) {
            moviesApiService.getPopularMovies()
        }
    }

    override suspend fun getUpcomingMovies(): MoviesResponse {
        return withContext(dispatcher) {
            moviesApiService.getUpcomingMovies()
        }
    }

    override suspend fun getTopRatedMovies(): MoviesResponse {
        return withContext(dispatcher) {
            moviesApiService.getTopRatedMovies()
        }
    }

    override suspend fun getMovieDetails(movieId: Int): MovieResponse {
        return withContext(dispatcher) {
            moviesApiService.getMovieDetails(movieId)
        }
    }

}