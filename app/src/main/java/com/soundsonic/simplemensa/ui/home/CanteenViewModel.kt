package com.soundsonic.simplemensa.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.data.model.UserProfile
import com.soundsonic.simplemensa.data.repositories.CanteenRepository
import com.soundsonic.simplemensa.data.repositories.UserRepository
import com.soundsonic.simplemensa.extensions.combineWith
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CanteenViewModel @Inject constructor(
    userRepository: UserRepository,
    private val canteenRepository: CanteenRepository
) : ViewModel() {

    private val _canteens = MutableLiveData<List<Canteen>>()
    private val userProfile: LiveData<UserProfile?> = userRepository.userProfile.asLiveData()
    val canteens: LiveData<List<Canteen>> = userProfile.combineWith(_canteens) { user, canteenList ->

        if (canteenList.isNullOrEmpty()) {
            return@combineWith emptyList()
        }

        if (user?.showOnlyFavourites == true) {
            canteenList.filter { canteen ->
                user.favouriteCanteenIds.contains(canteen.id)
            }
        } else {
            canteenList
        }
    }

    init {
        fetchCanteens()
    }

    private fun fetchCanteens() {
        viewModelScope.launch {
            _canteens.value = canteenRepository.getCanteens()
        }
    }
}
