package com.soundsonic.simplemensa.util

import com.soundsonic.simplemensa.data.api.OpenMensaApi
import com.soundsonic.simplemensa.data.database.CanteenDao
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.data.repositories.CanteenRepository
import com.soundsonic.simplemensa.data.repositories.CanteenRepositoryImpl
import com.soundsonic.simplemensa.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun provideMockCanteenDao(): CanteenDao = object : CanteenDao {

    override suspend fun insertAll(canteens: List<Canteen>) {
    }

    override suspend fun findAll(): List<Canteen> {
        return emptyList()
    }
}

fun provideApi(): OpenMensaApi =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(OpenMensaApi::class.java)

fun provideCanteenRepo(): CanteenRepository =
    CanteenRepositoryImpl(provideApi(), provideMockCanteenDao())
