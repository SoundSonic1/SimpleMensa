package com.soundsonic.simplemensa.data.repositories

import com.soundsonic.simplemensa.data.api.OpenMensaApi
import com.soundsonic.simplemensa.data.model.Canteen
import javax.inject.Inject

class CanteenRepositoryImpl @Inject constructor(
    private val openMensaApi: OpenMensaApi
) : CanteenRepository {

    override suspend fun getCanteens(): List<Canteen> = try {
        openMensaApi.getCanteens()
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }
}
