package com.example.forecast.data.datasource.remote

import android.util.Log
import com.example.forecast.domain.WeatherDay
import org.json.JSONObject

class RemoteDataSourceImpl(private val api: ApiService) : RemoteDataSource {
    override suspend fun getWeather(): WeatherDay {
        val json = JSONObject(api.getWeather().string())
        val name = json.getJSONObject("location").getString("name")
        val temperature = json.getJSONObject("current").getDouble("temp_c")

        return WeatherDay(
            cityName = name,
            degrees = temperature
        )
    }
}