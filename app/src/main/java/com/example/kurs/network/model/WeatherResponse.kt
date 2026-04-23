package com.example.kurs.network.model

import com.google.gson.annotations.SerializedName
import com.google.gson.Gson

data class WeatherResponse(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("generationtime_ms") val generationtime_ms: Double,
    @SerializedName("utc_offset_seconds") val utc_offset_seconds: Int,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("timezone_abbreviation") val timezone_abbreviation: String,
    @SerializedName("elevation") val elevation: Int,
    @SerializedName("hourly_units") val hourly_units: HourlyUnits,
    @SerializedName("hourly") val hourly: Hourly
)