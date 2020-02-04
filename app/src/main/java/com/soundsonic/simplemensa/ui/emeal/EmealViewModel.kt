package com.soundsonic.simplemensa.ui.emeal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.soundsonic.simplemensa.data.cardcheck.cardreader.ValueData

class EmealViewModel : ViewModel() {

    private val valueData: MutableLiveData<ValueData> = MutableLiveData()

    val balance: LiveData<Double> = Transformations.map(valueData) { data ->
        data.value / 1000.0
    }

    fun updateData(data: ValueData) {
        valueData.value = data
    }
}
