package com.soundsonic.simplemensa.util

import com.soundsonic.simplemensa.data.api.OpenMensaApi
import com.soundsonic.simplemensa.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

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
