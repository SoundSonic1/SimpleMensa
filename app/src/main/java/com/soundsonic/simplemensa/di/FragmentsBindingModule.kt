package com.soundsonic.simplemensa.di

import com.soundsonic.simplemensa.ui.main.fragment.CanteenFragment
import com.soundsonic.simplemensa.ui.meals.fragment.MealsFragment
import com.soundsonic.simplemensa.ui.meals.fragment.TabFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsBindingModule {

    @ContributesAndroidInjector(modules = [
        CanteenFragmentModule::class,
        RepositoryModule::class,
        ClickHandlerModule::class
    ])
    abstract fun canteenFragment(): CanteenFragment

    @ContributesAndroidInjector(modules = [MealsFragmentModule::class, RepositoryModule::class])
    abstract fun mealsFragment(): MealsFragment

    @ContributesAndroidInjector(modules = [TabFragmentModule::class])
    abstract fun tabFragment(): TabFragment
}
