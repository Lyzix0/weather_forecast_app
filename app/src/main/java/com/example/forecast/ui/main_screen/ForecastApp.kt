package com.example.forecast.ui.main_screen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.dp
import com.example.forecast.domain.Weather
import com.example.forecast.ui.theme.MainGradient


@Composable
fun ForecastApp(weather: Weather,
                onOpenDetails: () -> Unit)
{
    val panelData = PanelData()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainGradient)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            CityName("Москва")
            WeatherIcon(weather.icon)
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp)
                .weight(1f))
            {
                WeatherPanel(weather, panelData)
            }
            ForecastButton(onClick = onOpenDetails)
        }
    }
}

