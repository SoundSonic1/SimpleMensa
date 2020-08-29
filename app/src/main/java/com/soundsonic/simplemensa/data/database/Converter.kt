package com.soundsonic.simplemensa.data.database

import androidx.room.TypeConverter

object Converter {
    private const val delimiter = ","

    @TypeConverter
    @JvmStatic
    fun convertListToString(coordinates: List<Double>): String = coordinates.joinToString(delimiter)

    @TypeConverter
    @JvmStatic
    fun convertSetToString(ids: MutableSet<Int>): String = ids.joinToString(delimiter)

    @TypeConverter
    @JvmStatic
    fun convertToCoordinates(coordinates: String): List<Double> {
        return coordinates.split(delimiter).map { it.toDouble() }
    }

    @TypeConverter
    @JvmStatic
    fun convertToSet(ids: String): MutableSet<Int> {
        return if (ids.isNotEmpty()) {
            ids.split(delimiter).mapTo(LinkedHashSet()) { it.toInt() }
        } else {
            mutableSetOf()
        }
    }
}
