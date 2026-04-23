package com.example.kurs.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.kurs.R

@Composable
fun CurrentWeatherCard(
    temperature: Double?,
    humidity: Int?,
    windSpeed: Double?,
    temperatureUnit: String,
    humidityUnit: String,
    windUnit: String
) {
    val gradientBrush = WeatherGradient.getGradient(temperature)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(dimensionResource(R.dimen.radius_large)),
        elevation = CardDefaults.cardElevation(dimensionResource(R.dimen.elevation_xlarge)),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(gradientBrush, shape = RoundedCornerShape(dimensionResource(R.dimen.radius_large)))
                .padding(dimensionResource(R.dimen.padding_large))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                WeatherIcon(temperature = temperature)

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_vertical_card)))

                if (temperature != null) {
                    Text(
                        text = "${temperature.toInt()}${temperatureUnit}",
                        fontSize = 56.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                } else {
                    Text(
                        text = stringResource(R.string.no_temperature_data),
                        fontSize = 32.sp,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    HumidityInfo(humidity = humidity, humidityUnit = humidityUnit)
                    WindInfo(windSpeed = windSpeed, windUnit = windUnit)
                }
            }
        }
    }
}