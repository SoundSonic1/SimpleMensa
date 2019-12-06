package com.soundsonic.simplemensa.di

import androidx.fragment.app.viewModels
import com.soundsonic.simplemensa.data.api.OpenMensaApi
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
        openMensaApi: OpenMensaApi
    ): CanteenViewModel {
        val vm by canteenFragment.viewModels<CanteenViewModel> {
            BaseVMFactory {
                CanteenViewModel(
                    openMensaApi
                )
            }
        }
        return vm
    }

    @Provides
    fun supportFragmentManager(canteenFragment: CanteenFragment) =
        canteenFragment.activity!!.supportFragmentManager
}
