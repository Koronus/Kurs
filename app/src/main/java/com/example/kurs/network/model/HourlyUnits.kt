package com.example.kurs.network.model
import com.google.gson.annotations.SerializedName
data class HourlyUnits(
    @SerializedName("time") val time: String,
    @SerializedName("temperature_2m") val temperature_2m: String,
    @SerializedName("relativehumidity_2m") val relativehumidity_2m: String? = null,
    @SerializedName("windspeed_10m") val windspeed_10m: String? = null
)
