package com.soundsonic.simplemensa.di

import com.soundsonic.simplemensa.data.repositories.CanteenRepository
import com.soundsonic.simplemensa.data.repositories.CanteenRepositoryImpl
import com.soundsonic.simplemensa.data.repositories.MealRepository
import com.soundsonic.simplemensa.data.repositories.MealRepositoryImpl
import com.soundsonic.simplemensa.data.repositories.UserRepository
import com.soundsonic.simplemensa.data.repositories.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideCanteenRepository(canteenRepositoryImpl: CanteenRepositoryImpl): CanteenRepository

    @Binds
    abstract fun provideMealRepository(mealRepositoryImpl: MealRepositoryImpl): MealRepository

    @Binds
    abstract fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}
