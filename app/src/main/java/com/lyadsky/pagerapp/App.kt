package com.lyadsky.pagerapp

import android.app.Application
import com.lyadsky.pagerapp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            allowOverride(true)
        }
    }
}
