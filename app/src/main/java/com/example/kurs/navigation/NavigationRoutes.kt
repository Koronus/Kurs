package com.example.kurs.navigation

object NavigationRoutes {
    const val WEATHER_SCREEN = "weather_screen"
    const val WEEKLY_FORECAST = "weekly_forecast/{cityName}"

    fun weeklyForecast(cityName: String) = "weekly_forecast/$cityName"
}