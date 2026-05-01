package com.example.kurs.ui.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kurs.network.model.CityWithWeather
import com.example.kurs.R
import androidx.compose.ui.res.stringResource

@Composable
fun CityWeatherCard(
    city: CityWithWeather,
    onRefresh: () -> Unit,
    onRemove: () -> Unit,
    onCityClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCityClick() },
        elevation = CardDefaults.cardElevation(dimensionResource(R.dimen.elevation_normal)),
        shape = RoundedCornerShape(dimensionResource(R.dimen.radius_medium)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding( dimensionResource(R.dimen.padding_normal)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = city.cityInfo.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    softWrap = false
                )
                if (city.cityInfo.country.isNotBlank()) {
                    Text(
                        text = city.cityInfo.country,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                when {
                    city.isLoading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.size(R.dimen.progress_indicator_normal.dp),
                            strokeWidth = R.dimen.padding_extra_small.dp
                        )
                    }
                    city.error != null -> {
                        Icon(
                            Icons.Default.Warning,
                            contentDescription = stringResource(R.string.cd_error),
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                    city.weather != null -> {
                        val currentTemp = city.weather.hourly.temperature_2m.firstOrNull()
                        if (currentTemp != null) {
                            Text(
                                text = String.format("%.0f°", currentTemp),
                                fontSize = with(LocalDensity.current) {
                                    dimensionResource(R.dimen.text_xxxlarge).toSp()
                                },
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    else -> {
                        Text(
                            text = stringResource(R.string.no_data),
                            fontSize = R.dimen.text_xxlarge.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.width(R.dimen.padding_small.dp))

                Column {
                    IconButton(
                        onClick = onRefresh,
                        modifier = Modifier.size(R.dimen.icon_button_size.dp)
                    ) {
                        Icon(
                            Icons.Default.Refresh,
                            contentDescription = stringResource(R.string.cd_refresh),
                            modifier = Modifier.size(R.dimen.icon_medium.dp)
                        )
                    }
                    IconButton(
                        onClick = onRemove,
                        modifier = Modifier.size(R.dimen.icon_button_size.dp)
                    ) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = stringResource(R.string.cd_delete),
                            modifier = Modifier.size(R.dimen.icon_medium.dp)
                        )
                    }
                }
            }
        }
    }
}