package com.soundsonic.simplemensa.converter

import com.soundsonic.simplemensa.data.database.Converter
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ConverterTest {

    @Test
    fun `convert list to string`() {
        val string = Converter.convertListToString(listOf(1.0, 2.2))
        assertEquals("1.0,2.2", string)
    }

    @Test
    fun `convert to coordinates`() {
        val string = "3.45,-5.1"
        assertEquals(listOf(3.45, -5.1), Converter.convertToCoordinates(string))
    }

    @Test
    fun `convert set to string`() {
        val string = Converter.convertSetToString(mutableSetOf(1, 3))

        assertEquals("1,3", string)
        assertEquals("", Converter.convertSetToString(mutableSetOf()))
    }

    @Test
    fun `convert to set`() {
        val string = "1,3,4"

        assertEquals(mutableSetOf(1, 3, 4), Converter.convertToSet(string))
    }

    @Test
    fun `convert to empty set`() {
        assertEquals(mutableSetOf<Int>(), Converter.convertToSet(""))
    }
}
