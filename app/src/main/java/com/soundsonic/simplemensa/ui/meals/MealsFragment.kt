package com.soundsonic.simplemensa.ui.meals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.data.model.Meal
import com.soundsonic.simplemensa.databinding.FragmentMealsBinding
import com.soundsonic.simplemensa.ui.meals.adapter.MealListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_meals.*
import java.util.Date
import javax.inject.Inject

@AndroidEntryPoint
class MealsFragment : Fragment() {

    private val viewModel: MealsViewModel by viewModels()

    @Inject
    lateinit var customItemAnimator: RecyclerView.ItemAnimator

    companion object {
        private const val CANTEEN_KEY = "CANTEEN_KEY"
        private const val DATE_KEY = "DATE_KEY"

        fun newInstance(canteen: Canteen, date: Date) = MealsFragment().apply {
            arguments = bundleOf(
                CANTEEN_KEY to canteen,
                DATE_KEY to date
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val canteen: Canteen = requireArguments().getParcelable(CANTEEN_KEY)!!
        val date: Date = requireArguments().getSerializable(DATE_KEY)!! as Date

        viewModel.getMealsForDate(canteen.id, date)

        val binding: FragmentMealsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_meals, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        meal_recycler_view.apply {
            adapter = MealListAdapter(object : MealListAdapter.OnClickListener {
                override fun onMealClicked(v: View, meal: Meal) {
                }
            })
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = customItemAnimator
        }

    }
}
