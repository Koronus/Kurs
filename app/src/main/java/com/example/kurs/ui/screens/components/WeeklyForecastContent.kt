package com.example.kurs.ui.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.kurs.R
import com.example.kurs.network.model.WeatherResponse
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun WeeklyForecastContent(weather: WeatherResponse) {
    val hourlyData = weather.hourly
    val hourlyUnits = weather.hourly_units

    val currentTemp = hourlyData.temperature_2m.firstOrNull()
    val currentHumidity = hourlyData.relativehumidity_2m?.firstOrNull()
    val currentWind = hourlyData.windspeed_10m?.firstOrNull()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_normal)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_vertical_card))
    ) {
        item {
            CurrentWeatherCard(
                temperature = currentTemp,
                humidity = currentHumidity,
                windSpeed = currentWind,
                temperatureUnit = hourlyUnits.temperature_2m,
                humidityUnit = hourlyUnits.relativehumidity_2m ?: stringResource(R.string.percent_unit),
                windUnit = hourlyUnits.windspeed_10m ?: stringResource(R.string.meters_per_second)
            )
        }

        item {
            Text(
                text = stringResource(R.string.weekly_forecast),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small), bottom = dimensionResource(R.dimen.padding_small))
            )
        }


        val dailyTemperatures = hourlyData.temperature_2m
            .filterIndexed { index, _ -> index % 24 == 0 }
            .take(7)

        val dailyTimes = hourlyData.time
            .filterIndexed { index, _ -> index % 24 == 0 }
            .take(7)

        val dailyHumidity = hourlyData.relativehumidity_2m
            ?.filterIndexed { index, _ -> index % 24 == 0 }
            ?.take(7) ?: emptyList()

        val dailyWind = hourlyData.windspeed_10m
            ?.filterIndexed { index, _ -> index % 24 == 0 }
            ?.take(7) ?: emptyList()

        items(dailyTimes.indices.toList()) { index ->
            if (index < dailyTemperatures.size) {
                val date = formatDate(dailyTimes[index])
                DailyForecastCard(
                    dayOfWeek = date,
                    temperature = dailyTemperatures[index],
                    temperatureUnit = hourlyUnits.temperature_2m,
                    humidity = dailyHumidity.getOrNull(index),
                    humidityUnit = hourlyUnits.relativehumidity_2m ?: stringResource(R.string.percent_unit),
                    windSpeed = dailyWind.getOrNull(index),
                    windUnit = hourlyUnits.windspeed_10m ?: stringResource(R.string.meters_per_second)
                )
            }
        }

        item {
            Text(
                text = stringResource(R.string.hourly_forecast_title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small), bottom = dimensionResource(R.dimen.padding_small))
            )
        }


        val hourlyTemp = hourlyData.temperature_2m.take(12)
        val hourlyTimesList = hourlyData.time.take(12)

        items(hourlyTimesList.indices.toList()) { index ->
            if (index < hourlyTemp.size) {
                val time = formatTime(hourlyTimesList[index])
                HourlyForecastCard(
                    time = time,
                    temperature = hourlyTemp[index],
                    temperatureUnit = hourlyUnits.temperature_2m
                )
            }
        }
    }
}

private fun formatDate(dateString: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
        val outputFormat = SimpleDateFormat("EEEE, dd MMMM", Locale.getDefault())
        val parsedDate = inputFormat.parse(dateString)
        outputFormat.format(parsedDate ?: Date())
    } catch (e: Exception) {
        dateString.substring(0, 10)
    }
}

private fun formatTime(timeString: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
        val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val parsedDate = inputFormat.parse(timeString)
        outputFormat.format(parsedDate ?: Date())
    } catch (e: Exception) {
        timeString.substring(11, 16)
    }
}