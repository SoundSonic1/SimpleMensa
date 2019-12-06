package com.soundsonic.simplemensa.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Meal(
    val id: Long,
    val name: String,
    val notes: List<String>?,
    val category: String? = null,
    val image: String? = null,
    val url: String? = null
)
