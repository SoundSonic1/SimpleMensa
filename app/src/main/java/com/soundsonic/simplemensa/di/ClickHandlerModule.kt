package com.soundsonic.simplemensa.di

import com.soundsonic.simplemensa.ui.main.listener.CanteenHandler
import com.soundsonic.simplemensa.ui.main.listener.CanteenHandlerImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ClickHandlerModule {

    @Binds
    abstract fun provideCanteenHandler(canteenHandler: CanteenHandlerImpl): CanteenHandler
}
