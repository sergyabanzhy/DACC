package com.example.dacg.domain.vehicleUseCase

import com.example.dacg.data.api.Api
import com.example.dacg.data.repositories.vehicleRepo.IVehicleRepository
import com.example.dacg.data.repositories.vehicleRepo.VehicleRepository
import com.example.dacg.domain.vehicleUseCase.entity.Vehicle
import com.example.dacg.domain.extentions.mapToDomain
import io.reactivex.Observable

class VehicleUseCase(private val vehicleRepository: IVehicleRepository = VehicleRepository(Api.api)): IVehicleUseCase {

    override fun getVehicle(farLeftLat: Double, farLeftLong: Double, nearRightLat: Double, nearRightLong: Double): Observable<List<Vehicle>> {

        return vehicleRepository.getVehicle(farLeftLat.toString(), farLeftLong.toString(), nearRightLat.toString(), nearRightLong.toString()).map {
            it.map { item-> item.mapToDomain() }
        }
    }
}