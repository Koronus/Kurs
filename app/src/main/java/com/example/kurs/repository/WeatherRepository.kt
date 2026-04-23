package com.example.kurs.repository

import android.util.Log
import com.example.kurs.R
import com.example.kurs.network.RetrofitClient
import com.example.kurs.network.model.CityResponse
import com.example.kurs.network.model.WeatherResponse
import com.example.kurs.network.model.toCityResponse
import retrofit2.HttpException
import java.io.IOException
import com.example.kurs.network.model.CitySuggestion
import java.util.Locale


class WeatherRepository {
    companion object {
        private const val TAG = "WeatherRepository"
    }

    suspend fun getCityCoordinates(cityName: String): List<CityResponse> {
        return try {
            val response = RetrofitClient.cityApiService.getCityCoordinates(cityName)
            val cities = response.results?.map { it.toCityResponse() } ?: emptyList()
            cities.forEach { city ->
                Log.d(TAG, "   - ${city.name}, ${city.country} (${city.latitude}, ${city.longitude})")
            }
            cities
        } catch (e: HttpException) {
            emptyList()
        } catch (e: IOException) {

            emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getWeather(latitude: Double, longitude: Double): WeatherResponse? {
        return try {
            val response = RetrofitClient.weatherApiService.getWeather(latitude, longitude)
            response
        } catch (e: HttpException) {
            null
        } catch (e: IOException) {
            null
        } catch (e: Exception) {
            null
        }
    }

    suspend fun searchCities(query: String): List<CitySuggestion> {
        return try {
            val response = RetrofitClient.cityApiService.searchCities(
                name = query,
                count = 10,
                language = "auto",
                format = "json"
            )

            val suggestions = response.results?.map { result ->
                CitySuggestion(
                    id = result.id,
                    name = result.name,
                    latitude = result.latitude,
                    longitude = result.longitude,
                    country = result.country,
                    countryCode = result.country_code,
                    admin1 = result.admin1,
                    admin2 = result.admin2,
                    timezone = result.timezone,
                    population = result.population
                )
            } ?: emptyList()
            suggestions
        } catch (e: HttpException) {
            emptyList()
        } catch (e: IOException) {
            emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
}