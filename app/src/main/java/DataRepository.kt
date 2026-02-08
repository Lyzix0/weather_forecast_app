import com.example.forecast.data.datasource.remote.ApiService
import com.example.forecast.data.datasource.remote.RemoteDataSourceImpl
import com.example.forecast.domain.Weather
import okhttp3.OkHttpClient
import retrofit2.Retrofit


class DataRepository private constructor() {
    companion object {
        val client = OkHttpClient.Builder().build()
        val retrofit: Retrofit = Retrofit.Builder().client(client).baseUrl(BASE_URL).build()
        val service: ApiService = retrofit.create(ApiService::class.java)

        @Volatile
        private var instance: DataRepository? = null

        fun getInstance(): DataRepository {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = DataRepository()
                    }
                }
            }
            return instance!!
        }

        const val BASE_URL = "http://api.weatherapi.com/v1/"
        const val TAG = "TAG"
    }

    suspend fun getCurWeather(): Weather {
        val curWeather = RemoteDataSourceImpl(service).getWeather()
        return curWeather
    }

    suspend fun getDayWeather(): List<Weather> {
        val weathers = RemoteDataSourceImpl(service).getDayWeather()
        return weathers
    }

    suspend fun getFutureForecast(): List<Weather> {
        val weathers = RemoteDataSourceImpl(service).getDaysForecast()
        return weathers
    }
}