package com.soundsonic.simplemensa.data.repositories

import com.soundsonic.simplemensa.data.model.Meal
import java.util.Date

interface MealRepository {
    suspend fun getMealsForDate(canteenId: Int, date: Date): List<Meal>
}
