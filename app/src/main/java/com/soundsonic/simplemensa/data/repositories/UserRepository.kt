package com.soundsonic.simplemensa.data.repositories

import com.soundsonic.simplemensa.data.model.UserProfile

interface UserRepository {
    suspend fun insertUserProfile(userProfile: UserProfile)
    suspend fun getUserById(id: Int): UserProfile?
}
