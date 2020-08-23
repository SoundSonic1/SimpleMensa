package com.soundsonic.simplemensa.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.soundsonic.simplemensa.data.model.Canteen

@Database(entities = [Canteen::class], version = 1, exportSchema = false)
@TypeConverters(CoordinatesConverter::class)
abstract class CanteenDatabase : RoomDatabase() {
    abstract fun canteenDao(): CanteenDao
}
