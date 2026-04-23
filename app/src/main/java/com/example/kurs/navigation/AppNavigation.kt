package com.example.kurs.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kurs.ui.screens.WeeklyForecastScreen
import com.example.kurs.viewmodel.WeatherViewModel
import WeatherScreen

@Composable
fun AppNavigation(
    weatherViewModel: WeatherViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.WEATHER_SCREEN
    ) {
        composable(NavigationRoutes.WEATHER_SCREEN) {
            WeatherScreen(
                navController = navController,
                viewModel = weatherViewModel
            )
        }

        composable(
            route = NavigationRoutes.WEEKLY_FORECAST,
            arguments = listOf(
                navArgument("cityName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            WeeklyForecastScreen(
                cityName = cityName,
                navController = navController,
                viewModel = weatherViewModel
            )
        }
    }
}