    package com.example.forecast.ui.main_screen

    import android.graphics.Bitmap
    import androidx.compose.foundation.background
    import androidx.compose.foundation.border
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.PaddingValues
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxHeight
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
    import com.example.forecast.domain.PanelRowType
    import com.example.forecast.domain.Weather
    import com.example.forecast.ui.PanelData
    import com.example.forecast.ui.getPainterResource
    import com.example.forecast.ui.theme.textStyle
    import kotlin.math.round

    @Composable
    fun WeatherPanel(weather: Weather, panelData: PanelData) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
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
            TextsInPanel(round(weather.degrees).toInt().toString(), weather.weatherType,
                         weather.windKph, weather.humidity!!, panelData)
        }
    }

    @Composable
    private fun TextsInPanel(temperature: String, weatherType: String,
                             windSpeed: Double, humidity: Double, panelData: PanelData
    ) {
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
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 20.dp, start = 5.dp, end = 5.dp),
            style = textStyle,
        )
        Spacer(modifier = Modifier.height(20.dp))
        PanelRow(PanelRowType.WIND_SPEED, windSpeed) // ветерок
        Spacer(modifier = Modifier.height(10.dp))
        PanelRow(PanelRowType.HUMIDITY,humidity) // влага
    }

    @Composable
    fun ForecastButton(onClick: () -> Unit) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = onClick,
                contentPadding = PaddingValues(
                    start = 24.dp, top = 12.dp, end = 24.dp, bottom = 12.dp
                ),
                colors = ButtonDefaults.buttonColors().copy(containerColor = Color.White,
                                                            contentColor = Color.Black)) {

                Text("Подробнее", fontSize = 17.sp, fontWeight = FontWeight.SemiBold,
                    color = Color(68, 78, 114))
            }
        }
    }

    @Preview()
    @Composable
    fun ForecastAppPreview() {
        ForecastApp(
            weather = Weather(52.5, "Переохлажденный туман", 11.11),
            onOpenDetails = { println(52) }
        )
    }
