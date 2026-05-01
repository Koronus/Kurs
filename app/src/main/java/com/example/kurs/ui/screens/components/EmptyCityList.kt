package com.example.kurs.ui.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.kurs.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.platform.LocalDensity

@Composable
fun EmptyCityList() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                Icons.Default.Place,
                contentDescription = null,
                modifier = Modifier.size(R.dimen.icon_xxlarge.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(R.dimen.padding_normal.dp))
            Text(
                text = stringResource(R.string.find_and_add_city),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(R.dimen.padding_small.dp))
            Text(
                text = stringResource(R.string.search_city_instruction),
                fontSize = with(LocalDensity.current) {
                    dimensionResource(R.dimen.text_small).toSp()
                }

            )
        }
    }
}