package com.soundsonic.simplemensa.ui.meals.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.ui.meals.fragment.MealsFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject
import javax.inject.Named

class MealsPagerAdapter @Inject constructor(
    @Named("childFragmentManager") fm: FragmentManager,
    private val dateFormatOpenMensa: SimpleDateFormat,
    @Named("CanteenTab") private val canteen: Canteen
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val calendar = Calendar.getInstance()
    private val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.GERMANY)

    private val dates = listOf<String>(
        dateFormatOpenMensa.format(calendar.time),
        dateFormatOpenMensa.format(calendar.apply { add(Calendar.DAY_OF_YEAR, 1) }.time),
        dateFormatOpenMensa.format(calendar.apply { add(Calendar.DAY_OF_YEAR, 1) }.time)
    )

    override fun getCount(): Int = dates.size

    override fun getItem(position: Int): Fragment =
        MealsFragment.newInstance(canteen, dates[position])

    override fun getPageTitle(position: Int): CharSequence? {
        val date = dateFormatOpenMensa.parse(dates[position])!!
        return sdf.format(date)
    }
}
