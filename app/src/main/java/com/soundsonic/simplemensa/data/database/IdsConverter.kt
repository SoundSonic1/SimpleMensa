package com.soundsonic.simplemensa.data.database

import androidx.room.TypeConverter

object IdsConverter {
    private const val delimiter = ","

    @TypeConverter
    @JvmStatic
    fun convertToString(ids: MutableSet<Int>): String = ids.joinToString(delimiter)

    @TypeConverter
    @JvmStatic
    fun convertToSet(ids: String): MutableSet<Int> {
        return ids.split(delimiter).mapTo(LinkedHashSet()) { it.toInt() }
    }
}
