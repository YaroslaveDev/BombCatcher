package com.pfv.bombcatcher

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application(){

    override fun onCreate() {
        super.onCreate()
        context = this

    }

    companion object {
        lateinit var context: Context
    }
}