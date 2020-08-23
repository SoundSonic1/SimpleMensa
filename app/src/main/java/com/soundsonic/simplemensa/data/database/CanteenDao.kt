package com.soundsonic.simplemensa.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soundsonic.simplemensa.data.model.Canteen

@Dao
interface CanteenDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(canteens: List<Canteen>)

    @Query("SELECT * FROM Canteen")
    suspend fun findAll(): List<Canteen>
}
