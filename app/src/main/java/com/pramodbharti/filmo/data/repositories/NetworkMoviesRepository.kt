package com.pramodbharti.filmo.data.repositories

import com.pramodbharti.filmo.data.network.MoviesApiService
import com.pramodbharti.filmo.data.network.models.CreditsResponse
import com.pramodbharti.filmo.data.network.models.MediaResponse
import com.pramodbharti.filmo.data.network.models.MovieResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class NetworkMoviesRepository(
    private val moviesApiService: MoviesApiService,
    private val dispatcher: CoroutineDispatcher
) : MoviesRepository {
    override suspend fun getTrendingMovies(): MediaResponse<MovieResponse> {
        return withContext(dispatcher) {
            moviesApiService.getTrendingMovies()
        }
    }

    override suspend fun getNowPlayingMovies(): MediaResponse<MovieResponse> {
        return withContext(dispatcher) {
            moviesApiService.getNowPlayingMovies()
        }
    }

    override suspend fun getPopularMovies(): MediaResponse<MovieResponse> {
        return withContext(dispatcher) {
            moviesApiService.getPopularMovies()
        }
    }

    override suspend fun getUpcomingMovies(): MediaResponse<MovieResponse> {
        return withContext(dispatcher) {
            moviesApiService.getUpcomingMovies()
        }
    }

    override suspend fun getTopRatedMovies(): MediaResponse<MovieResponse> {
        return withContext(dispatcher) {
            moviesApiService.getTopRatedMovies()
        }
    }

    override suspend fun getMovieDetails(movieId: Int): MovieResponse {
        return withContext(dispatcher) {
            moviesApiService.getMovieDetails(movieId)
        }
    }

    override suspend fun getRecommendedMovies(movieId: Int): MediaResponse<MovieResponse> {
        return withContext(dispatcher) {
            moviesApiService.getRecommendedMovies(movieId)
        }
    }

    override suspend fun getSimilarMovies(movieId: Int): MediaResponse<MovieResponse> {
        return withContext(dispatcher) {
            moviesApiService.getSimilarMovies(movieId)
        }
    }

    override suspend fun getAllCast(movieId: Int): CreditsResponse {
        return withContext(dispatcher) {
            moviesApiService.getCredits(movieId)
        }
    }

}