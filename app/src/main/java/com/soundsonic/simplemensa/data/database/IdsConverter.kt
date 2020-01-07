package com.soundsonic.simplemensa.data.database

import androidx.room.TypeConverter

object IdsConverter {
    private const val delimiter = ","

    @TypeConverter
    @JvmStatic
    fun convertToString(ids: MutableSet<Long>): String = ids.joinToString(delimiter)

    @TypeConverter
    @JvmStatic
    fun convertToSet(ids: String): MutableSet<Long> {
        return if (ids.isNotEmpty()) {
            ids.split(delimiter).mapTo(LinkedHashSet()) { it.toLong() }
        } else {
            mutableSetOf()
        }
    }
}
