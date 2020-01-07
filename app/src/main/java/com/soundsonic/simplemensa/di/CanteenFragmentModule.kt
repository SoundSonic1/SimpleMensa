package com.soundsonic.simplemensa.di

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.soundsonic.simplemensa.data.repositories.CanteenRepository
import com.soundsonic.simplemensa.ui.base.BaseVMFactory
import com.soundsonic.simplemensa.ui.main.fragment.CanteenFragment
import com.soundsonic.simplemensa.ui.main.viewmodel.CanteenViewModel
import com.soundsonic.simplemensa.ui.main.viewmodel.UserProfileViewModel
import dagger.Module
import dagger.Provides

@Module
object CanteenFragmentModule {

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
    fun provideUserProfileViewModel(canteenFragment: CanteenFragment): UserProfileViewModel {
        val vm by canteenFragment.activityViewModels<UserProfileViewModel>()
        return vm
    }
}
