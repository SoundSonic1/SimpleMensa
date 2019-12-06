package com.soundsonic.simplemensa.di

import androidx.fragment.app.viewModels
import com.soundsonic.simplemensa.data.repositories.CanteenRepository
import com.soundsonic.simplemensa.ui.base.BaseVMFactory
import com.soundsonic.simplemensa.ui.main.fragment.CanteenFragment
import com.soundsonic.simplemensa.ui.main.viewmodel.CanteenViewModel
import dagger.Module
import dagger.Provides

@Module
class CanteenFragmentModule {

    @Provides
    fun provideCanteenViewModel(
        canteenFragment: CanteenFragment,
        canteenRepository: CanteenRepository
    ): CanteenViewModel {
        val vm by canteenFragment.viewModels<CanteenViewModel> {
            BaseVMFactory {
                CanteenViewModel(canteenRepository)
            }
        }
        return vm
    }

    @Provides
    fun supportFragmentManager(canteenFragment: CanteenFragment) =
        canteenFragment.activity!!.supportFragmentManager
}
