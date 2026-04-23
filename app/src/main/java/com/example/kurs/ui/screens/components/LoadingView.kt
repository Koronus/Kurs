package com.example.kurs.ui.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.kurs.R

@Composable
fun CityNotFoundView(
    cityName: String,
    onAddCity: () -> Unit,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = R.drawable.ic_error),
                contentDescription = null,
                modifier = Modifier.size(dimensionResource(R.dimen.icon_xxlarge)),
                tint = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.text_normal)))
            Text(
                text = stringResource(R.string.city_not_found_message),
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.text_normal)))
            Button(onClick = onAddCity) {
                Text(stringResource(R.string.add_city))
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.elevation_xlarge)))
            Button(onClick = onBack) {
                Text("Назад")
            }
        }
    }
}