package com.soundsonic.simplemensa.di

import com.soundsonic.simplemensa.data.repositories.CanteenRepository
import com.soundsonic.simplemensa.data.repositories.CanteenRepositoryImpl
import com.soundsonic.simplemensa.data.repositories.MealsRepository
import com.soundsonic.simplemensa.data.repositories.MealsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideCanteenRepo(canteenRepositoryImpl: CanteenRepositoryImpl): CanteenRepository

    @Binds
    abstract fun provideMealsRepo(mealsRepositoryImpl: MealsRepositoryImpl): MealsRepository
}
