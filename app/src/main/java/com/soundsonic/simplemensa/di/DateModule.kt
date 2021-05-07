package com.soundsonic.simplemensa.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DateModule {

    @Provides
    @Singleton
    fun provideSimpleDateFormat(): SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY)
}
