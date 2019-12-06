package com.soundsonic.simplemensa.data.api

import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.data.model.Meal
import retrofit2.http.GET
import retrofit2.http.Path

interface OpenMensaApi {

    @GET("canteens")
    suspend fun getCanteens(): List<Canteen>

    @GET("canteens/{id}/days/{date}/meals")
    suspend fun getMeals(
        @Path("id") id: Long,
        @Path("date") date: String
    ): List<Meal>
}
