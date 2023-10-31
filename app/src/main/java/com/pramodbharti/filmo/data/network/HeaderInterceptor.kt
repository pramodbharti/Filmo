package com.pramodbharti.filmo.data.network

import com.pramodbharti.filmo.BuildConfig
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val cacheControl = CacheControl.Builder().maxAge(1, TimeUnit.DAYS).build()
        return chain
            .proceed(
                chain
                    .request()
                    .newBuilder()
                    .cacheControl(cacheControl)
                    .addHeader("Authorization", "Bearer ${BuildConfig.ACCESS_TOKEN}")
                    .build()
            )
    }

}