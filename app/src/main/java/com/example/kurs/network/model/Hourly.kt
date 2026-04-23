package com.example.kurs.network.model
import com.google.gson.annotations.SerializedName
data class Hourly(
    @SerializedName("time") val time: List<String>,
    @SerializedName("temperature_2m") val temperature_2m: List<Double>,
    @SerializedName("relativehumidity_2m") val relativehumidity_2m: List<Int>? = null,
    @SerializedName("windspeed_10m") val windspeed_10m: List<Double>? = null,
    @SerializedName("weathercode") val weathercode: List<Int>? = null
)
