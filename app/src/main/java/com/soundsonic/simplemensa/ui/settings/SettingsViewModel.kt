package com.soundsonic.simplemensa.ui.settings

import androidx.lifecycle.ViewModel
import com.soundsonic.simplemensa.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    fun setHighlightVegetarianFood(highlight: Boolean) = userRepository.setHighlightVegetarianFood(highlight)
}
