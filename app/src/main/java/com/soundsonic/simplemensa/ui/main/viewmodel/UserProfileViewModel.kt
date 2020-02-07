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

        val user = _userProfile.value ?: return

        user.favouriteCanteenIds.add(id)
        _userProfile.value = user
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                userDao.insert(user)
            }
        }
    }

    fun removeCanteen(id: Long) {

        val user = _userProfile.value ?: return

        if (user.favouriteCanteenIds.remove(id) == true) {
            _userProfile.value = user
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    userDao.insert(user)
                }
            }
        }
    }
}
