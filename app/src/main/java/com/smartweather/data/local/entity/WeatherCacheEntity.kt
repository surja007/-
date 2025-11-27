package com.smartweather.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.smartweather.domain.model.WeatherCurrent

@Entity(tableName = "weather_cache")
data class WeatherCacheEntity(
    @PrimaryKey val locationKey: String, // "lat,lon"
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
    val longitude: Double,
    val cachedAt: Long
) {
    fun toDomain() = WeatherCurrent(
        timestamp = timestamp,
        temp = temp,
        feelsLike = feelsLike,
        humidity = humidity,
        windSpeed = windSpeed,
        windDeg = windDeg,
        conditionId = conditionId,
        conditionText = conditionText,
        iconUrl = iconUrl,
        cityName = cityName,
        latitude = latitude,
        longitude = longitude
    )

    companion object {
        fun fromDomain(weather: WeatherCurrent, locationKey: String) = WeatherCacheEntity(
            locationKey = locationKey,
            timestamp = weather.timestamp,
            temp = weather.temp,
            feelsLike = weather.feelsLike,
            humidity = weather.humidity,
            windSpeed = weather.windSpeed,
            windDeg = weather.windDeg,
            conditionId = weather.conditionId,
            conditionText = weather.conditionText,
            iconUrl = weather.iconUrl,
            cityName = weather.cityName,
            latitude = weather.latitude,
            longitude = weather.longitude,
            cachedAt = System.currentTimeMillis()
        )
    }
}
