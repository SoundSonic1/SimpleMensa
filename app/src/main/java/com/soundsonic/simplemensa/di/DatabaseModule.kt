package com.soundsonic.simplemensa.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.soundsonic.simplemensa.data.database.AppDatabase
import com.soundsonic.simplemensa.data.database.CanteenDao
import com.soundsonic.simplemensa.data.database.UserProfileDao
import com.soundsonic.simplemensa.util.Constants.CANTEEN_DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideSharedPrefs(app: Application): SharedPreferences = app
        .getSharedPreferences("com.soundsonic.simplemensa.PREFERENCE_FILE_KEY", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, CANTEEN_DB).build()

    @Provides
    @Singleton
    fun provideCanteenDao(appDatabase: AppDatabase): CanteenDao = appDatabase.canteenDao()

    @Provides
    @Singleton
    fun provideUserProfileDao(appDatabase: AppDatabase): UserProfileDao =
        appDatabase.userProfileDao()
}
