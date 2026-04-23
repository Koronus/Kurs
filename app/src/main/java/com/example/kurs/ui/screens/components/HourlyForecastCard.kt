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
fun HourlyForecastCard(
    time: String,
    temperature: Double,
    temperatureUnit: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(dimensionResource(R.dimen.radius_small)),
        elevation = CardDefaults.cardElevation(dimensionResource(R.dimen.elevation_small))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = time,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
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
                    modifier = Modifier.size(dimensionResource(R.dimen.text_xlarge)),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.elevation_xlarge)))
                Text(
                    text = "${temperature.toInt()}${temperatureUnit}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}