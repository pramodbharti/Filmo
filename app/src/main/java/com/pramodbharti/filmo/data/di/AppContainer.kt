package com.pramodbharti.filmo.data.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pramodbharti.filmo.data.BASE_URL
import com.pramodbharti.filmo.data.network.CacheInterceptor
import com.pramodbharti.filmo.data.network.ForceCacheInterceptor
import com.pramodbharti.filmo.data.network.HeaderInterceptor
import com.pramodbharti.filmo.data.network.MoviesApiService
import com.pramodbharti.filmo.data.network.TvShowsApiService
import com.pramodbharti.filmo.data.repositories.MoviesRepository
import com.pramodbharti.filmo.data.repositories.NetworkMoviesRepository
import com.pramodbharti.filmo.data.repositories.NetworkTvShowsRepository
import com.pramodbharti.filmo.data.repositories.TvShowsRepository
import com.pramodbharti.filmo.domain.MoviesUseCase
import com.pramodbharti.filmo.domain.TvShowsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

interface AppContainer {
    val moviesRepository: MoviesRepository
    val moviesUseCase: MoviesUseCase
    val tvShowsRepository: TvShowsRepository
    val tvShowsUseCase: TvShowsUseCase
}

const val CACHE_SIZE = 10 * 1024 * 2024

class DefaultAppContainer(context: Context) : AppContainer {

    private val cache = Cache(context.cacheDir, CACHE_SIZE.toLong())
    private val client = OkHttpClient
        .Builder()
        .cache(cache)
        .addNetworkInterceptor(CacheInterceptor())
        .addInterceptor(ForceCacheInterceptor(context))
        .addInterceptor(HeaderInterceptor())
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    private val retrofit = Retrofit
        .Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val moviesApiService by lazy {
        retrofit.create(MoviesApiService::class.java)
    }
    override val moviesRepository: MoviesRepository by lazy {
        NetworkMoviesRepository(moviesApiService, Dispatchers.IO)
    }
    override val moviesUseCase: MoviesUseCase by lazy {
        MoviesUseCase(moviesRepository)
    }
    private val tvShowsApiService by lazy {
        retrofit.create(TvShowsApiService::class.java)
    }
    override val tvShowsRepository: TvShowsRepository by lazy {
        NetworkTvShowsRepository(tvShowsApiService, Dispatchers.IO)
    }
    override val tvShowsUseCase: TvShowsUseCase by lazy {
        TvShowsUseCase(tvShowsRepository)
    }
}