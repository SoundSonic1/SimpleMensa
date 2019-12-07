package com.soundsonic.simplemensa.di

import android.app.Application
import com.soundsonic.simplemensa.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBindingModule::class,
        FragmentsBindingModule::class,
        CanteenFragmentModule::class,
        TabFragmentModule::class,
        MealsFragmentModule::class,
        MealDetailFragmentModule::class
    ]
)

interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(app: Application): Builder
        fun build(): AppComponent
    }
}
