package com.example.spacedeliveryman

import android.app.Application
import com.example.spacedeliveryman.di.databaseModule
import com.example.spacedeliveryman.di.networkModule
import com.example.spacedeliveryman.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SpaceDeliveryApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SpaceDeliveryApp)
            androidLogger(level = Level.DEBUG)
            modules(networkModule, viewModelModule, databaseModule)
        }
    }
}