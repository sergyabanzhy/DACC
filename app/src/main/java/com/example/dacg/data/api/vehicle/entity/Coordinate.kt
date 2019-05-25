package com.example.dacg.data.api.vehicle.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Coordinate(
    @SerializedName("latitude")
    @Expose
    var latitude: Double,

    @SerializedName("longitude")
    @Expose var longitude: Double
)