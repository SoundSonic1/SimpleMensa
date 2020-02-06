package com.soundsonic.simplemensa.ui.map.fragment

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mapbox.android.core.location.LocationEngineCallback
import com.mapbox.android.core.location.LocationEngineResult
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions
import com.mapbox.mapboxsdk.plugins.localization.LocalizationPlugin
import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.ui.map.viewmodel.MapViewModel
import dagger.android.support.DaggerFragment
import java.lang.Exception
import javax.inject.Inject
import kotlinx.android.synthetic.main.map_fragment.*

class MapFragment : DaggerFragment(), PermissionsListener {

    @Inject
    lateinit var mapViewModel: MapViewModel

    private lateinit var permissionsManager: PermissionsManager
    private lateinit var map: MapboxMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.map_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.menu_map)

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { mapboxMap: MapboxMap ->
            map = mapboxMap
            map.setStyle(Style.MAPBOX_STREETS) { style ->
                val localizationPlugin = LocalizationPlugin(mapView, mapboxMap, style)
                try {
                    localizationPlugin.matchMapLanguageWithDeviceDefault()
                } catch (e: RuntimeException) {
                    e.printStackTrace()
                }

                enableLocationComponent(style)

                val symbolManager = SymbolManager(mapView, map, style).apply {
                    iconAllowOverlap = true
                    iconIgnorePlacement = true
                }
                setUpMarkers(symbolManager)
            }
        }
    }

    private fun setUpMarkers(symbolManager: SymbolManager) {
        mapViewModel.canteens.observe(viewLifecycleOwner, Observer { canteens ->
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onPermissionResult(granted: Boolean) {
        if (granted) {
            map.getStyle {
                enableLocationComponent(it)
            }
        } else {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?) {
        Toast.makeText(requireContext(), R.string.location_explanation, Toast.LENGTH_LONG).show()
    }

    private fun enableLocationComponent(style: Style) {
        if (PermissionsManager.areLocationPermissionsGranted(requireContext())) {
            map.locationComponent.apply {
                activateLocationComponent(
                    LocationComponentActivationOptions.builder(requireContext(), style).build()
                )
                isLocationComponentEnabled = true
                cameraMode = CameraMode.TRACKING
                renderMode = RenderMode.COMPASS
            }
            getLastLocation()
        } else {
            permissionsManager = PermissionsManager(this).apply {
                requestLocationPermissions(requireActivity())
            }
        }
    }

    private fun getLastLocation() {
        map.locationComponent.locationEngine?.getLastLocation(
            object : LocationEngineCallback<LocationEngineResult> {
                override fun onSuccess(result: LocationEngineResult?) {
                    val location = result?.lastLocation ?: return
                    moveToLocation(location)
                }
                override fun onFailure(exception: Exception) {
                    exception.printStackTrace()
                }
            })
    }

    private fun moveToLocation(location: Location) {
        val position = CameraPosition.Builder()
            .target(LatLng(location))
            .zoom(10.0)
            .tilt(20.0)
            .build()
        map.animateCamera(CameraUpdateFactory.newCameraPosition(position), 1500)
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView?.onDestroy()
    }

    companion object {
        private const val ICON_ID = "restaurant-11"
    }
}
