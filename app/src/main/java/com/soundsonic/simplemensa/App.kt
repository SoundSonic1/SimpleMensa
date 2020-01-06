package com.soundsonic.simplemensa

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import com.mapbox.mapboxsdk.Mapbox
import com.soundsonic.simplemensa.BuildConfig.MAPBOX_TOKEN
import com.soundsonic.simplemensa.di.DaggerAppComponent
import com.soundsonic.simplemensa.util.Constants.DARK_THEME_ON
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class App : DaggerApplication() {

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .builder()
            .create(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        Mapbox.getInstance(applicationContext, MAPBOX_TOKEN)
        AppCompatDelegate.setDefaultNightMode(
            sharedPref.getInt(DARK_THEME_ON, AppCompatDelegate.MODE_NIGHT_NO)
        )
    }
}
