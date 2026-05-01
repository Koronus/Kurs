package com.example.kurs.ui.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.kurs.R

@Composable
fun NoDataView(onLoad: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(stringResource(R.string.no_weather_data))
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))
            Button(onClick = onLoad) {
                Text(stringResource(R.string.load))
            }
        }
    }
}