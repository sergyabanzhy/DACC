package com.example.dacg.domain.vehicleUseCase

import com.example.dacg.domain.IUseCase
import com.example.dacg.domain.vehicleUseCase.entity.Vehicle
import io.reactivex.Observable

interface IVehicleUseCase: IUseCase {
    fun getVehicle(farLeftLat: Double, farLeftLong: Double, nearRightLat: Double, nearRightLong: Double): Observable<List<Vehicle>>
}