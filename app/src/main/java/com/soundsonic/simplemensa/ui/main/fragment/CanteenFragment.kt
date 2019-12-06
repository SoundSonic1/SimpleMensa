package com.soundsonic.simplemensa.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.databinding.CanteenFragmentBinding
import com.soundsonic.simplemensa.ui.main.adapter.CanteenListAdapter
import com.soundsonic.simplemensa.ui.main.viewmodel.CanteenViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import kotlinx.android.synthetic.main.canteen_fragment.*

class CanteenFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: CanteenViewModel

    @Inject
    lateinit var canteenListAdapter: CanteenListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: CanteenFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.canteen_fragment, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewMain.adapter = canteenListAdapter
        recyclerViewMain.layoutManager = LinearLayoutManager(requireContext())
    }
}
