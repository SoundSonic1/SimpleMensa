package com.soundsonic.simplemensa.ui.meals.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.ui.meals.fragment.MealsFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Named

class MealsPagerAdapter @Inject constructor(
    @Named("childFragmentManager") fm: FragmentManager,
    simpleDateFormat: SimpleDateFormat,
    @Named("CanteenTab") private val canteen: Canteen
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val calendar = Calendar.getInstance()

    private val dates = listOf<String>(
        simpleDateFormat.format(calendar.time),
        simpleDateFormat.format(calendar.apply { add(Calendar.DAY_OF_YEAR, 1) }.time),
        simpleDateFormat.format(calendar.apply { add(Calendar.DAY_OF_YEAR, 1) }.time)
    )

    override fun getCount(): Int = 3

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MealsFragment.newInstance(canteen, dates[position])
            1 -> MealsFragment.newInstance(canteen, dates[position])
            else -> MealsFragment.newInstance(canteen, dates[position])
        }

    override fun getPageTitle(position: Int): CharSequence? = dates[position]
}
