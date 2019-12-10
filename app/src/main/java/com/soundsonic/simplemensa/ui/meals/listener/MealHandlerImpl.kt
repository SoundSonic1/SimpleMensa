package com.soundsonic.simplemensa.ui.meals.listener

import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.data.model.Meal
import com.soundsonic.simplemensa.ui.mealdetail.MealDetailFragment
import com.soundsonic.simplemensa.ui.meals.fragment.MealsFragment
import com.soundsonic.simplemensa.util.replaceFragment
import javax.inject.Inject

class MealHandlerImpl @Inject constructor(
    mealsFragment: MealsFragment
) : MealHandler {

    private val fm = mealsFragment.requireActivity().supportFragmentManager

    override fun onMealClicked(meal: Meal) {
        replaceFragment(fm, R.id.mainContent, MealDetailFragment.newInstance(meal))
    }
}
