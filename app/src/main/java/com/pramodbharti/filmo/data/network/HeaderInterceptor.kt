package com.pramodbharti.filmo.data.network

import com.pramodbharti.filmo.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain
            .proceed(
                chain
                    .request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer ${BuildConfig.ACCESS_TOKEN}")
                    .build()
            )
    }

}