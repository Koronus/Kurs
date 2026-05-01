package com.example.kurs.network

import com.example.kurs.network.api.CityApiService
import com.example.kurs.network.api.WeatherApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private const val CITY_API_BASE_URL = "https://geocoding-api.open-meteo.com/"
    private const val WEATHER_API_BASE_URL = "https://api.open-meteo.com/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val cityApiClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }


    private val weatherApiClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    val cityApiService: CityApiService by lazy {
        Retrofit.Builder()
            .baseUrl(CITY_API_BASE_URL)
            .client(cityApiClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CityApiService::class.java)
    }

    val weatherApiService: WeatherApiService by lazy {
        Retrofit.Builder()
            .baseUrl(WEATHER_API_BASE_URL)
            .client(weatherApiClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApiService::class.java)
    }
}