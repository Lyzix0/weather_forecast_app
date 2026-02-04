package com.example.forecast.data.datasource.remote

import com.example.forecast.domain.WeatherDay

interface RemoteDataSource {
    suspend fun getWeather(): WeatherDay?
}