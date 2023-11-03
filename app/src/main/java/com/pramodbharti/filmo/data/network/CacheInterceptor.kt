package com.pramodbharti.filmo.data.network

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val cacheControl = CacheControl.Builder().maxAge(1, TimeUnit.DAYS).build()
        return chain.proceed(
            chain.request().newBuilder().header("Cache-Control", cacheControl.toString()).build()
        )
    }
}