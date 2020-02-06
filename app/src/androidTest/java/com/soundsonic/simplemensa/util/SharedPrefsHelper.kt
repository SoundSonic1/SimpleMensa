package com.soundsonic.simplemensa.util

import android.content.Context
import androidx.core.content.edit
import com.soundsonic.simplemensa.util.Constants.PREFERENCE_FILE_KEY

object SharedPrefsHelper {
    fun clearSharedPrefs(context: Context) {
        val prefs = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)
        prefs.edit(commit = true) {
            clear()
        }
    }
}
