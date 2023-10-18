package com.pramodbharti.filmo.domain

import com.pramodbharti.filmo.R
import com.pramodbharti.filmo.data.network.models.CastResponse
import com.pramodbharti.filmo.data.network.models.MovieResponse
import com.pramodbharti.filmo.data.repositories.MoviesRepository
import com.pramodbharti.filmo.ui.models.Cast
import com.pramodbharti.filmo.ui.models.MediaItem
import com.pramodbharti.filmo.ui.models.MovieDetails
import com.pramodbharti.filmo.ui.models.Movies
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class MoviesUseCase(private val moviesRepository: MoviesRepository) {
    suspend fun getMovies(): Movies {
        return coroutineScope {
            val trendingMovies =
                async {
                    moviesRepository.getTrendingMovies().results.take(7).map { it.toMovieItem() }
                }
            val popularMovies =
                async { moviesRepository.getPopularMovies().results.map { it.toMovieItem() } }
            val nowPlaying =
                async { moviesRepository.getNowPlayingMovies().results.map { it.toMovieItem() } }
            val topRatedMovies =
                async { moviesRepository.getTopRatedMovies().results.map { it.toMovieItem() } }
            val upcomingMovies =
                async { moviesRepository.getUpcomingMovies().results.map { it.toMovieItem() } }
            Movies(
                trendingMovies.await(),
                nowPlaying.await(),
                popularMovies.await(),
                topRatedMovies.await(),
                upcomingMovies.await()
            )
        }
    }

    suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return coroutineScope {
            val movie = async { moviesRepository.getMovieDetails(movieId).toMovieItem() }
            val casts = async { moviesRepository.getAllCast(movieId).cast.map { it.toCast()  } }
            val similar =
                async { moviesRepository.getSimilarMovies(movieId).results.map { it.toMovieItem() } }
            val recommended =
                async { moviesRepository.getRecommendedMovies(movieId).results.map { it.toMovieItem() } }

            MovieDetails(movie.await(), casts.await(), similar.await(), recommended.await())
        }
    }
}

private fun MovieResponse.toMovieItem(): MediaItem =
    MediaItem(
        id = id,
        title = title,
        poster = posterPath ?: "",
        backdrop = backdropPath ?: "",
        overview = overview,
        releaseDate = releaseDate
    )

private fun CastResponse.toCast(): Cast =
    Cast(
        name = name,
        photo = profilePath?:""
    )