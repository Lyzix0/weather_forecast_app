package com.example.forecast.activities

import Navigation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.lifecycleScope
import com.example.forecast.data.datasource.remote.ApiService
import com.example.forecast.data.datasource.remote.RemoteDataSourceImpl
import com.example.forecast.ui.Loading
import com.example.forecast.ui.theme.MainGradient
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent { Loading() }

        lifecycleScope.launch {
//            Toast.makeText(this@MainActivity, curWeather.toString(), Toast.LENGTH_SHORT).show()
            val instance = DataRepository.getInstance()
            val weather = instance.getCurWeather()
            setContent {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MainGradient)
                ) {
                    Navigation(weather)
                }
            }
        }
    }
}