package com.soundsonic.simplemensa.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
@JsonClass(generateAdapter = true)
data class Canteen(
    @PrimaryKey val id: Int,
    val name: String,
    val city: String,
    val address: String,
    val coordinates: List<Double>,
    val url: String,
    val menu: String
) : Parcelable {

    companion object {
        const val CANTEEN_OVERVIEW_URL =
            "https://static.studentenwerk-dresden.de/mensen/mensen-cafeterien-aussenansichten/"
    }

    fun getImageUrl(): String {
        val htmlName = url.substringAfter("details-").substringBefore(".html")
        return "$CANTEEN_OVERVIEW_URL$htmlName.jpg"
    }
}
