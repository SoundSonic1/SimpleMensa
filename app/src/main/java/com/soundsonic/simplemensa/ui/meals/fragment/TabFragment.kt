package com.soundsonic.simplemensa.ui.meals.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.ui.meals.adapter.MealsPagerAdapter
import com.soundsonic.simplemensa.util.Constants.CANTEEN_KEY
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import javax.inject.Named
import kotlinx.android.synthetic.main.tab_fragment.*

class TabFragment : DaggerFragment() {

    @Inject
    @Named("CanteenTab")
    lateinit var canteen: Canteen

    @Inject
    lateinit var mealsPagerAdapter: MealsPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tab_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = canteen.name

        viewPagerDates.adapter = mealsPagerAdapter
        tabLayoutDates.setupWithViewPager(viewPagerDates)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
    }

    companion object {
        fun newInstance(canteen: Canteen) = TabFragment().apply {
            arguments = bundleOf(CANTEEN_KEY to canteen)
        }
    }
}
