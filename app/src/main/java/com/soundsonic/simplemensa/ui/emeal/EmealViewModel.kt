package com.soundsonic.simplemensa.ui.emeal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.soundsonic.simplemensa.data.cardcheck.cardreader.ValueData

class EmealViewModel : ViewModel() {

    private val _valueData: MutableLiveData<ValueData> = MutableLiveData()
    val valueData: LiveData<ValueData> get() = _valueData

    fun updateData(data: ValueData) {
        _valueData.value = data
    }
}
