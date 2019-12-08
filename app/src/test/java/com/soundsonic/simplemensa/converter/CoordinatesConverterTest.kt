package com.soundsonic.simplemensa.converter

import com.soundsonic.simplemensa.data.database.CoordinatesConverter
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CoordinatesConverterTest {

    @Test
    fun `convert to string`() {
        val string = CoordinatesConverter.convertToString(listOf(1.0, 2.2))
        assertEquals("1.0,2.2", string)
    }

    @Test
    fun `convert to coordinates`() {
        val string = "3.45,-5.1"
        assertEquals(listOf(3.45, -5.1), CoordinatesConverter.convertToCoordinates(string))
    }
}
