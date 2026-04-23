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
fun ErrorView(
    errorMessage: String,
    onRetry: () -> Unit
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
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_vertical_card)))
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_vertical_card)))
            Button(onClick = onRetry) {
                Text(stringResource(R.string.retry))
            }
        }
    }
}