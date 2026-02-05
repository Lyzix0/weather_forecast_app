package com.example.forecast.data.datasource.remote

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import com.example.forecast.domain.WeatherDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class RemoteDataSourceImpl(private val api: ApiService) : RemoteDataSource {
    override suspend fun getWeather(): WeatherDay {
        val json = JSONObject(api.getWeather().string())

        val name = json.getJSONObject("location").getString("name")

        val current = json.getJSONObject("current")
        val condition = current.getJSONObject("condition")

        val temperature = current.getDouble("temp_c")
        val windSpeed = current.getDouble("wind_kph")

        val weatherType = condition.getString("text")
        val iconUrl = condition.getString("icon")
        val imageBitmap = getIcon(iconUrl)

        return WeatherDay(
            cityName = name,
            degrees = temperature,
            weatherType = weatherType,
            windKph = windSpeed,
            icon = imageBitmap
        )
    }

    override suspend fun getIcon(url: String): Bitmap? {
        val url = url.replace("64x64", "128x128") // делаем норм иконку (надеюсь не крашнется никогда)

        return withContext(Dispatchers.IO) {
            val iconBody = api.downloadIcon(url)
            val bitmap = iconBody.byteStream().use { inputStream ->
                BitmapFactory.decodeStream(inputStream)
            }
            bitmap
        }
    }
}