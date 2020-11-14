package com.example.mcsservice

import android.app.Application
import timber.log.Timber

@Suppress("unused")
class Application : Application() {


    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

    }

}