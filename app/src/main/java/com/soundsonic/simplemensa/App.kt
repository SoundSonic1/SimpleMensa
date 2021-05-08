package com.soundsonic.simplemensa

import android.app.Application
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import com.mapbox.mapboxsdk.Mapbox
import com.soundsonic.simplemensa.BuildConfig.MAPBOX_TOKEN
import com.soundsonic.simplemensa.util.Constants.DARK_THEME_ON
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(
            sharedPref.getInt(DARK_THEME_ON, AppCompatDelegate.MODE_NIGHT_NO)
        )
        Mapbox.getInstance(applicationContext, MAPBOX_TOKEN)
    }
}
