package com.example.forecast.data.datasource.remote

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.forecast.domain.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDateTime

class RemoteDataSourceImpl(private val api: ApiService) : RemoteDataSource {
    override suspend fun getWeather(): Weather {
        val json = JSONObject(api.getCurrentWeather().string())

        val current = json.getJSONObject("current")
        val weather = parseWeather(current,)

        return weather
    }

    override suspend fun getDayWeather(): List<Weather> {
        val currentTime = LocalDateTime.now().hour

        val json = JSONObject(api.getDayWeather().string())
        val forecast: JSONObject = json.getJSONObject("forecast").getJSONArray("forecastday")[0] as JSONObject
        val hours = forecast.getJSONArray("hour")

        return (currentTime - 2..currentTime + 2).map { i ->
            val hour = hours[i % 24] as JSONObject
            parseWeather(hour,)
        }
    }

    override suspend fun getDaysForecast(): List<Weather> {
        val daysCount = 8
        var json: JSONObject? = null

        try {
            json = JSONObject(api.getDayWeather(days=daysCount).string())
        }
        catch (e: Exception) {
            return getDaysForecast()
        }
        val forecastDays: JSONArray = json
            .getJSONObject("forecast")
            .getJSONArray("forecastday")

        return (0 until forecastDays.length()).map { i ->
            val data = forecastDays.getJSONObject(i).getJSONObject("day")
            parseWeather(data, avg = true)
        }
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

    private suspend fun parseWeather(current: JSONObject, avg: Boolean = false): Weather {
        val temp = current.getDouble("${if (avg) "avg" else ""}temp_c")
        val windSpeed = current.getDouble(if (avg) "avgvis_km" else "wind_kph")

        val humidity = if (!avg) {
            current.getDouble("humidity")
        } else null

        val condition = current.getJSONObject("condition")
        val weatherType = condition.getString("text")
        val iconUrl = condition.getString("icon")

        val weather = Weather(
            degrees = temp,
            weatherType = weatherType,
            windKph = windSpeed,
            icon = getIcon(iconUrl),
            humidity = humidity
        )
        return weather
    }
}