package com.soundsonic.simplemensa.di

import com.soundsonic.simplemensa.data.model.Meal
import com.soundsonic.simplemensa.ui.mealdetail.MealDetailFragment
import com.soundsonic.simplemensa.util.Constants.MEAL_KEY
import dagger.Module
import dagger.Provides

@Module
object MealDetailFragmentModule {

    @Provides
    fun provideMeal(mealDetailFragment: MealDetailFragment): Meal =
        mealDetailFragment.arguments!!.getParcelable(MEAL_KEY)!!
}
