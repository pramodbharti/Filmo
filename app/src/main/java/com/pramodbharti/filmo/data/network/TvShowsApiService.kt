package com.pramodbharti.filmo.data.network

import com.pramodbharti.filmo.data.network.models.CreditsResponse
import com.pramodbharti.filmo.data.network.models.MediaResponse
import com.pramodbharti.filmo.data.network.models.MovieResponse
import com.pramodbharti.filmo.data.network.models.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowsApiService {
    @GET(TRENDING_TV_SHOWS)
    suspend fun getTrendingTvShows(@Query("page") page: Int = 1): MediaResponse<TvShowResponse>

    @GET(AIRING_TODAY_TV_SHOWS)
    suspend fun getAiringToday(@Query("page") page: Int = 1): MediaResponse<TvShowResponse>

    @GET(POPULAR_TV_SHOWS)
    suspend fun getPopularTvShows(@Query("page") page: Int = 1): MediaResponse<TvShowResponse>

    @GET(TOP_RATED_TV_SHOWS)
    suspend fun getTopRatedTvShows(@Query("page") page: Int = 1): MediaResponse<TvShowResponse>

    @GET(ON_THE_AIR_TV_SHOWS)
    suspend fun getOnTheAirShows(@Query("page") page: Int = 1): MediaResponse<TvShowResponse>

    @GET(RECOMMENDED_TV_SHOWS)
    suspend fun getRecommendedTvShows(
        @Path("series_id") showId: Int,
        @Query("page") page: Int = 1
    ): MediaResponse<TvShowResponse>

    @GET(SIMILAR_TV_SHOWS)
    suspend fun getSimilarTvShows(
        @Path("series_id") showId: Int,
        @Query("page") page: Int = 1
    ): MediaResponse<TvShowResponse>

    @GET(TV_SHOW_DETAILS)
    suspend fun getTvShowDetails(@Path("series_id") showId: Int): TvShowResponse

    @GET(CREDITS_CAST_TV)
    suspend fun getCredits(@Path("series_id") showId: Int): CreditsResponse

    companion object {
        const val TRENDING_TV_SHOWS = "trending/tv/day"
        const val AIRING_TODAY_TV_SHOWS = "tv/airing_today"
        const val POPULAR_TV_SHOWS = "tv/popular"
        const val TOP_RATED_TV_SHOWS = "tv/top_rated"
        const val ON_THE_AIR_TV_SHOWS = "tv/on_the_air"
        const val RECOMMENDED_TV_SHOWS = "tv/{series_id}/recommendations"
        const val SIMILAR_TV_SHOWS = "tv/{series_id}/similar"
        const val TV_SHOW_DETAILS = "tv/{series_id}"
        const val CREDITS_CAST_TV = "tv/{series_id}/credits"
    }
}