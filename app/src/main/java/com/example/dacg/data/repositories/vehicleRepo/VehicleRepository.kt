package com.example.dacg.data.repositories.vehicleRepo

import com.example.dacg.data.api.Api
import com.example.dacg.data.api.vehicle.entity.Vehicle
import io.reactivex.Observable

class VehicleRepository(private val api: Api) : IVehicleRepository {

    override fun getVehicle(farLeftLat: String, farLeftLong: String, nearRightLat: String, nearRightLong: String): Observable<List<Vehicle>> {

        return api.getVehicle(farLeftLat, farLeftLong, nearRightLat, nearRightLong).map {
                it.poiList
            }
        }
    }