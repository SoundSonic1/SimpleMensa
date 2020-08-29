package com.soundsonic.simplemensa.data.repositories

import com.soundsonic.simplemensa.data.database.UserProfileDao
import com.soundsonic.simplemensa.data.model.UserProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userProfileDao: UserProfileDao
) : UserRepository {

    override suspend fun insertUserProfile(userProfile: UserProfile) = withContext(Dispatchers.Default) {
        userProfileDao.insert(userProfile)
    }

    override suspend fun getUserById(id: Int): UserProfile? = withContext(Dispatchers.Default) {
        userProfileDao.findUserById(id)
    }
}
