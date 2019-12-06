package com.soundsonic.simplemensa.data.repositories

import com.soundsonic.simplemensa.data.model.Meal

interface MealsRepository {
    suspend fun getMeals(date: String): List<Meal>
}
