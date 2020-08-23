package com.soundsonic.simplemensa.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Canteen(
    @PrimaryKey val id: Long,
    val name: String,
    val city: String,
    val address: String,
    val coordinates: List<Double>,
    val url: String,
    val menu: String
)
