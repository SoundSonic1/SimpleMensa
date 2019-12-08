package com.soundsonic.simplemensa.di

import androidx.fragment.app.viewModels
import com.soundsonic.simplemensa.data.repositories.CanteenRepository
import com.soundsonic.simplemensa.ui.base.BaseVMFactory
import com.soundsonic.simplemensa.ui.map.fragment.MapFragment
import com.soundsonic.simplemensa.ui.map.viewmodel.MapViewModel
import dagger.Module
import dagger.Provides

@Module
object MapFragmentModule {
    @Provides
    fun provideMapViewModel(
        mapFragment: MapFragment,
        canteenRepository: CanteenRepository
    ): MapViewModel {
        val vm by mapFragment.viewModels<MapViewModel> {
            BaseVMFactory { MapViewModel(canteenRepository) }
        }
        return vm
    }
}
