package com.soundsonic.simplemensa.di

import com.soundsonic.simplemensa.ui.main.fragment.CanteenFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsBindingModule {

    @ContributesAndroidInjector(modules = [CanteenFragmentModule::class])
    abstract fun canteenFragment(): CanteenFragment
}