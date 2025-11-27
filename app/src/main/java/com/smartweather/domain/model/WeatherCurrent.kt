package com.smartweather.domain.model

data class WeatherCurrent(
    val timestamp: Long,
    val temp: Double,
    val feelsLike: Double,
    val humidity: Int,
    val windSpeed: Double,
    val windDeg: Int,
    val conditionId: Int,
    val conditionText: String,
    val iconUrl: String,
    val cityName: String,
    val latitude: Double,
    val longitude: Double
)
