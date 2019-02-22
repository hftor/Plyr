package com.hftor.plyr

import android.app.Application
import android.util.Log

/**
 * Created by hafthorg on 22/02/2019.
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("override", "- Do initial work -")
    }
}