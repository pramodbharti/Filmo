package com.pramodbharti.filmo.domain

import com.pramodbharti.filmo.data.network.models.CastResponse
import com.pramodbharti.filmo.data.network.models.TvShowResponse
import com.pramodbharti.filmo.data.repositories.TvShowsRepository
import com.pramodbharti.filmo.ui.models.Cast
import com.pramodbharti.filmo.ui.models.MediaDetails
import com.pramodbharti.filmo.ui.models.MediaItem
import com.pramodbharti.filmo.ui.models.TvShows
import com.pramodbharti.filmo.ui.models.getGenreList
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class TvShowsUseCase(private val tvShowsRepository: TvShowsRepository) {
    suspend fun getTvShows(): TvShows {
        return coroutineScope {
            val trendingTvShows =
                async {
                    tvShowsRepository.getTrendingShows().results.take(7).map { it.toMediaItem() }
                }
            val popularTvShows =
                async { tvShowsRepository.getPopularTvShows().results.map { it.toMediaItem() } }
            val airingTodayTvShows =
                async { tvShowsRepository.getAiringToday().results.map { it.toMediaItem() } }
            val topRatedTvShows =
                async { tvShowsRepository.getTopRatedTvShows().results.map { it.toMediaItem() } }
            val onTheAirTvShows =
                async { tvShowsRepository.getOnTheAirShows().results.map { it.toMediaItem() } }
            TvShows(
                trendingTvShows = trendingTvShows.await(),
                airingTodayTvShows = airingTodayTvShows.await(),
                onTheAirTvShows = onTheAirTvShows.await(),
                popularTvShows = popularTvShows.await(),
                topRatedTvShows = topRatedTvShows.await()
            )
        }
    }

    suspend fun getTvShowDetails(showId: Int): MediaDetails {
        return coroutineScope {
            val media = async { tvShowsRepository.getTvShowDetails(showId).toMediaItem() }
            val casts = async { tvShowsRepository.getAllCast(showId).cast.map { it.toCast() } }
            val similar =
                async { tvShowsRepository.getSimilarTvShows(showId).results.map { it.toMediaItem() } }
            val recommended =
                async { tvShowsRepository.getRecommendedTvShows(showId).results.map { it.toMediaItem() } }

            MediaDetails(media.await(), casts.await(), similar.await(), recommended.await())
        }
    }

    private fun TvShowResponse.toMediaItem(): MediaItem =
        MediaItem(
            id = id,
            title = originalTitle,
            poster = posterPath ?: "",
            backdrop = backdropPath ?: "",
            overview = overview,
            releaseDate = firstAirDate,
            mediaType = "tv",
            genres = genres?.map { it.name }?.toList() ?: genreIds?.let { getGenreList(it) }
        )

    private fun CastResponse.toCast(): Cast =
        Cast(
            name = name,
            photo = profilePath ?: ""
        )
}