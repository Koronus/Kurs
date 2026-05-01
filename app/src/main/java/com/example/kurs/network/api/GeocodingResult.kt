package com.example.kurs.network.api

import com.google.gson.annotations.SerializedName

data class GeocodingResponse(
    val results: List<GeocodingResult>? = null
)

data class GeocodingResult(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val country: String,
    @SerializedName("country_code")
    val country_code: String,
    val admin1: String? = null,
    val admin2: String? = null,
    val timezone: String? = null,
    val population: Int? = null,
    @SerializedName("elevation")
    val elevation: Double? = null
)