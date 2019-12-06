package com.soundsonic.simplemensa.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Canteen(
    val id: Long,
    val name: String,
    val city: String,
    val address: String,
    val url: String,
    val menu: String
)
