package com.example.kurs.network.model

data class CityWithWeather(
    val cityInfo: CityResponse,
    val weather: WeatherResponse? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)