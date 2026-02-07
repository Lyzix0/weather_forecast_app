package com.example.forecast.data.datasource.remote

import com.example.forecast.BuildConfig
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Streaming
import retrofit2.http.Url

interface ApiService {
    @GET("current.json?key=${BuildConfig.WeatherApiKey}&q=Moscow&lang=ru")
    suspend fun getCurrentWeather(): ResponseBody

    @GET("forecast.json?key=${BuildConfig.WeatherApiKey}&q=Moscow&aqi=no&alerts=no&lang=ru")
    suspend fun getDayWeather(@Query("days") days: Int = 1): ResponseBody

    @Streaming
    @GET()
    suspend fun downloadIcon(@Url url: String): ResponseBody
}