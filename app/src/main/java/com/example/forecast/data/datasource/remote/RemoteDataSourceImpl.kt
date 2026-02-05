package com.example.forecast.data.datasource.remote

import com.example.forecast.domain.WeatherDay
import org.json.JSONObject

class RemoteDataSourceImpl(private val api: ApiService) : RemoteDataSource {
    override suspend fun getWeather(): WeatherDay {
        val json = JSONObject(api.getWeather().string())
        val name = json.getJSONObject("location").getString("name")

        val current = json.getJSONObject("current")
        val temperature = current.getDouble("temp_c")
        val weatherType = current.getJSONObject("condition").getString("text")
        val windSpeed = current.getDouble("wind_kph")

        return WeatherDay(
            cityName = name,
            degrees = temperature,
            weatherType = weatherType,
            windKph = windSpeed
        )
    }
}