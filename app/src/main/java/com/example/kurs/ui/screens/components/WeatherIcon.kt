package com.example.kurs.ui.screens.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.example.kurs.R

@Composable
fun WeatherIcon(temperature: Double?, modifier: Modifier = Modifier.size(dimensionResource(R.dimen.icon_xxlarge))) {
    val iconRes = when {
        temperature != null && temperature < 0 -> R.drawable.ic_ac_unit
        temperature != null && temperature < 15 -> R.drawable.ic_cloud
        temperature != null && temperature < 25 -> R.drawable.ic_wb_sunny
        else -> R.drawable.ic_whatshot
    }
    Icon(
        painter = painterResource(id = iconRes),
        contentDescription = null,
        modifier = modifier,
        tint = Color.White
    )
}