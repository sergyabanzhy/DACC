package com.example.dacg.presentation.vehicleLocation

import com.example.dacg.domain.vehicleUseCase.entity.Vehicle
import com.example.dacg.mvp.IPresenter
import com.google.android.gms.maps.model.LatLng

interface IVehicleLocationPresenter: IPresenter<IView> {
    fun mapIsReadyToShow(vehicle: Vehicle)
    fun onMapBoundsCalculated(farLeft: LatLng, nearRight: LatLng)
}