package com.soundsonic.simplemensa.ui.map

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
class MapViewModel @Inject constructor(
    private val canteenRepository: CanteenRepository
) : ViewModel() {

    private val _canteens: MutableLiveData<List<Canteen>> by lazy {
        MutableLiveData<List<Canteen>>().also {
            viewModelScope.launch {
                it.value = canteenRepository.getCanteens()
            }
        }
    }

    val canteens: LiveData<List<Canteen>> get() = _canteens
}
