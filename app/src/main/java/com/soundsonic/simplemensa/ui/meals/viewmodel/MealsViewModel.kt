package com.soundsonic.simplemensa.ui.meals.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soundsonic.simplemensa.data.api.OpenMensaApi
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.data.model.Meal
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MealsViewModel(
    private val openMensaApi: OpenMensaApi,
    private val canteen: Canteen
) : ViewModel() {
    private val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY)

    private val _meals: MutableLiveData<List<Meal>> by lazy {
        MutableLiveData<List<Meal>>().also {
            viewModelScope.launch {
                it.value = openMensaApi.getMeals(canteen.id.toString(), formatter.format(Date()))
            }
        }
    }

    val meals: LiveData<List<Meal>> get() = _meals
}
