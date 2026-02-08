package com.example.forecast.data.datasource.remote

import android.graphics.Bitmap
import com.example.forecast.domain.Weather

interface RemoteDataSource {
    suspend fun getWeather(): Weather?
    suspend fun getIcon(url: String): Bitmap?
    suspend fun getDayWeather(): List<Weather>
    suspend fun getDaysForecast(): List<Weather>
}