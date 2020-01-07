package com.soundsonic.simplemensa.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soundsonic.simplemensa.data.database.UserProfileDao
import com.soundsonic.simplemensa.data.model.UserProfile
import com.soundsonic.simplemensa.util.Constants.USER_ID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserProfileViewModel(private val userDao: UserProfileDao) : ViewModel() {

    private val _userProfile: MutableLiveData<UserProfile> by lazy {
        MutableLiveData<UserProfile>().also {
            viewModelScope.launch {
                it.value = withContext(Dispatchers.Default) {
                    userDao.findUserById(USER_ID)
                }
            }
        }
    }

    val userProfile: LiveData<UserProfile> get() = _userProfile

    fun addCanteen(id: Long) {
        _userProfile.value?.favouriteCanteenIds?.add(id)
        _userProfile.value = _userProfile.value.also { user ->
            user?.let {
                viewModelScope.launch {
                    withContext(Dispatchers.Default) {
                        userDao.insert(user)
                    }
                }
            }
        }
    }

    fun removeCanteen(id: Long) {
        if (_userProfile.value?.favouriteCanteenIds?.remove(id) == true) {
            _userProfile.value = _userProfile.value.also { user ->
                user?.let {
                    viewModelScope.launch {
                        withContext(Dispatchers.Default) {
                            userDao.insert(user)
                        }
                    }
                }
            }
        }
    }
}
