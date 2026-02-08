package com.example.forecast.domain

import android.graphics.Bitmap


data class Weather (
    val degrees: Double,
    val weatherType: String,
    val windKph: Double,
    val icon: Bitmap? = null,
    val humidity: Double? = null
)