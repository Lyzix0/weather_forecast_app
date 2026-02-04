package com.example.forecast.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forecast.R

@Composable
fun WeatherPanel(temperature: String) {
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
        TextsInPanel(temperature)
    }
}

@Composable
private fun TextsInPanel(temperature: String) {
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
        text="Сегодня, 2 февраля",
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
        text = "Облачно",
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        modifier = Modifier.padding(top = 20.dp),
        style = textStyle,
    )

    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
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
            text = "10 м/c",
            style = textStyle,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Start
        )
    }
}