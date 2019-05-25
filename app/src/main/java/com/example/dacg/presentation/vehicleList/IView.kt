package com.example.dacg.presentation.vehicleList

import com.example.dacg.domain.vehicleUseCase.entity.Vehicle

interface IView {
    fun onShowVehicles(vehicles: List<Vehicle>)
    fun showVehicleLocation(vehicle: Vehicle)
    fun onShowError(message: String)
    fun showProgress()
    fun hideProgress()
}