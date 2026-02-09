import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.forecast.data.datasource.remote.DataRepository
import com.example.forecast.domain.Weather
import com.example.forecast.ui.Loading
import com.example.forecast.ui.descriptions.DescriptionScreen
import com.example.forecast.ui.main_screen.ForecastApp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data object MainScreen
data object DetailedScreen

@Composable
fun Navigation(curWeather: Weather) {
    val backStack = remember { mutableStateListOf<Any>(MainScreen) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        transitionSpec = {
            slideInVertically(initialOffsetY = { it }) togetherWith
                    slideOutVertically(targetOffsetY = { -it })
        },
        popTransitionSpec = {
            slideInVertically(initialOffsetY = { -it }) togetherWith
                    slideOutVertically(targetOffsetY = { it })
        },

        entryProvider = entryProvider {
            entry<MainScreen> {
                ForecastApp(
                    weather = curWeather,
                    onOpenDetails = {
                        backStack.add(DetailedScreen)
                    }
                )
            }

            entry<DetailedScreen> {
                var futureForecast by remember { mutableStateOf<List<Weather>?>(null) }
                var longLoading by remember { mutableStateOf(false) }

                LaunchedEffect(Unit) {
                    launch {
                        delay(3000)
                        if (futureForecast == null) {
                            longLoading = true
                        }
                        futureForecast = getForecast()
                    }
                }

                val futureWeathers = futureForecast

                if (futureWeathers == null) {
                    Loading()
                    if (longLoading) {
                        LaunchedEffect(Unit) {
                            futureForecast = getForecast()
                        }
                    }
                } else {
                    DescriptionScreen(futureWeathers,
                        futureWeathers
                    ) { backStack.removeLastOrNull() }
                }
            }
        }
    )
}

suspend fun getForecast(): List<Weather> {
    val instance = DataRepository.getInstance()
    return instance.getFutureForecast()
}