package com.example.forecast.domain

import kotlinx.serialization.Serializable

data class WeatherDay (
    val cityName: String,
    val degrees: Double
)