package com.soundsonic.simplemensa.util

import com.soundsonic.simplemensa.data.api.OpenMensaApi
import com.soundsonic.simplemensa.data.database.CanteenDao
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


fun provideMockCanteenDao(): CanteenDao = object : CanteenDao {

    override suspend fun deleteAll() {
    }
    override suspend fun findAll(): List<Canteen> {
        return emptyList()
    }
    override suspend fun insert(canteen: Canteen) {
    }
    override suspend fun insertAll(canteens: List<Canteen>) {
    }
}

fun provideApi(): OpenMensaApi =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(OpenMensaApi::class.java)

fun provideMockApi(): OpenMensaApi =
    Retrofit.Builder()
        .baseUrl("https://www.google.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(OpenMensaApi::class.java)
