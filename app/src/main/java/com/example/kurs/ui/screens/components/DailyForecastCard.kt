package com.example.kurs.ui.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.kurs.R

@Composable
fun DailyForecastCard(
    dayOfWeek: String,
    temperature: Double,
    temperatureUnit: String,
    humidity: Int?,
    humidityUnit: String,
    windSpeed: Double?,
    windUnit: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(dimensionResource(R.dimen.radius_medium)),
        elevation = CardDefaults.cardElevation(dimensionResource(R.dimen.elevation_medium))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_normal))
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = dayOfWeek,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    val weatherIconRes = when {
                        temperature < 0 -> R.drawable.ic_ac_unit
                        temperature < 15 -> R.drawable.ic_cloud
                        else -> R.drawable.ic_wb_sunny
                    }
                    Icon(
                        painter = painterResource(id = weatherIconRes),
                        contentDescription = null,
                        modifier = Modifier.size(dimensionResource(R.dimen.text_xxlarge)),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(dimensionResource(R.dimen.elevation_xlarge)))
                    Text(
                        text = "${temperature.toInt()}${temperatureUnit}",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.elevation_xlarge)))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_vertical_card))
            ) {
                if (humidity != null) {
                    HumidityDetail(humidity = humidity, humidityUnit = humidityUnit)
                }
                if (windSpeed != null) {
                    WindDetail(windSpeed = windSpeed, windUnit = windUnit)
                }
            }
        }
    }
}

@Composable
private fun HumidityDetail(humidity: Int, humidityUnit: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = R.drawable.ic_water_drop),
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(R.dimen.text_normal)),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.elevation_normal)))
        Text(
            text = "$humidity$humidityUnit",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun WindDetail(windSpeed: Double, windUnit: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = R.drawable.ic_air),
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(R.dimen.icon_small)),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.elevation_normal)))
        Text(
            text = String.format("%.1f%s", windSpeed, windUnit),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}