package com.pramodbharti.filmo.data.repositories

import com.pramodbharti.filmo.data.network.MoviesApiService
import com.pramodbharti.filmo.data.network.TvShowsApiService
import com.pramodbharti.filmo.data.network.models.CreditsResponse
import com.pramodbharti.filmo.data.network.models.MediaResponse
import com.pramodbharti.filmo.data.network.models.TvShowResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class NetworkTvShowsRepository(
    private val tvShowsApiService: TvShowsApiService,
    private val dispatcher: CoroutineDispatcher
) : TvShowsRepository {
    override suspend fun getTrendingShows(): MediaResponse<TvShowResponse> {
        return withContext(dispatcher) {
            tvShowsApiService.getTrendingTvShows()
        }
    }

    override suspend fun getAiringToday(): MediaResponse<TvShowResponse> {
        return withContext(dispatcher) {
            tvShowsApiService.getAiringToday()
        }
    }

    override suspend fun getPopularTvShows(): MediaResponse<TvShowResponse> {
        return withContext(dispatcher) {
            tvShowsApiService.getPopularTvShows()
        }
    }

    override suspend fun getOnTheAirShows(): MediaResponse<TvShowResponse> {
        return withContext(dispatcher) {
            tvShowsApiService.getOnTheAirShows()
        }
    }

    override suspend fun getTopRatedTvShows(): MediaResponse<TvShowResponse> {
        return withContext(dispatcher) {
            tvShowsApiService.getTopRatedTvShows()
        }
    }

    override suspend fun getTvShowDetails(showId: Int): TvShowResponse {
        return withContext(dispatcher) {
            tvShowsApiService.getTvShowDetails(showId)
        }
    }

    override suspend fun getRecommendedTvShows(showId: Int): MediaResponse<TvShowResponse> {
        return withContext(dispatcher) {
            tvShowsApiService.getRecommendedTvShows(showId)
        }
    }

    override suspend fun getSimilarTvShows(showId: Int): MediaResponse<TvShowResponse> {
        return withContext(dispatcher) {
            tvShowsApiService.getSimilarTvShows(showId)
        }
    }

    override suspend fun getAllCast(showId: Int): CreditsResponse {
        return withContext(dispatcher) {
            tvShowsApiService.getCredits(showId)
        }
    }

}