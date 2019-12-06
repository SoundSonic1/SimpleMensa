package com.soundsonic.simplemensa.di

import com.soundsonic.simplemensa.ui.main.fragment.CanteenFragment
import com.soundsonic.simplemensa.ui.meals.fragment.MealsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsBindingModule {

    @ContributesAndroidInjector(modules = [CanteenFragmentModule::class, ClickHandlerModule::class])
    abstract fun canteenFragment(): CanteenFragment

    @ContributesAndroidInjector(modules = [MealsFragmentModule::class])
    abstract fun mealsFragment(): MealsFragment
}
