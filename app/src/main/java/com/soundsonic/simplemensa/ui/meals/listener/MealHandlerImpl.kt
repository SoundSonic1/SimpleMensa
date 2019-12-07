package com.soundsonic.simplemensa.ui.meals.listener

import androidx.fragment.app.FragmentManager
import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.data.model.Meal
import com.soundsonic.simplemensa.ui.mealdetail.MealDetailFragment
import com.soundsonic.simplemensa.util.replaceFragment
import javax.inject.Inject
import javax.inject.Named

class MealHandlerImpl @Inject constructor(
    @Named("MealFM") private val fm: FragmentManager
) : MealHandler {

    override fun onMealClicked(meal: Meal) {
        replaceFragment(fm, R.id.mainContent, MealDetailFragment.newInstance(meal))
    }
}
