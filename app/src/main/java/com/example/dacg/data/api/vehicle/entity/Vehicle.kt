package com.example.dacg.data.api.vehicle.entity


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Vehicle(
    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("coordinate")
    @Expose val coordinate: Coordinate,

    @SerializedName("fleetType")
    @Expose
    var fleetType: String,

    @SerializedName("heading")
    @Expose
    var heading: Double
)