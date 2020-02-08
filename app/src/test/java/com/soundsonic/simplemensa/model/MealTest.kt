package com.soundsonic.simplemensa.model

import com.soundsonic.simplemensa.data.model.Meal
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MealTest {

    @Test
    fun `vegan food`() {
        val meal = Meal(
            id = 1,
            name = "Tofu",
            notes = listOf("Menü ist vegan")
        )
        assertTrue(meal.isVegetarian)
    }

    @Test
    fun `vegetarian food`() {
        val meal = Meal(
            id = 1,
            name = "Pancakes",
            notes = listOf("vegetarisch", "other stuff")
        )
        assertTrue(meal.isVegetarian)
    }

    @Test
    fun `meal with meat`() {
        val meal = Meal(
            id = 1,
            name = "Hamburger",
            notes = listOf("enthält Fleisch")
        )
        assertFalse(meal.isVegetarian)
    }
}
