package com.example.forecast.ui
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ForecastApp(weather: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF47BFDF),
                        Color(0xFF4A91FF)
                    ),
                    start = Offset(Float.POSITIVE_INFINITY, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY)
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            CityName("Москва")
            WeatherIcon()
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp)
                .weight(1f))
            {
                WeatherPanel(weather)
            }
        }
    }
}

