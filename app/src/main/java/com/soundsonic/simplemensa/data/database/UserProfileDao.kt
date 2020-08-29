package com.soundsonic.simplemensa.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soundsonic.simplemensa.data.model.UserProfile

@Dao
interface UserProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userProfile: UserProfile)

    @Query("SELECT * FROM UserProfile WHERE id = :id")
    suspend fun findUserById(id: Int): UserProfile?

    @Query("DELETE FROM UserProfile")
    suspend fun deleteAll()
}
