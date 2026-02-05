package com.example.forecast.data.datasource.remote

import com.example.forecast.BuildConfig
import com.example.forecast.domain.WeatherDay
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface ApiService {
    @GET("current.json?key=${BuildConfig.WeatherApiKey}&q=Moscow&lang=ru")
    suspend fun getWeather(): ResponseBody

    @Streaming
    @GET()
    suspend fun downloadIcon(@Url url: String): ResponseBody
}