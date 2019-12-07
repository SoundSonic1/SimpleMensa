package com.soundsonic.simplemensa.ui.mealdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.data.model.Meal
import com.soundsonic.simplemensa.databinding.MealDetailFragmentBinding
import com.soundsonic.simplemensa.util.Constants.MEAL_KEY
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MealDetailFragment : DaggerFragment() {

    @Inject
    lateinit var meal: Meal

    companion object {
        fun newInstance(meal: Meal) = MealDetailFragment().apply {
            arguments = bundleOf(MEAL_KEY to meal)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: MealDetailFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.meal_detail_fragment, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.meal = meal

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        meal.category?.let {
            (activity as AppCompatActivity).supportActionBar?.title = it
        }
    }
}
