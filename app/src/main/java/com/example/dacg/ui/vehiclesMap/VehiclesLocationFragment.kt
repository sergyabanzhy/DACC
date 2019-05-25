package com.example.dacg.ui.vehiclesMap

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.dacg.R
import com.example.dacg.presentation.vehicleLocation.IView
import com.example.dacg.presentation.vehicleLocation.VehicleLocationPresenter
import com.google.android.gms.maps.*
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import android.graphics.Bitmap
import com.google.android.gms.maps.model.BitmapDescriptor
import android.graphics.Canvas
import com.example.dacg.domain.vehicleUseCase.entity.Vehicle


class VehiclesLocationFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnCameraIdleListener, IView {

    private val presenter = VehicleLocationPresenter()

    private lateinit var mapFragment: SupportMapFragment

    private lateinit var map: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")

        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
    }

    override fun onMapReady(map: GoogleMap) {
        Log.d(TAG, "onMapReady")

        this.map = map

        map.setOnCameraIdleListener(this)

        arguments?.apply {
            presenter.mapIsReadyToShow(this[EXTRA_VEHICLE] as Vehicle)
        }
    }

    override fun showVehicleOnMap(vehicle: Vehicle) {
        Log.d(TAG, "showVehicleOnMap")

        val coordinates = LatLng(vehicle.latitude, vehicle.longitude)
        val marker = MarkerOptions()
            .icon(bitmapDescriptorFromVector(vehicle.fleetType))
            .position(coordinates)
            .title(vehicle.fleetType)

        val cameraPosition = CameraPosition.Builder()
            .target(coordinates)
            .zoom(14f)
            .bearing(90f)
            .tilt(30f)
            .build()

        map.addMarker(marker)

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    override fun onShowVehiclesOnMap(vehicles: List<Vehicle>) {
        Log.d(TAG, "onShowVehiclesOnMap")

        for (vehicle in vehicles) {
            val icon = bitmapDescriptorFromVector(vehicle.fleetType)
            val coordinates = LatLng(vehicle.latitude, vehicle.longitude)
            val marker = MarkerOptions()
                .icon(icon)
                .position(coordinates)
                .title(vehicle.fleetType)

            map.addMarker(marker)
        }
    }

    override fun onCameraIdle() {
        Log.d(TAG, "onCameraIdle")

        val mapBoundsOnScreen = map.projection.visibleRegion

        presenter.onMapBoundsCalculated(mapBoundsOnScreen.farLeft, mapBoundsOnScreen.nearRight)

        map.setOnCameraIdleListener(null)
    }

    override fun onShowError(error: String) {
        Log.d(TAG, "onShowError, message $error")
    }

    override fun onStart() {
        Log.d(TAG, "onStart")
        super.onStart()

        presenter.attach(this)

        mapFragment.getMapAsync(this)
    }

    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()

        presenter.detach()
    }

    private fun bitmapDescriptorFromVector(type: String?): BitmapDescriptor {
        val icon = if (type == "TAXI") R.drawable.ic_local_taxi else R.drawable.ic_directions_car
        val vectorDrawable = resources.getDrawable(icon, null)
        vectorDrawable.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)
        val bitmap = Bitmap.createBitmap(vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    companion object {
        private const val EXTRA_VEHICLE = "EXTRA_VEHICLE"

        fun create(vehicle: Vehicle): VehiclesLocationFragment {
            val bundle = bundleOf(EXTRA_VEHICLE to vehicle)

            return VehiclesLocationFragment().apply {
                arguments = bundle
            }
        }

        private val TAG = VehiclesLocationFragment::class.java.simpleName
    }
}