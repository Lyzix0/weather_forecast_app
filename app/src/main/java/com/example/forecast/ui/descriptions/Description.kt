package com.example.forecast.ui.descriptions

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forecast.R
import com.example.forecast.domain.Weather
import com.example.forecast.ui.formatterFuture
import com.example.forecast.ui.getPainterResource
import com.example.forecast.ui.theme.MainGradient
import com.example.forecast.ui.theme.textStyle
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.math.round

@Composable
fun ShowScreen(
    weathersToday: List<Weather>,
    weathersForecast: List<Weather>,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainGradient)
            .padding(top = 60.dp, end = 25.dp, bottom = 20.dp, start = 25.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedButton(
                onClick = { onBack() },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White),
                border = BorderStroke(1.dp, color=Color.White.copy(alpha = 0.3f)),
                modifier = Modifier.shadow(
                    elevation = 10.dp, shape = CircleShape,
                    ambientColor = Color.Black.copy(alpha = 0.2f),
                    spotColor = Color.Black.copy(alpha = 0.2f)),

                contentPadding = PaddingValues(horizontal = 15.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "< Назад", style = textStyle,
                    fontSize = 24.sp, fontWeight = FontWeight.Bold,
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        )
        {
            val result = LocalDateTime.now()
            var date = result.format(formatterFuture)
            date = date.replace(".", "").replaceFirstChar { it.uppercase() }

            Text("Сегодня", style = textStyle, fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
            Text(date, style = textStyle, fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(35.dp))
        WeatherRow(weathersToday)
        Spacer(modifier = Modifier.height(50.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        )
        {
            Text(
                "Прогноз погоды",
                style = textStyle,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )
            Icon(
                painter = painterResource(R.drawable.calendar),
                tint = Color.White,
                contentDescription = "иконка)",
                modifier = Modifier.size(26.dp)
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        WeatherForecast(weathersForecast)
    }
}

@Composable
fun WeatherRow(weathers: List<Weather>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        val currentTime = LocalDateTime.now().hour

        for (i in 0..4) {
            WeatherColumn(weathers[i], (currentTime - 2 + 24 + i) % 24)
        }
    }
}

@Composable
fun WeatherForecast(weathers: List<Weather>) {
    Column(
        modifier = Modifier.height(350.dp).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val result = LocalDate.now()

        for (i in 1 until weathers.size) {
            var date = result.plusDays(i.toLong()).format(formatterFuture)
            date = date.replace(".", "").replaceFirstChar { it.uppercase() }

            ForecastRow(weathers[i], date)
        }
    }
}

@Composable
fun ForecastRow(weather: Weather, curDate: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth().padding(vertical = 6.dp)
            .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(15)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = curDate, style = textStyle, fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f).padding(start = 7.dp)
        )

        Icon(
            painter = getPainterResource(weather.icon),
            contentDescription = "иконка",
            modifier = Modifier.size(45.dp), tint = Color.White
        )

        Text(
            text = "${round(weather.degrees).toInt()}°C",
            style = textStyle,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f).padding(end = 7.dp),
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun WeatherColumn(weather: Weather, currHour: Int) {
    Column(
        modifier = Modifier.height(150.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        Alignment.CenterHorizontally
    )
    {
        Text(
            text = "${round(weather.degrees).toInt()}°C",
            style = textStyle,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Icon(
            painter = getPainterResource(weather.icon),
            contentDescription = "иконка",
            modifier = Modifier.size(64.dp),
            tint = Color.White,
        )
        Text(
            text = if (currHour < 10) { "0$currHour:00" } else "$currHour:00",
            style = textStyle,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun DescriptionScreen(
    weathersToday: List<Weather>,
    weathersForecast: List<Weather>,
    onBack: () -> Unit
) {
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
            ShowScreen(weathersToday, weathersForecast, onBack)
        }
    }
}

@Preview
@Composable
fun ScreenPreview() {
    val weathers = listOf(
        Weather(degrees = 52.5, weatherType = "хз", windKph = 11.1),
        Weather(degrees = 52.5, weatherType = "хз", windKph = 11.1),
        Weather(degrees = 52.5, weatherType = "хз", windKph = 11.1),
        Weather(degrees = 52.5, weatherType = "хз", windKph = 11.1),
        Weather(degrees = 52.5, weatherType = "хз", windKph = 11.1),
        Weather(degrees = 52.5, weatherType = "хз", windKph = 11.1),
        Weather(degrees = 52.5, weatherType = "хз", windKph = 11.1),
        Weather(degrees = 52.5, weatherType = "хз", windKph = 11.1),
        Weather(degrees = 52.5, weatherType = "хз", windKph = 11.1),
        Weather(degrees = 52.5, weatherType = "хз", windKph = 11.1),
        Weather(degrees = 52.5, weatherType = "хз", windKph = 11.1),
        Weather(degrees = 52.5, weatherType = "хз", windKph = 11.1),
        Weather(degrees = 52.5, weatherType = "хз", windKph = 11.1),
        Weather(degrees = 52.5, weatherType = "хз", windKph = 11.1),
    )
    DescriptionScreen(
        weathersToday = weathers,
        weathersForecast = weathers,
        onBack = { println("52") }
    )
}