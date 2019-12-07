package com.soundsonic.simplemensa.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
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
) : Parcelable
