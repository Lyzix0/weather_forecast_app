package com.example.forecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.example.forecast.data.datasource.remote.ApiService
import com.example.forecast.data.datasource.remote.RemoteDataSourceImpl
import com.example.forecast.ui.ForecastApp
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val client = OkHttpClient.Builder().build()

        val retrofit = Retrofit.Builder().client(client).baseUrl(BASE_URL).build()
        val service = retrofit.create(ApiService::class.java)


        lifecycleScope.launch {
            val a = RemoteDataSourceImpl(service).getWeather()
        }

        setContent { ForecastApp("-20.8") }
    }

    private companion object {
        const val BASE_URL = "http://api.weatherapi.com/v1/"
        const val TAG = "TAG"
    }
}

