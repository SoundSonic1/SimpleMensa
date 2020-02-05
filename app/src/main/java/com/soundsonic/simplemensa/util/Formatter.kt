package com.soundsonic.simplemensa.util

import java.text.NumberFormat
import java.util.Locale

object Formatter {
    val euroCurrency = NumberFormat.getCurrencyInstance(Locale.GERMANY).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
    }
}
