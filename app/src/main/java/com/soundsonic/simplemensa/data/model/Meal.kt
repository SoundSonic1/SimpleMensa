package com.soundsonic.simplemensa.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Meal(
    val id: Long,
    val name: String,
    val notes: List<String>?,
    val category: String?,
    val image: String?,
    val url: String?
)
