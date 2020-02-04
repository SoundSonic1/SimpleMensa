package com.soundsonic.simplemensa.ui.emeal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.databinding.EmealFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class EmealFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: EmealViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: EmealFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.emeal_fragment, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.menu_balance)
    }
}
