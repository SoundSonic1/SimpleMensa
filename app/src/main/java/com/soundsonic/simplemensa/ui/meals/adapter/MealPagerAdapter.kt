package com.soundsonic.simplemensa.ui.meals.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.ui.meals.MealsFragment
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MealPagerAdapter(
    private val canteen: Canteen,
    private val dates: List<Date>,
    fm: FragmentManager
) : FragmentPagerAdapter(
    fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    private val sdf = SimpleDateFormat("EEE, MMM d", Locale.getDefault())

    override fun getCount(): Int = dates.size

    override fun getItem(position: Int): Fragment = MealsFragment.newInstance(canteen, dates[position])

    override fun getPageTitle(position: Int): CharSequence? {
        return sdf.format(dates[position])
    }
}
