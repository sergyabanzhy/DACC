package com.example.dacg.presentation.vehicleList

import com.example.dacg.domain.vehicleUseCase.entity.Vehicle
import com.example.dacg.mvp.IPresenter

interface IVehicleListPresenter: IPresenter<IView> {
    fun vehicleItemPressed(vehicle: Vehicle)
    fun storeVehiclesList(vehicles: List<Vehicle>)
}