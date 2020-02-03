package com.soundsonic.simplemensa.di

import androidx.fragment.app.activityViewModels
import com.soundsonic.simplemensa.ui.emeal.EmealFragment
import com.soundsonic.simplemensa.ui.emeal.EmealViewModel
import dagger.Module
import dagger.Provides

@Module
object EmealFragmentModule {

    @Provides
    fun provideEmealViewModel(emealFragment: EmealFragment): EmealViewModel {
        val vm by emealFragment.activityViewModels<EmealViewModel>()
        return vm
    }
}
