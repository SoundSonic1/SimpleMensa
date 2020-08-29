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
import androidx.recyclerview.widget.RecyclerView
import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.databinding.FragmentHomeBinding
import com.soundsonic.simplemensa.ui.home.adapter.CanteenListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var customItemAnimator: RecyclerView.ItemAnimator

    private val canteenViewModel: CanteenViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.canteenViewModel = canteenViewModel
        binding.userViewModel = userViewModel

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

                override fun onFavouriteClicked(v: View, canteen: Canteen) {
                    v.isSelected = !v.isSelected
                    if (v.isSelected) {
                        userViewModel.addCanteen(canteen)
                    } else {
                        userViewModel.removeCanteen(canteen)
                    }
                }
            })
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = customItemAnimator
        }
    }
}
