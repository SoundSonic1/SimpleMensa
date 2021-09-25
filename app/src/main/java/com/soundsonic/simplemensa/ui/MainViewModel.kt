package com.soundsonic.simplemensa.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soundsonic.simplemensa.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject() constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun setShowOnlyFavourites(showOnlyFavourites: Boolean) {
        viewModelScope.launch {
            userRepository.setShowOnlyFavourites(showOnlyFavourites = showOnlyFavourites)
        }
    }

    suspend fun getShowOnlyFavourites(): Boolean = userRepository.getUserProfile().showOnlyFavourites
}
