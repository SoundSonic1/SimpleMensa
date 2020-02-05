package com.soundsonic.simplemensa.formatter

import com.soundsonic.simplemensa.util.Formatter
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FormatterTest {

    @Test
    fun `convert double to euro currency`() {
        assertEquals("3,50 €", Formatter.euroCurrency.format(3.500))
        assertEquals("5,00 €", Formatter.euroCurrency.format(5.0))
    }
}
