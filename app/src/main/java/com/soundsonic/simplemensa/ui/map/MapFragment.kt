package com.soundsonic.simplemensa.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions
import com.mapbox.mapboxsdk.plugins.localization.LocalizationPlugin
import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.databinding.FragmentMapBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : Fragment() {

    companion object {
        private const val ICON_ID = "restaurant-11"
    }

    private var _binding: FragmentMapBinding? = null
    private val binding: FragmentMapBinding get() = _binding!!
    private val mapViewModel: MapViewModel by viewModels()
    private lateinit var map: MapboxMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.menu_map)

        with(binding) {
            mapView.onCreate(savedInstanceState)
            mapView.getMapAsync { mapboxMap ->
                map = mapboxMap
                map.setStyle(Style.MAPBOX_STREETS) { style ->
                    val localizationPlugin = LocalizationPlugin(mapView, mapboxMap, style)
                    try {
                        localizationPlugin.matchMapLanguageWithDeviceDefault()
                    } catch (e: RuntimeException) {
                        e.printStackTrace()
                    }
                    val symbolManager = SymbolManager(mapView, map, style).apply {
                        iconAllowOverlap = true
                        iconIgnorePlacement = true
                    }
                    setUpMarkers(symbolManager)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.mapView.onDestroy()
    }

    private fun setUpMarkers(symbolManager: SymbolManager) {
        mapViewModel.canteens.observe(viewLifecycleOwner, { canteens ->
            canteens.forEach { canteen ->
                symbolManager.create(
                    SymbolOptions()
                        .withIconImage(ICON_ID)
                        .withLatLng(LatLng(canteen.coordinates[0], canteen.coordinates[1]))
                        .withIconSize(2.0f)
                        .withTextField(canteen.name)
                        .withTextOffset(arrayOf(0f, 1.5f))
                )
            }
        })
    }
}
