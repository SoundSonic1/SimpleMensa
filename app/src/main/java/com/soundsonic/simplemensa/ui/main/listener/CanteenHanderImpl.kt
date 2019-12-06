package com.soundsonic.simplemensa.ui.main.listener

import androidx.fragment.app.FragmentManager
import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.ui.meals.fragment.MealsFragment
import com.soundsonic.simplemensa.util.replaceFragment

class CanteenHanderImpl(private val fm: FragmentManager) : CanteenHandler{

    override fun onCanteenClicked(canteen: Canteen) {
        replaceFragment(fm, R.id.mainContent, MealsFragment.newInstance(canteen))
    }
}
