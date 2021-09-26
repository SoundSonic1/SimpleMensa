package com.soundsonic.simplemensa.di

import com.soundsonic.simplemensa.data.adapter.MealAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SerializationModule {
    @Provides
    @Singleton
    fun provideMoshi(mealAdapter: MealAdapter): Moshi = Moshi.Builder().add(mealAdapter).build()
}
