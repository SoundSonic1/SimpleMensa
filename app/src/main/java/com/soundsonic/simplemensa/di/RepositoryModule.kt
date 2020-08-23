package com.soundsonic.simplemensa.di

import com.soundsonic.simplemensa.data.repositories.CanteenRepository
import com.soundsonic.simplemensa.data.repositories.CanteenRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideCanteenRepository(
        canteenRepositoryImpl: CanteenRepositoryImpl
    ): CanteenRepository
}
