package com.israis007.pruebagapsi

import android.app.Application

class GapsiApp: Application() {

    companion object {
        lateinit var instance : GapsiApp
            private set
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
    }
}