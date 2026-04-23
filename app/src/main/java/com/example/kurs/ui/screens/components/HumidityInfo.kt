package com.example.kurs.ui.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.kurs.R

@Composable
fun HumidityInfo(humidity: Int?, humidityUnit: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(id = R.drawable.ic_water_drop),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(dimensionResource(R.dimen.icon_large))
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.elevation_normal)))
        Text(
            text = if (humidity != null) "$humidity$humidityUnit" else "",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = stringResource(R.string.humidity),
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 12.sp
        )
    }
}