package com.soundsonic.simplemensa.ui.meals.listener

import com.soundsonic.simplemensa.data.model.Meal

interface MealHandler {
    fun onMealClicked(meal: Meal)
}
