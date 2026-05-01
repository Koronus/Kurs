package com.example.kurs.network.model

import com.google.gson.annotations.SerializedName
import com.example.kurs.network.api.GeocodingResult

data class CityResponse(
    @SerializedName("name") val name: String,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("country") val country: String,
    @SerializedName("population") val population: Int? = null,
    @SerializedName("is_capital") val isCapital: Boolean? = null
)


fun GeocodingResult.toCityResponse(): CityResponse {
    return CityResponse(
        name = this.name,
        latitude = this.latitude,
        longitude = this.longitude,
        country = this.country,
        population = this.population,
        isCapital = null
    )
}

