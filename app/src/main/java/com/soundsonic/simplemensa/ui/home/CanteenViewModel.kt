package com.soundsonic.simplemensa.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.data.repositories.CanteenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CanteenViewModel @Inject constructor(
    private val canteenRepository: CanteenRepository
) : ViewModel() {

    private val _canteens = MutableLiveData<List<Canteen>>()
    val canteens: LiveData<List<Canteen>> = _canteens

    init {
        fetchCanteens()
    }

    private fun fetchCanteens() {
        viewModelScope.launch {
            _canteens.value = canteenRepository.getCanteens()
        }
    }
}
