package com.example.forecast.ui.main_screen

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


val current: LocalDateTime = LocalDateTime.now()
val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("d MMMM", Locale("ru"))

data class PanelData (
    val curDateString: String = "Сегодня, ${current.format(formatter)}"
)