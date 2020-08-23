package com.soundsonic.simplemensa.data.api

import com.soundsonic.simplemensa.data.model.Canteen
import retrofit2.http.GET

interface OpenMensaApi {
    @GET("canteens")
    suspend fun getCanteens(): List<Canteen>
}
