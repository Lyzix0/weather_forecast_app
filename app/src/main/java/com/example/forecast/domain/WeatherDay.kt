package com.example.forecast.domain


data class WeatherDay (
    val cityName: String,
    val degrees: Double,
    val weatherType: String,
    val windKph: Double
)