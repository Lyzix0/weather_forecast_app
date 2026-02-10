package com.example.forecast.ui.main_screen

import android.graphics.Bitmap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forecast.R
import com.example.forecast.domain.PanelRowType
import com.example.forecast.ui.theme.textStyle
import kotlin.math.round


@Composable
fun PanelRow(type: PanelRowType, counter: Double) {
    val (iconRes, textName, textValue) = when (type) {
        PanelRowType.WIND_SPEED -> Triple(R.drawable.wind, "Ветер", "${round(counter).toInt()} км/ч")
        PanelRowType.HUMIDITY -> Triple(R.drawable.humidity, "Влжнсть", "${round(counter).toInt()} %")
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = Color.White
        )

        Spacer(modifier = Modifier.width(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = textName,
                style = textStyle,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Text(
                text = "|",
                style = textStyle,
                modifier = Modifier.padding(horizontal = 8.dp),
                fontSize = 16.sp
            )
            Text(
                text = textValue,
                style = textStyle,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }
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

        OutlinedButton(
            onClick = { println(52) },
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
                text = cityName,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black.copy(alpha = 0.2f),
                        offset = Offset(0f, 20f),
                        blurRadius = 10f),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 23.sp,
                    color = Color.White,
                )
            )
        }
    }
}