package com.soundsonic.simplemensa.ui.meals.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soundsonic.simplemensa.data.model.Meal
import com.soundsonic.simplemensa.data.repositories.MealsRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.launch

class MealsViewModel(
    private val mealsRepository: MealsRepository
) : ViewModel() {
    private val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY)

    private val _meals: MutableLiveData<List<Meal>> by lazy {
        MutableLiveData<List<Meal>>().also {
            viewModelScope.launch {
                it.value = mealsRepository.getMeals(formatter.format(Date()))
            }
        }
    }

    val meals: LiveData<List<Meal>> get() = _meals
}
