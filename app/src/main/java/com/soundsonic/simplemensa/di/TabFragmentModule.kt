package com.soundsonic.simplemensa.di

import androidx.fragment.app.FragmentManager
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.ui.meals.fragment.TabFragment
import com.soundsonic.simplemensa.util.Constants.CANTEEN_KEY
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
object TabFragmentModule {

    @Provides
    @Named("childFragmentManager")
    fun provideFragmentManager(tabFragment: TabFragment): FragmentManager =
        tabFragment.childFragmentManager

    @Provides
    @Named("CanteenTab")
    fun provideCanteen(tabFragment: TabFragment): Canteen =
        tabFragment.arguments!!.getParcelable(CANTEEN_KEY)!!
}
