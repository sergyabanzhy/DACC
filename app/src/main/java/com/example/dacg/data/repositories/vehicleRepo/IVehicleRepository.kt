package com.example.dacg.data.repositories.vehicleRepo

import com.example.dacg.data.api.vehicle.entity.Vehicle
import com.example.dacg.data.repositories.IRepository
import io.reactivex.Observable
import io.reactivex.Single

interface IVehicleRepository : IRepository {
    fun getVehicle(farLeftLat: String, farLeftLong: String, nearRightLat: String, nearRightLong: String): Observable<List<Vehicle>>
}