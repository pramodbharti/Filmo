package com.pramodbharti.filmo.data.network

import android.content.Context
import com.pramodbharti.filmo.ui.isInternetAvailable
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

class ForceCacheInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        if (!isInternetAvailable(context)) {
            builder.cacheControl(CacheControl.FORCE_CACHE)
        }

        return chain.proceed(builder.build())
    }
}