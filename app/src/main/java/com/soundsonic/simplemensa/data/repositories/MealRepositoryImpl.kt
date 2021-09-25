package com.soundsonic.simplemensa.data.repositories

import com.soundsonic.simplemensa.data.api.OpenMensaApi
import com.soundsonic.simplemensa.data.model.Meal
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealRepositoryImpl @Inject constructor(
    private val api: OpenMensaApi,
    private val simpleDateFormat: SimpleDateFormat
) : MealRepository {

    override suspend fun getMealsForDate(canteenId: Int, date: Date): List<Meal> = try {
        api.getMeals(canteenId, simpleDateFormat.format(date))
    } catch (e: Exception) {
        Timber.d(e)
        emptyList()
    }
}
