package com.gabrieal.podcastapplication.ui.views.base

import android.app.Application
import com.gabrieal.podcastapplication.di.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(listOf(appModule))
        }
    }
}