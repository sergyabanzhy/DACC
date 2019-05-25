package com.example.dacg.domain.extentions

import com.example.dacg.data.api.vehicle.entity.Vehicle

fun Vehicle.mapToDomain(): com.example.dacg.domain.vehicleUseCase.entity.Vehicle {
    return com.example.dacg.domain.vehicleUseCase.entity.Vehicle(id, coordinate.latitude, coordinate.longitude, fleetType)
}