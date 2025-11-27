package com.smartweather.domain.model

data class HourlyForecast(
    val timestamp: Long,
    val temp: Double,
    val pop: Double, // Probability of precipitation
    val iconUrl: String,
    val conditionText: String
)
