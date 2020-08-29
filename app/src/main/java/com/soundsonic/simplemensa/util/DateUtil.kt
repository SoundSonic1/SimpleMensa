package com.soundsonic.simplemensa.util

import java.util.Calendar
import java.util.Date

fun createDatesForThreeDays(): List<Date> {
    val calendar = Calendar.getInstance()

    return listOf(
        calendar.time,
        calendar.apply { add(Calendar.DAY_OF_YEAR, 1) }.time,
        calendar.apply { add(Calendar.DAY_OF_YEAR, 1) }.time
    )
}
