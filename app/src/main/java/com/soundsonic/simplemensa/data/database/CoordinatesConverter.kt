package com.soundsonic.simplemensa.data.database

import androidx.room.TypeConverter

object CoordinatesConverter {
    private const val delimiter = ","

    @TypeConverter
    @JvmStatic
    fun convertToString(coordinates: List<Double>): String = coordinates.joinToString(delimiter)

    @TypeConverter
    @JvmStatic
    fun convertToCoordinates(coordinates: String): List<Double> {
        return coordinates.split(delimiter).map { it.toDouble() }
    }
}
