package com.example.kurs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kurs.navigation.AppNavigation
import com.example.kurs.ui.theme.KursTheme
import com.example.kurs.viewmodel.WeatherViewModel
import com.example.kurs.viewmodel.WeatherViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KursTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val weatherViewModel: WeatherViewModel = viewModel(
                        factory = WeatherViewModelFactory(applicationContext)
                    )

                    AppNavigation(weatherViewModel = weatherViewModel)
                }
            }
        }
    }
}