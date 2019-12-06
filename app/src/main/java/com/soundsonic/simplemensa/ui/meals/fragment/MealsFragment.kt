package com.soundsonic.simplemensa.ui.meals.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager

import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.databinding.MealsFragmentBinding
import com.soundsonic.simplemensa.ui.meals.MealsListAdapter
import com.soundsonic.simplemensa.ui.meals.viewmodel.MealsViewModel
import com.soundsonic.simplemensa.util.Constants.CANTEEN_KEY
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.meals_fragment.*
import javax.inject.Inject

class MealsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: MealsViewModel

    @Inject
    lateinit var mealsListAdapter: MealsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: MealsFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.meals_fragment, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewMeals.adapter = mealsListAdapter
        recyclerViewMeals.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {
        fun newInstance(canteen: Canteen) = MealsFragment().apply {
            arguments = bundleOf(CANTEEN_KEY to canteen)
        }
    }
}
