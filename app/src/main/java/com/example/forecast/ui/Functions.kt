package com.example.forecast.ui

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.forecast.R
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


val current: LocalDateTime = LocalDateTime.now()
val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("d MMMM", Locale("ru"))

val formatterFuture = DateTimeFormatter.ofPattern("MMM, d", Locale("ru"))

data class PanelData (
    val curDateString: String = "Сегодня, ${current.format(formatter)}"
)

@Composable
fun getPainterResource(icon: Bitmap?): Painter {
    return if (icon != null) {
        BitmapPainter(icon.asImageBitmap())
    } else {
        painterResource(R.drawable.weather_icon)
    }
}