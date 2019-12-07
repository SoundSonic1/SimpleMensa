package com.soundsonic.simplemensa.ui.meals.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soundsonic.simplemensa.data.model.Meal
import com.soundsonic.simplemensa.data.repositories.MealsRepository
import kotlinx.coroutines.launch

class MealsViewModel(
    private val mealsRepository: MealsRepository,
    private val date: String
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>(false)

    private val _meals: MutableLiveData<List<Meal>> by lazy {
        MutableLiveData<List<Meal>>().also {
            viewModelScope.launch {
                _isLoading.value = true
                it.value = mealsRepository.getMeals(date)
                _isLoading.value = false
            }
        }
    }

    val isLoading: LiveData<Boolean> get() = _isLoading
    val meals: LiveData<List<Meal>> get() = _meals

    fun reloadMeals() {
        viewModelScope.launch {
            _isLoading.value = true
            _meals.value = mealsRepository.getMeals(date)
            _isLoading.value = false
        }
    }
}
