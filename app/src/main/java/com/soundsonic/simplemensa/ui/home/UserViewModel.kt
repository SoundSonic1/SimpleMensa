package com.soundsonic.simplemensa.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
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

    companion object {
        private const val USER_ID = 1
    }

    private val userProfile: MutableLiveData<UserProfile> = MutableLiveData()
    val favouriteCanteenIds: LiveData<Set<Int>> = Transformations.map(userProfile) {
        it.favouriteCanteenIds
    }

    init {
        viewModelScope.launch {
            val profile = userRepository.getUserById(USER_ID)
            if (profile == null) {
                val newProfile = UserProfile(USER_ID, mutableSetOf())
                userRepository.insertUserProfile(newProfile)
                userProfile.value = newProfile
            } else {
                userProfile.value = profile
            }
        }
    }

    fun addCanteen(canteen: Canteen) {
        val user = userProfile.value ?: return
        user.favouriteCanteenIds.add(canteen.id)
        viewModelScope.launch {
            userRepository.insertUserProfile(user)
            userProfile.value = user
        }
    }

    fun removeCanteen(canteen: Canteen) {
        val user = userProfile.value ?: return
        user.favouriteCanteenIds.remove(canteen.id)
        viewModelScope.launch {
            userRepository.insertUserProfile(user)
            userProfile.value = user
        }
    }
}
