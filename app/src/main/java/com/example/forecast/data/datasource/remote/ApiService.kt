package com.example.forecast.data.datasource.remote

import com.example.forecast.domain.WeatherDay
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("current.json?key=6b8828a251284293a3285642260202&q=Moscow")
    suspend fun getWeather(): ResponseBody
}