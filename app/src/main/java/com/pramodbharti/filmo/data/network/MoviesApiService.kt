package com.pramodbharti.filmo.data.network

import com.pramodbharti.filmo.data.network.models.CreditsResponse
import com.pramodbharti.filmo.data.network.models.MediaResponse
import com.pramodbharti.filmo.data.network.models.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {
    @GET(TRENDING_MOVIES)
    suspend fun getTrendingMovies(@Query("page") page: Int = 1): MediaResponse<MovieResponse>

    @GET(NOW_PLAYING_MOVIES)
    suspend fun getNowPlayingMovies(@Query("page") page: Int = 1): MediaResponse<MovieResponse>

    @GET(POPULAR_MOVIES)
    suspend fun getPopularMovies(@Query("page") page: Int = 1): MediaResponse<MovieResponse>

    @GET(TOP_RATED_MOVIES)
    suspend fun getTopRatedMovies(@Query("page") page: Int = 1): MediaResponse<MovieResponse>

    @GET(UPCOMING_MOVIES)
    suspend fun getUpcomingMovies(@Query("page") page: Int = 1): MediaResponse<MovieResponse>

    @GET(RECOMMENDED_MOVIES)
    suspend fun getRecommendedMovies(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int = 1
    ): MediaResponse<MovieResponse>

    @GET(SIMILAR_MOVIES)
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int = 1
    ): MediaResponse<MovieResponse>

    @GET(MOVIE_DETAILS)
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): MovieResponse

    @GET(CREDITS_CAST_MOVIE)
    suspend fun getCredits(@Path("movie_id") movieId: Int): CreditsResponse

    companion object {
        const val TRENDING_MOVIES = "trending/movie/day"
        const val NOW_PLAYING_MOVIES = "movie/now_playing"
        const val POPULAR_MOVIES = "movie/popular"
        const val TOP_RATED_MOVIES = "movie/top_rated"
        const val UPCOMING_MOVIES = "movie/upcoming"
        const val RECOMMENDED_MOVIES = "movie/{movie_id}/recommendations"
        const val SIMILAR_MOVIES = "movie/{movie_id}/similar"
        const val MOVIE_DETAILS = "movie/{movie_id}"
        const val CREDITS_CAST_MOVIE = "movie/{movie_id}/credits"
    }
}