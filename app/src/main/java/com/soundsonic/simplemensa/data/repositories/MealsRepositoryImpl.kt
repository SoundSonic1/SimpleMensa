package com.soundsonic.simplemensa.data.repositories

import com.soundsonic.simplemensa.data.api.OpenMensaApi
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.data.model.Meal
import javax.inject.Inject

class MealsRepositoryImpl @Inject constructor(
    private val canteen: Canteen,
    private val openMensaApi: OpenMensaApi
) : MealsRepository {

    override suspend fun getMeals(date: String): List<Meal> = try {
        openMensaApi.getMeals(canteen.id.toString(), date)
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }
}
