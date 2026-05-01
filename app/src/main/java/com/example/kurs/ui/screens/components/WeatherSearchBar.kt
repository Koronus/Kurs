package com.example.kurs.ui.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.kurs.R
import com.example.kurs.network.model.CitySuggestion


@Composable
fun WeatherSearchBar(
    inputText: String,
    onInputChange: (String) -> Unit,
    suggestions: List<CitySuggestion>,
    isSearching: Boolean,
    onSuggestionClick: (CitySuggestion) -> Unit,
    onClear: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_normal))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = inputText,
                onValueChange = onInputChange,
                label = { Text(stringResource(R.string.search_city_hint)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(dimensionResource(R.dimen.radius_medium)),
                trailingIcon = {
                    if (inputText.isNotEmpty()) {
                        IconButton(onClick = onClear) {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = null
                            )
                        }
                    } else if (isSearching) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(dimensionResource(R.dimen.icon_medium)),
                            strokeWidth = dimensionResource(R.dimen.elevation_medium)
                        )
                    }
                }
            )


            if (suggestions.isNotEmpty()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(R.dimen.padding_extra_small)),
                    elevation = CardDefaults.cardElevation( dimensionResource(R.dimen.padding_extra_small)),
                    shape = RoundedCornerShape(dimensionResource(R.dimen.padding_medium))
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = dimensionResource(R.dimen.suggestion_list_max_height))
                    ) {
                        items(suggestions) { citySuggestion ->
                            SuggestionItem(
                                citySuggestion = citySuggestion,
                                onClick = { onSuggestionClick(citySuggestion) }
                            )
                        }
                    }
                }
            }
        }
    }
}