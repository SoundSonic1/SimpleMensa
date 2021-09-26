package com.soundsonic.simplemensa.di

import com.soundsonic.simplemensa.data.api.OpenMensaApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://api.studentenwerk-dresden.de/openmensa/v2/"

    @Provides
    @Singleton
    fun provideOpenMensaApi(): OpenMensaApi = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(OpenMensaApi::class.java)
}
