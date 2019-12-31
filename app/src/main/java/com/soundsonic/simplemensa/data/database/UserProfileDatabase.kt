package com.soundsonic.simplemensa.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.soundsonic.simplemensa.data.model.UserProfile

@Database(entities = [UserProfile::class], version = 1, exportSchema = false)
@TypeConverters(IdsConverter::class)
abstract class UserProfileDatabase : RoomDatabase() {

    abstract fun userProfileDao(): UserProfileDao
}
