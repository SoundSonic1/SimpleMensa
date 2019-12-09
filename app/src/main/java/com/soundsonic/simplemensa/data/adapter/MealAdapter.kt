package com.soundsonic.simplemensa.data.adapter

import com.soundsonic.simplemensa.data.model.Meal
import com.squareup.moshi.FromJson
import javax.inject.Inject

class MealAdapter @Inject constructor() {

    @Suppress("UNCHECKED_CAST")
    @FromJson
    fun fromJson(jsonMap: Map<String, *>): Meal {
        return Meal(
            id = (jsonMap["id"] as Double).toLong(),
            name = jsonMap["name"] as String,
            notes = jsonMap["notes"] as List<String>,
            prices = jsonMap["prices"] as? Map<String, Double> ?: emptyMap(),
            category = jsonMap["category"] as? String,
            image = jsonMap["image"] as? String,
            url = jsonMap["url"] as? String
        )
    }
}
