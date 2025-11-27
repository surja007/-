package com.smartweather.domain.model

data class DailyForecast(
    val date: Long,
    val minTemp: Double,
    val maxTemp: Double,
    val sunrise: Long,
    val sunset: Long,
    val summary: String,
    val iconUrl: String,
    val pop: Double
)
