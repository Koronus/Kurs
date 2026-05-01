package com.example.kurs.ui.screens.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.kurs.R

object WeatherGradient {

    @Composable
    fun getGradient(temperature: Double?): Brush {
        val context = LocalContext.current
        return if (temperature != null) {
            when {
                temperature < 0 -> Brush.verticalGradient(
                    colors = listOf(
                        Color(context.getColor(R.color.gradient_cold_start)),
                        Color(context.getColor(R.color.gradient_cold_end))
                    )
                )
                temperature < 15 -> Brush.verticalGradient(
                    colors = listOf(
                        Color(context.getColor(R.color.gradient_cool_start)),
                        Color(context.getColor(R.color.gradient_cool_end))
                    )
                )
                temperature < 25 -> Brush.verticalGradient(
                    colors = listOf(
                        Color(context.getColor(R.color.gradient_warm_start)),
                        Color(context.getColor(R.color.gradient_warm_end))
                    )
                )
                else -> Brush.verticalGradient(
                    colors = listOf(
                        Color(context.getColor(R.color.gradient_hot_start)),
                        Color(context.getColor(R.color.gradient_hot_end))
                    )
                )
            }
        } else {
            Brush.verticalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.secondary
                )
            )
        }
    }
}