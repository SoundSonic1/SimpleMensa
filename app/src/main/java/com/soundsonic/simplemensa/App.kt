package com.soundsonic.simplemensa

import android.app.Application
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import com.soundsonic.simplemensa.util.Constants.DARK_THEME_ON
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        AppCompatDelegate.setDefaultNightMode(
            sharedPref.getInt(DARK_THEME_ON, AppCompatDelegate.MODE_NIGHT_NO)
        )
    }
}
