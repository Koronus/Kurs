package com.example.kurs.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kurs.R
import com.example.kurs.viewmodel.WeatherViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.example.kurs.ui.screens.components.CityNotFoundView
import com.example.kurs.ui.screens.components.ErrorView
import com.example.kurs.ui.screens.components.WeeklyForecastContent
import com.example.kurs.ui.screens.components.NoDataView
import androidx.compose.ui.platform.LocalContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeeklyForecastScreen(
    cityName: String,
    navController: NavController,
    viewModel: WeatherViewModel = viewModel()
) {
    val context = LocalContext.current
    val cities by viewModel.cities.collectAsState()
    var currentCity by remember { mutableStateOf(cities.find { it.cityInfo.name == cityName }) }

    LaunchedEffect(cities) {
        currentCity = cities.find { it.cityInfo.name == cityName }
    }

    LaunchedEffect(Unit) {
        val cityForCheck = currentCity
        if (cityForCheck != null && cityForCheck.weather == null && !cityForCheck.isLoading && cityForCheck.error == null) {
            viewModel.refreshWeather(cityName)
            delay(context.resources.getInteger(R.integer.delay_loading_ms).toLong())
            currentCity = cities.find { it.cityInfo.name == cityName }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = currentCity?.cityInfo?.name ?: cityName,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        if (currentCity?.cityInfo?.country?.isNotBlank() == true) {
                            Text(
                                text = currentCity?.cityInfo?.country ?: "",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                actions = {
                    IconButton(onClick = {
                        viewModel.refreshWeather(cityName)
                        viewModel.viewModelScope.launch {
                            delay(500)
                            currentCity = cities.find { it.cityInfo.name == cityName }
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_refresh),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            val city = currentCity


            when {
                city == null -> {
                    CityNotFoundView(
                        cityName = cityName,
                        onAddCity = {
                            viewModel.addCity(cityName)
                            viewModel.viewModelScope.launch {
                                delay(context.resources.getInteger(R.integer.delay_add_city_ms).toLong())
                                currentCity = cities.find { it.cityInfo.name == cityName }
                            }
                        },
                        onBack = { navController.navigateUp() }
                    )
                }

                city.error != null -> {
                    ErrorView(
                        errorMessage = city.error,
                        onRetry = {
                            viewModel.refreshWeather(cityName)
                            viewModel.viewModelScope.launch {
                                delay(context.resources.getInteger(R.integer.delay_refresh_ms).toLong())
                                currentCity = cities.find { it.cityInfo.name == cityName }
                            }
                        }
                    )
                }
                city.weather != null -> WeeklyForecastContent(weather = city.weather)
                else -> {
                    NoDataView(
                        onLoad = {
                            viewModel.refreshWeather(cityName)
                            viewModel.viewModelScope.launch {
                                delay(context.resources.getInteger(R.integer.delay_refresh_ms).toLong())
                                currentCity = cities.find { it.cityInfo.name == cityName }
                            }
                        }
                    )
                }
            }
        }
    }
}