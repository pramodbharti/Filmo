package com.pramodbharti.filmo

import android.app.Application
import com.pramodbharti.filmo.data.di.AppContainer
import com.pramodbharti.filmo.data.di.DefaultAppContainer

class FilmoApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(applicationContext)
    }
}