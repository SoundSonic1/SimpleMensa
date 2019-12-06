package com.soundsonic.simplemensa.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.data.repositories.CanteenRepository
import kotlinx.coroutines.launch

class CanteenViewModel(private val canteenRepository: CanteenRepository) : ViewModel() {

    private val _canteens by lazy {
        MutableLiveData<List<Canteen>>().also {
            viewModelScope.launch {
                it.value = canteenRepository.getCanteens()
            }
        }
    }

    val canteens: LiveData<List<Canteen>> get() = _canteens
}
