package com.soundsonic.simplemensa.data.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Meal(
    val id: Int,
    val name: String,
    val notes: List<String>?,
    val prices: Map<String, Double?>,
    val category: String?,
    val image: String?,
    val url: String?,
    var highlightMeal: Boolean = false
) : Parcelable {

    val isVegetarian: Boolean get() = notes?.any {
        it.contains("vegetarisch", true) || it.contains("vegan", true)
    } ?: false
}
