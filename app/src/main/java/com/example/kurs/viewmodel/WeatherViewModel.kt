package com.example.kurs.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kurs.network.model.CityResponse
import com.example.kurs.network.model.CitySuggestion
import com.example.kurs.network.model.CityWithWeather
import com.example.kurs.repository.WeatherRepository
import com.example.kurs.storage.CityStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val context: Context
) : ViewModel() {
    private val repository = WeatherRepository()
    private val storage = CityStorage(context)


    private val _cities = MutableStateFlow<List<CityWithWeather>>(emptyList())
    val cities: StateFlow<List<CityWithWeather>> = _cities.asStateFlow()

    private val _newCityInput = MutableStateFlow("")
    val newCityInput: StateFlow<String> = _newCityInput.asStateFlow()

    private val _suggestions = MutableStateFlow<List<CitySuggestion>>(emptyList())
    val suggestions: StateFlow<List<CitySuggestion>> = _suggestions.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching.asStateFlow()

    init {
        loadSavedCities()
    }


    private fun loadSavedCities() {
        viewModelScope.launch {
                val savedCities = storage.loadCities()
                if (savedCities.isNotEmpty()) {
                    savedCities.forEach { city ->
                        addCityWithWeather(city)
                    }
                }

        }
    }

    private fun saveCities() {
        viewModelScope.launch {
            storage.saveCities(_cities.value)
        }
    }



    private fun addCityWithWeather(cityInfo: CityResponse) {
        val cityName = cityInfo.name

        if (_cities.value.any { it.cityInfo.name.equals(cityName, ignoreCase = true) }) {
            return
        }


        val newCity = CityWithWeather(
            cityInfo = cityInfo,
            isLoading = true
        )

        _cities.value = _cities.value + newCity
        saveCities()

        viewModelScope.launch {
            try {
                val weather = repository.getWeather(cityInfo.latitude, cityInfo.longitude)

                _cities.value = _cities.value.map { city ->
                    if (city.cityInfo.name == cityName) {
                        city.copy(
                            weather = weather,
                            isLoading = false
                        )
                    } else city
                }
                saveCities()

            } catch (e: Exception) {
                _cities.value = _cities.value.map { city ->
                    if (city.cityInfo.name == cityName) {
                        city.copy(
                            isLoading = false,
                            error = e.message
                        )
                    } else city
                }
            }
        }
    }


    fun addCity(cityName: String) {
        viewModelScope.launch {
            val cities = repository.getCityCoordinates(cityName)
            if (cities.isNotEmpty()) {
                addCityWithWeather(cities[0])
            } else {
                val errorCity = CityWithWeather(
                    cityInfo = CityResponse(cityName, 0.0, 0.0, ""),
                    isLoading = false
                )
                _cities.value = _cities.value + errorCity
                saveCities()
            }
        }
    }

    fun removeCity(cityName: String) {
        _cities.value = _cities.value.filter { it.cityInfo.name != cityName }
        saveCities()
    }

    fun refreshWeather(cityName: String) {
        val city = _cities.value.find { it.cityInfo.name == cityName }

        if (city != null && city.cityInfo.latitude != 0.0) {
            viewModelScope.launch {
                _cities.value = _cities.value.map {
                    if (it.cityInfo.name == cityName) it.copy(isLoading = true)
                    else it
                }

                val weather = repository.getWeather(city.cityInfo.latitude, city.cityInfo.longitude)

                _cities.value = _cities.value.map {
                    if (it.cityInfo.name == cityName) it.copy(weather = weather, isLoading = false)
                    else it
                }
                saveCities()
            }
        }
    }

    fun refreshAllCities() {
        _cities.value.forEach { city ->
            if (city.cityInfo.latitude != 0.0) {
                refreshWeather(city.cityInfo.name)
            }
        }
    }

    fun updateNewCityInput(input: String) {
        _newCityInput.value = input
    }

    fun searchCities(query: String) {
        if (query.length < 2) {
            _suggestions.value = emptyList()
            return
        }

        viewModelScope.launch {
            _isSearching.value = true
            try {
                val cities = repository.searchCities(query)
                _suggestions.value = cities
            } catch (e: Exception) {
                _suggestions.value = emptyList()
            } finally {
                _isSearching.value = false
            }
        }
    }

    fun clearSuggestions() {
        _suggestions.value = emptyList()
    }


}