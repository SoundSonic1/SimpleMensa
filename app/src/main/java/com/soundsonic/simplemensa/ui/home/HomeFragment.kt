package com.soundsonic.simplemensa.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.databinding.FragmentHomeBinding
import com.soundsonic.simplemensa.ui.home.adapter.CanteenListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val canteenViewModel: CanteenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = canteenViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        canteen_recycler_view.apply {
            adapter = CanteenListAdapter(object : CanteenListAdapter.CanteenListener {
                override fun onCanteenClicked(v: View, canteen: Canteen) {
                    val action = HomeFragmentDirections.actionNavHomeToTabFragment(canteen, canteen.name)
                    v.findNavController().navigate(action)
                }
            })
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}
