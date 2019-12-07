package com.soundsonic.simplemensa.ui.main.listener

import androidx.fragment.app.FragmentManager
import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.ui.meals.fragment.TabFragment
import com.soundsonic.simplemensa.util.replaceFragment
import javax.inject.Inject
import javax.inject.Named

class CanteenHandlerImpl @Inject constructor(
    @Named("CanteenFM") private val fm: FragmentManager
) : CanteenHandler {

    override fun onCanteenClicked(canteen: Canteen) {
        replaceFragment(fm, R.id.mainContent, TabFragment.newInstance(canteen))
    }
}
