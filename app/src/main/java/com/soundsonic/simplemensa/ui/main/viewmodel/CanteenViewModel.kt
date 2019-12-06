package com.soundsonic.simplemensa.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soundsonic.simplemensa.data.api.OpenMensaApi
import com.soundsonic.simplemensa.data.model.Canteen
import kotlinx.coroutines.launch

class CanteenViewModel(private val openMensaApi: OpenMensaApi) : ViewModel() {

    private val _canteens by lazy {
        MutableLiveData<List<Canteen>>().also {
            viewModelScope.launch {
                it.value = openMensaApi.getCanteens()
            }
        }
    }

    val canteens: LiveData<List<Canteen>> get() = _canteens

    fun reloadCanteens() {
        viewModelScope.launch {
            _canteens.value = openMensaApi.getCanteens()
        }
    }
}
