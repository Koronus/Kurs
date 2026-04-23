package com.example.kurs.network.api

import retrofit2.http.GET
import retrofit2.http.Query

interface CityApiService {
    @GET("v1/search")
    suspend fun getCityCoordinates(
        @Query("name") cityName: String,
        @Query("count") count: Int = 1,
        @Query("language") language: String = "ru",
        @Query("format") format: String = "json"
    ): GeocodingResponse

    @GET("v1/search")
    suspend fun searchCities(
        @Query("name") name: String,
        @Query("count") count: Int = 10,
        @Query("language") language: String = "ru",
        @Query("format") format: String = "json"
    ): GeocodingResponse
}

