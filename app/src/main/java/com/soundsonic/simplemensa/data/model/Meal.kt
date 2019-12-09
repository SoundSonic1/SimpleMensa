package com.soundsonic.simplemensa.data.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Meal(
    val id: Long,
    val name: String,
    val notes: List<String>? = null,
    val prices: Map<String, Double> = emptyMap(),
    val category: String? = null,
    val image: String? = null,
    val url: String? = null
) : Parcelable
