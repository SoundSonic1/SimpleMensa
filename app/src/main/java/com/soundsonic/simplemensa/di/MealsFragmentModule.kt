package com.soundsonic.simplemensa.di

import androidx.fragment.app.viewModels
import com.soundsonic.simplemensa.data.api.OpenMensaApi
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.ui.meals.fragment.MealsFragment
import com.soundsonic.simplemensa.ui.meals.viewmodel.MealsViewModel
import com.soundsonic.simplemensa.ui.base.BaseVMFactory
import com.soundsonic.simplemensa.util.Constants.CANTEEN_KEY
import dagger.Module
import dagger.Provides

@Module
class MealsFragmentModule {

    @Provides
    fun provideCanteen(mealsFragment: MealsFragment): Canteen =
        mealsFragment.arguments!!.getParcelable(CANTEEN_KEY)!!

    @Provides
    fun provideMealsViewModel(
        mealsFragment: MealsFragment,
        canteen: Canteen,
        openMensaApi: OpenMensaApi
    ): MealsViewModel {

        val vm by mealsFragment.viewModels<MealsViewModel> {
            BaseVMFactory {
                MealsViewModel(
                    openMensaApi,
                    canteen
                )
            }
        }
        return vm
    }
}
