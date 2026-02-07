package com.example.forecast.ui.descriptions

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forecast.R
import com.example.forecast.domain.Weather
import com.example.forecast.ui.main_screen.current
import java.time.LocalDateTime
import kotlin.math.round

val textStyle = TextStyle(
    shadow = Shadow(
        color = Color.Black.copy(alpha = 0.2f),
        offset = Offset(0f, 20f),
        blurRadius = 20f
    ),
    color = Color.White
)

@Composable
fun ShowScreen(weathers: List<Weather>) {
    Column(
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
            .padding(top=60.dp, end=25.dp, bottom=20.dp, start=25.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text="< Назад", style = textStyle,
                 fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom)
        {
            Text("Сегодня", style = textStyle, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text("Фев, 12", style = textStyle, fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(35.dp))
        WeatherRow(weathers)
        Spacer(modifier = Modifier.height(50.dp))

        Text("Прогноз погоды", style = textStyle, fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(20.dp))
        WeatherForecast()
    }
}

@Composable
fun WeatherRow(weathers: List<Weather>) {
    Row (modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween) {

        val currentTime = LocalDateTime.now().hour

        for (i in 0..4) {
            WeatherColumn(weathers[i], currentTime - 2 + i)
        }
    }
}

@Composable
fun WeatherForecast() {
    Column(modifier = Modifier.height(250.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally) {
        ForecastRow()
        ForecastRow()
        ForecastRow()
        ForecastRow()
        ForecastRow()
    }
}

@Composable
fun ForecastRow() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Фев, 13", style = textStyle, fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))

        Icon(painter = painterResource(R.drawable.weather_icon), contentDescription = "иконка",
            modifier = Modifier.size(40.dp), tint = Color.White)

        Text(text = "21°", style = textStyle, fontSize = 18.sp, fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f), textAlign = TextAlign.End)
    }
}

@Composable
fun WeatherColumn(weather: Weather, currHour: Int) {
    Column(modifier = Modifier.height(150.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            Alignment.CenterHorizontally)
    {

        Text(text = "${round(weather.degrees)}°C", style = textStyle, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Icon(
            painter = if (weather.icon != null) {
                BitmapPainter(weather.icon.asImageBitmap())
            } else {
                painterResource(R.drawable.weather_icon)
            },
            contentDescription = "иконка",
            modifier = Modifier.size(64.dp),
            tint = Color.White,
        )
        Text(text = "$currHour:00", style = textStyle, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun DescriptionScreen(weathers: List<Weather>) {
    val density = LocalDensity.current
    val visibleState = remember { MutableTransitionState(false) }

    LaunchedEffect(Unit) {
        visibleState.targetState = true
    }

    Column(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(
            visibleState = visibleState,
            enter = slideInVertically { with(density) { 40.dp.roundToPx() } } + fadeIn(tween(500))
        ) {
            ShowScreen(weathers)
        }
    }
}

@Preview
@Composable
fun ScreenPreview() {
    DescriptionScreen(weathers = listOf(Weather(degrees = 52.5, weatherType = "порно", windKph = 11.1),
        Weather(degrees = 52.5, weatherType = "порно", windKph = 11.1),
        Weather(degrees = 52.5, weatherType = "порно", windKph = 11.1),
        Weather(degrees = 52.5, weatherType = "порно", windKph = 11.1),
        Weather(degrees = 52.5, weatherType = "порно", windKph = 11.1)))
}