package com.example.kurs.network.model

import com.google.gson.annotations.SerializedName

data class CitySuggestion(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("country") val country: String,
    @SerializedName("country_code") val countryCode: String,
    @SerializedName("admin1") val admin1: String? = null,
    @SerializedName("admin2") val admin2: String? = null,
    @SerializedName("timezone") val timezone: String? = null,
    @SerializedName("population") val population: Int? = null
)
