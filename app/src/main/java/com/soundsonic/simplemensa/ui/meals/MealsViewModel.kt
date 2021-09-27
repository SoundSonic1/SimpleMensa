package com.soundsonic.simplemensa.ui.meals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.soundsonic.simplemensa.data.model.Meal
import com.soundsonic.simplemensa.data.repositories.MealRepository
import com.soundsonic.simplemensa.data.repositories.UserRepository
import com.soundsonic.simplemensa.extensions.combineWith
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    userRepository: UserRepository,
    private val mealRepository: MealRepository
) : ViewModel() {

    private val highlightVegetarianFood: LiveData<Boolean> = userRepository.highlightVegetarianFood.asLiveData()
    private val _meals = MutableLiveData<List<Meal>>()
    val meals: LiveData<List<Meal>> = _meals.combineWith(highlightVegetarianFood) { meals, highlight ->

        if (meals.isNullOrEmpty()) {
            return@combineWith listOf<Meal>()
        }

        if (highlight == true) {
            meals.forEach { meal ->
                meal.highlightMeal = meal.isVegetarian
            }
        }

        meals
    }

    fun getMealsForDate(canteenId: Int, date: Date) = viewModelScope.launch {
        _meals.value = mealRepository.getMealsForDate(canteenId, date)
    }
}
