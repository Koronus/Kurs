package com.example.kurs.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.kurs.network.model.CityResponse
import com.example.kurs.network.model.CityWithWeather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "cities_prefs")

class CityStorage(private val context: Context) {

    companion object {
        private val CITIES_KEY = stringPreferencesKey("saved_cities")
        private val gson = Gson()
    }

    suspend fun saveCities(cities: List<CityWithWeather>) {
        try {
            val citiesToSave = cities.map { city ->
                CityResponse(
                    name = city.cityInfo.name,
                    latitude = city.cityInfo.latitude,
                    longitude = city.cityInfo.longitude,
                    country = city.cityInfo.country
                )
            }
            val json = gson.toJson(citiesToSave)
            context.dataStore.edit { preferences ->
                preferences[CITIES_KEY] = json
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun loadCities(): List<CityResponse> {
        return try {
            val json = context.dataStore.data.map { preferences ->
                preferences[CITIES_KEY] ?: "[]"
            }.first()

            val type = object : TypeToken<List<CityResponse>>() {}.type
            val cities: List<CityResponse> = gson.fromJson(json, type)

            cities
        } catch (e: Exception) {
            emptyList()
        }
    }
}