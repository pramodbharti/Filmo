package com.pramodbharti.filmo.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pramodbharti.filmo.data.BASE_URL
import com.pramodbharti.filmo.data.network.HeaderInterceptor
import com.pramodbharti.filmo.data.network.MoviesApiService
import com.pramodbharti.filmo.data.repositories.MoviesRepository
import com.pramodbharti.filmo.data.repositories.NetworkMoviesRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

interface AppContainer {
    val moviesRepository: MoviesRepository
}

class DefaultAppContainer : AppContainer {

    private val client = OkHttpClient
        .Builder()
        .addInterceptor(HeaderInterceptor())
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
        NetworkMoviesRepository(moviesApiService)
    }

}