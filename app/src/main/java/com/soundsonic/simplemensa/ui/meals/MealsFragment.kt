package com.soundsonic.simplemensa.ui.meals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.data.model.Canteen
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint
class MealsFragment : Fragment() {

    private val viewModel: MealsViewModel by viewModels()

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

        return inflater.inflate(R.layout.fragment_meals, container, false)
    }
}
