package com.soundsonic.simplemensa.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.preference.CheckBoxPreference
import androidx.preference.PreferenceFragmentCompat
import com.soundsonic.simplemensa.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {

    private val viewModel: SettingsViewModel by viewModels()
    private val listener: SharedPreferences.OnSharedPreferenceChangeListener =
        object : SharedPreferences.OnSharedPreferenceChangeListener {
            override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
                key ?: return

                val checkBoxPref = findPreference<CheckBoxPreference>(key)
                checkBoxPref?.let {
                    viewModel.setHighlightVegetarianFood(it.isChecked)
                }
            }
        }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceManager.sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        preferenceManager.sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
    }
}
