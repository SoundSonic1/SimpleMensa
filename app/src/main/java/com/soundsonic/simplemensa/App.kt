package com.soundsonic.simplemensa

import com.mapbox.mapboxsdk.Mapbox
import com.soundsonic.simplemensa.BuildConfig.MAPBOX_TOKEN
import com.soundsonic.simplemensa.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .builder()
            .create(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        Mapbox.getInstance(applicationContext, MAPBOX_TOKEN)
    }
}
