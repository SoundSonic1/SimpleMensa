package com.soundsonic.simplemensa.ui.mealdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.soundsonic.simplemensa.data.model.Meal
import com.soundsonic.simplemensa.databinding.FragmentMealDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealDetailFragment : Fragment() {

    private var _binding: FragmentMealDetailBinding? = null
    private val binding: FragmentMealDetailBinding get() = _binding!!
    private val viewModel: MealDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMealDetailBinding.inflate(inflater, container, false)
        val meal: Meal = arguments?.getParcelable("meal")!!
        viewModel.setMeal(meal)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
