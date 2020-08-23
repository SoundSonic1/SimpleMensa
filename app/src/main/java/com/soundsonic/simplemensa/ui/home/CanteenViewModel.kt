package com.soundsonic.simplemensa.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.data.repositories.CanteenRepository
import kotlinx.coroutines.launch

class CanteenViewModel @ViewModelInject constructor(
    private val canteenRepository: CanteenRepository
) : ViewModel() {

    private val _canteens = MutableLiveData<List<Canteen>>()
    val canteens: LiveData<List<Canteen>> = _canteens

    init {
        fetchCanteens()
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private fun fetchCanteens() {
        viewModelScope.launch {
            _canteens.value = canteenRepository.getCanteens()
        }
    }
}
