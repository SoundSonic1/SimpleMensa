package com.soundsonic.simplemensa.data.repositories

import com.soundsonic.simplemensa.data.database.UserProfileDao
import com.soundsonic.simplemensa.data.model.UserProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userProfileDao: UserProfileDao
) : UserRepository {

    companion object {
        private const val USER_ID = 1
    }

    private val _userProfile: MutableStateFlow<UserProfile?> = MutableStateFlow(null)
    override val userProfile: StateFlow<UserProfile?> get() = _userProfile

    override suspend fun loadUser() {
        _userProfile.value = getUserProfile()
    }

    override suspend fun addCanteen(canteenId: Int) {
        val user = getUserProfile()
        user.favouriteCanteenIds.add(canteenId)
        insertUserProfile(user)
    }

    override suspend fun removeCanteen(canteenId: Int) {
        val user = getUserProfile()
        user.favouriteCanteenIds.remove(canteenId)
        insertUserProfile(user)
    }

    override suspend fun setShowOnlyFavourites(showOnlyFavourites: Boolean) {
        val user = getUserProfile()
        user.showOnlyFavourites = showOnlyFavourites
        insertUserProfile(user)
    }

    override suspend fun getUserProfile(): UserProfile = withContext(Dispatchers.Default) {
        val user = userProfileDao.findUserById(USER_ID) ?: UserProfile(
            id = USER_ID,
            favouriteCanteenIds = mutableSetOf(),
            showOnlyFavourites = false
        )
        user
    }

    private suspend fun insertUserProfile(profile: UserProfile) = withContext(Dispatchers.Default) {
        userProfileDao.insert(profile)
        _userProfile.value = profile
    }
}
