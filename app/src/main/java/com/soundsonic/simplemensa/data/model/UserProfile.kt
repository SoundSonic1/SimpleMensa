package com.soundsonic.simplemensa.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserProfile(
    @PrimaryKey val id: Int,
    val favouriteCanteenIds: MutableSet<Int> = mutableSetOf()
)
