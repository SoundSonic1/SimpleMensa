package com.soundsonic.simplemensa.di

import com.soundsonic.simplemensa.data.adapter.MealAdapter
import com.soundsonic.simplemensa.data.api.OpenMensaApi
import com.soundsonic.simplemensa.util.Constants.BASE_URL
import com.squareup.moshi.Moshi
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

    @Provides
    @Singleton
    fun provideMoshi(mealAdapter: MealAdapter): Moshi = Moshi.Builder().add(mealAdapter).build()

    @Provides
    @Singleton
    fun provideOpenMensaApi(moshi: Moshi): OpenMensaApi = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()
        .create(OpenMensaApi::class.java)
}
