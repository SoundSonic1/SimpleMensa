package com.soundsonic.simplemensa.ui.meals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.soundsonic.simplemensa.R
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class MealsFragment : Fragment() {

    private val viewModel: MealsViewModel by viewModels()
    private val args: MealsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.getMealsForDate(args.canteen.id, Calendar.getInstance().time)
        return inflater.inflate(R.layout.fragment_meals, container, false)
    }
}
