package com.melisa.paradise.di

import android.app.Application
import com.google.firebase.BuildConfig
import timber.log.Timber

class EdenApplication : Application() {
    lateinit var appContainer: AppContainer
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        appContainer = AppContainer()
    }
}