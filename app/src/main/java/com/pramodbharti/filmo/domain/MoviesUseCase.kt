package com.pramodbharti.filmo.domain

import com.pramodbharti.filmo.data.network.models.CastResponse
import com.pramodbharti.filmo.data.network.models.MovieResponse
import com.pramodbharti.filmo.data.repositories.MoviesRepository
import com.pramodbharti.filmo.ui.models.Cast
import com.pramodbharti.filmo.ui.models.MediaItem
import com.pramodbharti.filmo.ui.models.MediaDetails
import com.pramodbharti.filmo.ui.models.Movies
import com.pramodbharti.filmo.ui.models.getGenreList
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class MoviesUseCase(private val moviesRepository: MoviesRepository) {
    suspend fun getMovies(): Movies {
        return coroutineScope {
            val trendingMovies =
                async {
                    moviesRepository.getTrendingMovies().results.take(7).map { it.toMediaItem() }
                }
            val popularMovies =
                async { moviesRepository.getPopularMovies().results.map { it.toMediaItem() } }
            val nowPlaying =
                async { moviesRepository.getNowPlayingMovies().results.map { it.toMediaItem() } }
            val topRatedMovies =
                async { moviesRepository.getTopRatedMovies().results.map { it.toMediaItem() } }
            val upcomingMovies =
                async { moviesRepository.getUpcomingMovies().results.map { it.toMediaItem() } }
            Movies(
                trendingMovies.await(),
                nowPlaying.await(),
                popularMovies.await(),
                topRatedMovies.await(),
                upcomingMovies.await()
            )
        }
    }

    suspend fun getMovieDetails(movieId: Int): MediaDetails {
        return coroutineScope {
            val movie = async { moviesRepository.getMovieDetails(movieId).toMediaItem() }
            val casts = async { moviesRepository.getAllCast(movieId).cast.map { it.toCast() } }
            val similar =
                async { moviesRepository.getSimilarMovies(movieId).results.map { it.toMediaItem() } }
            val recommended =
                async { moviesRepository.getRecommendedMovies(movieId).results.map { it.toMediaItem() } }

            MediaDetails(movie.await(), casts.await(), similar.await(), recommended.await())
        }
    }
}

private fun MovieResponse.toMediaItem(): MediaItem =
    MediaItem(
        id = id,
        title = title,
        poster = posterPath ?: "",
        backdrop = backdropPath ?: "",
        overview = overview,
        releaseDate = releaseDate,
        mediaType = "movie",
        genres = genres?.map { it.name }?.toList() ?: genreIds?.let { getGenreList(it) }
    )

private fun CastResponse.toCast(): Cast =
    Cast(
        name = name,
        photo = profilePath ?: ""
    )