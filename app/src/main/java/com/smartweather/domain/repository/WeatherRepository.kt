package com.smartweather.domain.repository

import com.smartweather.domain.model.*

interface WeatherRepository {
    suspend fun getCurrentWeather(
        lat: Double,
        lon: Double,
        cityName: String,
        forceRefresh: Boolean = false
    ): Result<WeatherCurrent>

    suspend fun getHourlyForecast(lat: Double, lon: Double): Result<List<HourlyForecast>>
    suspend fun getDailyForecast(lat: Double, lon: Double): Result<List<DailyForecast>>
    suspend fun getAQI(lat: Double, lon: Double): Result<AQIInfo>
    suspend fun getWeatherAlerts(lat: Double, lon: Double): Result<List<WeatherAlert>>
    suspend fun clearExpiredCache()
}
