package com.example.forecast.data.datasource.remote

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.example.forecast.domain.WeatherDay

interface RemoteDataSource {
    suspend fun getWeather(): WeatherDay?
    suspend fun getIcon(url: String): Bitmap?
}