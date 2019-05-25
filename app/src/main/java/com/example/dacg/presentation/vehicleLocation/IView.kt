package com.example.dacg.presentation.vehicleLocation

import com.example.dacg.domain.vehicleUseCase.entity.Vehicle

interface IView {
    fun showVehicleOnMap(vehicle: Vehicle)
    fun onShowVehiclesOnMap(vehicles: List<Vehicle>)
    fun onShowError(error: String)
}