package com.soundsonic.simplemensa.ui.meals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.soundsonic.simplemensa.databinding.FragmentTabBinding
import com.soundsonic.simplemensa.ui.meals.adapter.MealPagerAdapter
import com.soundsonic.simplemensa.util.createDatesForThreeDays
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TabFragment : Fragment() {

    private var _binding: FragmentTabBinding? = null
    private val binding: FragmentTabBinding get() = _binding!!

    private val args: TabFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewPager.adapter = MealPagerAdapter(
                args.canteen,
                createDatesForThreeDays(),
                childFragmentManager
            )
            tabLayoutMeals.setupWithViewPager(viewPager)
        }
    }
}
