package com.example.kurs.network.api

import com.example.kurs.network.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("v1/forecast")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("hourly") hourly: String = "temperature_2m,relativehumidity_2m,windspeed_10m,weathercode",
        @Query("forecast_days") forecastDays: Int = 7,
        @Query("current_weather") currentWeather: Boolean = false
    ): WeatherResponse
}