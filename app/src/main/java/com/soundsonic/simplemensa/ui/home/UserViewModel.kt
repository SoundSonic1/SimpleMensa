package com.soundsonic.simplemensa.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.data.model.UserProfile
import com.soundsonic.simplemensa.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val userProfile: LiveData<UserProfile?> = userRepository.userProfile.asLiveData()
    val favouriteCanteenIds: LiveData<Set<Int>> = Transformations.map(userProfile) {
        it?.favouriteCanteenIds ?: mutableSetOf()
    }

    init {
        viewModelScope.launch {
            userRepository.loadUser()
        }
    }

    fun addCanteen(canteen: Canteen) {
        viewModelScope.launch {
            userRepository.addCanteen(canteenId = canteen.id)
        }
    }

    fun removeCanteen(canteen: Canteen) {
        viewModelScope.launch {
            userRepository.removeCanteen(canteenId = canteen.id)
        }
    }
}
