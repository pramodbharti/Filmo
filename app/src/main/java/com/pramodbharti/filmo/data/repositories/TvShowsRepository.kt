package com.pramodbharti.filmo.data.repositories

import com.pramodbharti.filmo.data.network.models.CreditsResponse
import com.pramodbharti.filmo.data.network.models.MediaResponse
import com.pramodbharti.filmo.data.network.models.TvShowResponse


interface TvShowsRepository {
    suspend fun getTrendingShows(): MediaResponse<TvShowResponse>
    suspend fun getAiringToday(): MediaResponse<TvShowResponse>
    suspend fun getPopularTvShows(): MediaResponse<TvShowResponse>
    suspend fun getOnTheAirShows(): MediaResponse<TvShowResponse>
    suspend fun getTopRatedTvShows(): MediaResponse<TvShowResponse>
    suspend fun getTvShowDetails(showId: Int): TvShowResponse
    suspend fun getRecommendedTvShows(showId: Int): MediaResponse<TvShowResponse>
    suspend fun getSimilarTvShows(showId: Int): MediaResponse<TvShowResponse>
    suspend fun getAllCast(showId: Int): CreditsResponse
}