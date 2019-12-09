package com.soundsonic.simplemensa.adapter

import com.soundsonic.simplemensa.data.adapter.MealAdapter
import com.soundsonic.simplemensa.data.model.Meal
import com.squareup.moshi.Moshi
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MealAdapterTest {

    private val moshi = Moshi.Builder().add(MealAdapter()).build()
    private val adapter = moshi.adapter(Meal::class.java)

    @Test
    fun `meal without price`() {
        val json = """{
        "id": 238287,
        "name": "Pasta mit Basilikumpesto",
        "notes": [
            "Menü ist vegan"
        ],
        "prices": [],
        "category": "Pasta",
        "image": "Image with Url",
        "url": "speiseplan.html"
        }"""
        val meal = adapter.fromJson(json)

        val expectedMeal = Meal(
            id = 238287,
            name = "Pasta mit Basilikumpesto",
            notes = listOf("Menü ist vegan"),
            prices = emptyMap(),
            category = "Pasta",
            image = "Image with Url",
            url = "speiseplan.html"
        )

        assertEquals(expectedMeal, meal)
    }

    @Test
    fun `meal with price`() {

        val json = """{
        "id": 238178,
        "name": "Gefülltes Handbrot mit Salami, roten Zwiebeln und Käse, dazu Paprika-Dip",
        "notes": [
            "enthält Schweinefleisch",
            "mit Konservierungsstoff (2)"
        ],
        "prices": {
            "Studierende": 2.77,
            "Bedienstete": 4.57
        },
        "category": "Ofenfrisch",
        "image": "studentenwerk-dresden-lieber-mensen-gehen.jpg",
        "url": "speiseplan.html"
        }"""

        val meal = adapter.fromJson(json)

        val expectedMeal = Meal(
            id = 238178,
            name = "Gefülltes Handbrot mit Salami, roten Zwiebeln und Käse, dazu Paprika-Dip",
            notes = listOf("enthält Schweinefleisch", "mit Konservierungsstoff (2)"),
            prices = mapOf("Studierende" to 2.77, "Bedienstete" to 4.57),
            category = "Ofenfrisch",
            image = "studentenwerk-dresden-lieber-mensen-gehen.jpg",
            url = "speiseplan.html"
        )

        assertEquals(expectedMeal, meal)
    }

    @Test
    fun `minimum expected response`() {
        val json = """{"id": 123, "name": "food"}"""

        val meal = adapter.fromJson(json)
        val expectedMeal = Meal(id = 123, name = "food")

        assertEquals(expectedMeal, meal)
    }
}
