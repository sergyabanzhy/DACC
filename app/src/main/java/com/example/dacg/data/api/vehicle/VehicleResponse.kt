package com.example.dacg.data.api.vehicle

import com.example.dacg.data.api.vehicle.entity.Vehicle
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class VehicleResponse(
    @SerializedName("poiList")
    @Expose
    var poiList: List<Vehicle>
)