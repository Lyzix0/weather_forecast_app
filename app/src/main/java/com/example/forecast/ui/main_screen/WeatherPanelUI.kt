package com.example.forecast.ui.main_screen

import android.content.Intent
import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forecast.R
import com.example.forecast.activities.DetailedForecast
import com.example.forecast.domain.WeatherDay
import com.example.forecast.ui.ForecastApp

@Composable
fun WeatherPanel(weather: WeatherDay, panelData: PanelData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(horizontal = 30.dp, vertical = 10.dp)
            .border(
                width = 2.dp,
                brush = Brush.verticalGradient(listOf(Color(0xFFFFFFFF), Color(0xFFBFBFBF))),
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White.copy(alpha = 0.3f)),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextsInPanel(weather.degrees.toString(), weather.weatherType,
                     weather.windKph, panelData)
    }
}

@Composable
private fun TextsInPanel(temperature: String, weatherType: String,
                         windSpeed: Double, panelData: PanelData) {
    val font = FontFamily(Font(R.font.overpass_regular))
    val textStyle = TextStyle(
        shadow = Shadow(
            color = Color.Black.copy(alpha = 0.2f),
            offset = Offset(0f, 20f),
            blurRadius = 20f
        ),
        color = Color.White
    )

    Text(
        text= panelData.curDateString,
        modifier = Modifier.padding(top = 20.dp, bottom = 10.dp),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        style = textStyle)

    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = temperature,
            fontSize = 80.sp,
            style = textStyle,
            fontFamily = font,
        )

        Text(
            text = "°",
            fontSize = 70.sp,
            style = textStyle
        )
    }
    Text(
        text = weatherType,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        modifier = Modifier.padding(top = 20.dp),
        style = textStyle,
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Ветер",
            style = textStyle,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End
        )

        Text(
            text = "|",
            style = textStyle,
            modifier = Modifier.padding(horizontal = 20.dp),
            fontSize = 24.sp,
        )

        Text(
            text = "$windSpeed км/ч",
            style = textStyle,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun CityName(cityName: String) {
    Row(
        modifier = Modifier
            .padding(top = 70.dp, start = 30.dp, bottom = 50.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.location),
            contentDescription = "иконка",
            modifier = Modifier.size(24.dp),
            tint = Color.White
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Black.copy(alpha = 0.2f),
                    offset = Offset(0f, 20f),
                    blurRadius = 10f
                )
            ),
            text = cityName,
            fontWeight = FontWeight.SemiBold,
            fontSize = 23.sp,
            color = Color.White,
        )
    }
}

@Composable
fun WeatherIcon(icon: Bitmap? = null) {
    val imageIcon = if (icon == null) {
        painterResource(R.drawable.cloudy)
    } else {
        BitmapPainter(icon.asImageBitmap())
    }

    Icon(
        painter = imageIcon,
        contentDescription = "иконка",
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .size(128.dp),
        tint = Color.White
    )
}

@Composable
fun ForecastButton() {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 40.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            val intent: Intent = Intent(context, DetailedForecast::class.java)
            context.startActivity(intent)
        },
            contentPadding = PaddingValues(
                start = 24.dp, top = 12.dp, end = 24.dp, bottom = 12.dp
            ),
            colors = ButtonDefaults.buttonColors().copy(containerColor = Color.White,
                                                        contentColor = Color.Black)) {

            Text("Подробнее", fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Preview()
@Composable
fun ForecastAppPreview() {
    ForecastApp(weather = WeatherDay("Moscow", 52.0, "Переохлажденный туман", 11.11))
}
