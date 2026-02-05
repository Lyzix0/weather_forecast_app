package com.example.forecast.domain

import android.graphics.Bitmap


data class WeatherDay (
    val cityName: String,
    val degrees: Double,
    val weatherType: String,
    val windKph: Double,
    val icon: Bitmap? = null
)