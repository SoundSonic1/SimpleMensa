package com.soundsonic.simplemensa.di

import android.app.Application
import androidx.room.Room
import com.soundsonic.simplemensa.data.database.CanteenDao
import com.soundsonic.simplemensa.data.database.CanteenDatabase
import com.soundsonic.simplemensa.util.Constants.CANTEEN_DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideCanteenDao(app: Application): CanteenDao = Room.databaseBuilder(
        app,
        CanteenDatabase::class.java,
        CANTEEN_DB
    ).build().canteenDao()
}
