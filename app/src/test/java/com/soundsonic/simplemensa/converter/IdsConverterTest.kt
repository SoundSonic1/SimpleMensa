package com.soundsonic.simplemensa.converter

import com.soundsonic.simplemensa.data.database.IdsConverter
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class IdsConverterTest {

    @Test
    fun `convert to string`() {
        val string = IdsConverter.convertToString(mutableSetOf(1, 3))

        assertEquals("1,3", string)
        assertEquals("", IdsConverter.convertToString(mutableSetOf()))
    }

    @Test
    fun `convert to set`() {
        val string = "1,3,4"

        assertEquals(mutableSetOf(1, 3, 4), IdsConverter.convertToSet(string))
    }

    @Test
    fun `convert to empty set`() {
        assertEquals(mutableSetOf<Int>(), IdsConverter.convertToSet(""))
    }
}
