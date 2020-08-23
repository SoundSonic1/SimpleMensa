package com.soundsonic.simplemensa.ui.meals

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soundsonic.simplemensa.data.model.Meal
import com.soundsonic.simplemensa.data.repositories.MealRepository
import kotlinx.coroutines.launch
import java.util.Date

class MealsViewModel @ViewModelInject constructor(
    private val mealRepository: MealRepository
) : ViewModel() {

    private val _meals = MutableLiveData<List<Meal>>()
    val meals: LiveData<List<Meal>> = _meals

    fun getMealsForDate(canteenId: Int, date: Date) = viewModelScope.launch {
        _meals.value = mealRepository.getMealsForDate(canteenId, date)
    }
}
