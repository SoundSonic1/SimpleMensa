package com.soundsonic.simplemensa.data.repositories

import com.soundsonic.simplemensa.data.api.OpenMensaApi
import com.soundsonic.simplemensa.data.database.CanteenDao
import com.soundsonic.simplemensa.data.model.Canteen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CanteenRepositoryImpl @Inject constructor(
    private val api: OpenMensaApi,
    private val canteenDao: CanteenDao
) : CanteenRepository {
    override suspend fun getCanteens(): List<Canteen> = withContext(Dispatchers.Default) {
        try {
            api.getCanteens().apply {
                canteenDao.insertAll(this)
            }
        } catch (e: Exception) {
            canteenDao.findAll()
        }
    }
}
