package com.husqvarna.dsassignment

import android.app.Application
import com.husqvarna.dsassignment.framework.di.dataModules
import com.husqvarna.dsassignment.framework.di.domainModules
import com.husqvarna.dsassignment.framework.di.frameWorkModules
import com.husqvarna.dsassignment.framework.di.vmModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.logger.Level

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    fun startKoin() {
        org.koin.core.context.startKoin {
            androidLogger(Level.INFO)
            androidContext(this@MainApplication)
        }
        loadKoinModules(listOf(frameWorkModules, vmModules, domainModules, dataModules))
    }
}