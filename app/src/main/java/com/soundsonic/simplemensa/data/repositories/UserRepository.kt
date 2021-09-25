package com.soundsonic.simplemensa.data.repositories

import com.soundsonic.simplemensa.data.model.UserProfile
import kotlinx.coroutines.flow.StateFlow

interface UserRepository {

    val userProfile: StateFlow<UserProfile?>

    suspend fun loadUser()
    suspend fun addCanteen(canteenId: Int)
    suspend fun removeCanteen(canteenId: Int)
    suspend fun setShowOnlyFavourites(showOnlyFavourites: Boolean)
    suspend fun getUserProfile(): UserProfile
}
