package com.soundsonic.simplemensa.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.databinding.FragmentHomeBinding
import com.soundsonic.simplemensa.ui.home.adapter.CanteenListAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var customItemAnimator: RecyclerView.ItemAnimator

    private val canteenViewModel: CanteenViewModel by viewModels()
    private val viewModel: UserViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.canteenViewModel = canteenViewModel
        binding.userViewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            canteenRecyclerView.apply {
                adapter = CanteenListAdapter(object : CanteenListAdapter.CanteenListener {

                    override fun onCanteenClicked(canteen: Canteen) {
                        val action = HomeFragmentDirections.actionNavHomeToTabFragment(canteen, canteen.name)
                        findNavController().navigate(action)
                    }

                    override fun onFavouriteClicked(v: View, canteen: Canteen) {
                        v.isSelected = !v.isSelected
                        if (v.isSelected) {
                            viewModel.addCanteen(canteen)
                        } else {
                            viewModel.removeCanteen(canteen)
                        }
                    }
                })
                layoutManager = LinearLayoutManager(requireContext())
                itemAnimator = customItemAnimator
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
