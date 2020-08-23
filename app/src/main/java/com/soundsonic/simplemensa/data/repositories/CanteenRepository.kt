package com.soundsonic.simplemensa.data.repositories

import com.soundsonic.simplemensa.data.model.Canteen

interface CanteenRepository {
    suspend fun getCanteens(): List<Canteen>
}
