package com.hftor.plyr

import android.app.Application
import android.util.Log
import org.koin.android.ext.android.startKoin

/**
 * Created by hafthorg on 22/02/2019.
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("override", "- Do initial work -")
        startKoin(this, listOf(DependencyModule.appModule))
    }
}