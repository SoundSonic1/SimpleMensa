package com.soundsonic.simplemensa

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.soundsonic.simplemensa.data.database.UserProfileDao
import com.soundsonic.simplemensa.data.database.UserProfileDatabase
import com.soundsonic.simplemensa.data.model.UserProfile
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserProfileDatabaseTest {

    private lateinit var db: UserProfileDatabase
    private lateinit var userProfileDao: UserProfileDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, UserProfileDatabase::class.java
        ).build()
        userProfileDao = db.userProfileDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertUserProfile() {

        val user = UserProfile(
            id = 1,
            favouriteCanteenIds = mutableSetOf(2, 4)
        )

        runBlocking {
            userProfileDao.insert(user)
            assertEquals(user, userProfileDao.findUserById(1))
        }
    }

    @Test
    fun deleteAllProfiles() {
        val user = UserProfile(id = 1)

        runBlocking {
            userProfileDao.insert(user)
            userProfileDao.deleteAll()
            assertNull(userProfileDao.findUserById(1))
        }
    }

    @Test
    fun findNonExistentUser() {
        runBlocking {
            assertNull(userProfileDao.findUserById(-1))
        }
    }
}
