package com.soundsonic.simplemensa.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.data.model.UserProfile

@Database(entities = [Canteen::class, UserProfile::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun canteenDao(): CanteenDao
    abstract fun userProfileDao(): UserProfileDao
}
