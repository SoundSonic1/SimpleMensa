package com.soundsonic.simplemensa.ui.meals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.ui.meals.adapter.MealPagerAdapter
import com.soundsonic.simplemensa.util.createDatesForThreeDays
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_tab.*
import java.text.SimpleDateFormat
import javax.inject.Inject

@AndroidEntryPoint
class TabFragment : Fragment() {

    @Inject
    lateinit var sdf: SimpleDateFormat

    private val args: TabFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view_pager.adapter = MealPagerAdapter(
            args.canteen,
            createDatesForThreeDays(),
            childFragmentManager
        )
        tab_layout_meals.setupWithViewPager(view_pager)
    }
}
