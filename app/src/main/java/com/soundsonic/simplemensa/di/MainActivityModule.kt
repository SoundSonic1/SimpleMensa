package com.soundsonic.simplemensa.di

import androidx.activity.viewModels
import com.soundsonic.simplemensa.data.database.UserProfileDao
import com.soundsonic.simplemensa.ui.base.BaseVMFactory
import com.soundsonic.simplemensa.ui.emeal.EmealViewModel
import com.soundsonic.simplemensa.ui.main.MainActivity
import com.soundsonic.simplemensa.ui.main.viewmodel.UserProfileViewModel
import dagger.Module
import dagger.Provides

@Module
object MainActivityModule {

    @Provides
    fun provideUserProfileVM(
        userProfileDao: UserProfileDao,
        mainActivity: MainActivity
    ): UserProfileViewModel {
        val vm by mainActivity.viewModels<UserProfileViewModel> {
            BaseVMFactory { UserProfileViewModel(userProfileDao) }
        }
        return vm
    }

    @Provides
    fun provideEmealViewModel(mainActivity: MainActivity): EmealViewModel {
        val vm by mainActivity.viewModels<EmealViewModel>()
        return vm
    }
}
