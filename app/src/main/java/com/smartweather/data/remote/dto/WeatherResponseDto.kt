package com.smartweather.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.smartweather.domain.model.*

@JsonClass(generateAdapter = true)
data class WeatherResponseDto(
    @Json(name = "lat") val lat: Double,
    @Json(name = "lon") val lon: Double,
    @Json(name = "timezone") val timezone: String,
    @Json(name = "current") val current: CurrentDto,
    @Json(name = "hourly") val hourly: List<HourlyDto>?,
    @Json(name = "daily") val daily: List<DailyDto>?,
    @Json(name = "alerts") val alerts: List<AlertDto>?
)

@JsonClass(generateAdapter = true)
data class CurrentDto(
    @Json(name = "dt") val dt: Long,
    @Json(name = "temp") val temp: Double,
    @Json(name = "feels_like") val feelsLike: Double,
    @Json(name = "humidity") val humidity: Int,
    @Json(name = "wind_speed") val windSpeed: Double,
    @Json(name = "wind_deg") val windDeg: Int,
    @Json(name = "weather") val weather: List<WeatherConditionDto>
)

@JsonClass(generateAdapter = true)
data class HourlyDto(
    @Json(name = "dt") val dt: Long,
    @Json(name = "temp") val temp: Double,
    @Json(name = "pop") val pop: Double,
    @Json(name = "weather") val weather: List<WeatherConditionDto>
)

@JsonClass(generateAdapter = true)
data class DailyDto(
    @Json(name = "dt") val dt: Long,
    @Json(name = "sunrise") val sunrise: Long,
    @Json(name = "sunset") val sunset: Long,
    @Json(name = "temp") val temp: TempDto,
    @Json(name = "weather") val weather: List<WeatherConditionDto>,
    @Json(name = "pop") val pop: Double,
    @Json(name = "summary") val summary: String?
)

@JsonClass(generateAdapter = true)
data class TempDto(
    @Json(name = "min") val min: Double,
    @Json(name = "max") val max: Double
)

@JsonClass(generateAdapter = true)
data class WeatherConditionDto(
    @Json(name = "id") val id: Int,
    @Json(name = "main") val main: String,
    @Json(name = "description") val description: String,
    @Json(name = "icon") val icon: String
)

@JsonClass(generateAdapter = true)
data class AlertDto(
    @Json(name = "sender_name") val senderName: String,
    @Json(name = "event") val event: String,
    @Json(name = "start") val start: Long,
    @Json(name = "end") val end: Long,
    @Json(name = "description") val description: String,
    @Json(name = "tags") val tags: List<String>?
)

fun WeatherResponseDto.toWeatherCurrent(cityName: String): WeatherCurrent {
    val weatherCondition = current.weather.firstOrNull()
    return WeatherCurrent(
        timestamp = current.dt,
        temp = current.temp,
        feelsLike = current.feelsLike,
        humidity = current.humidity,
        windSpeed = current.windSpeed,
        windDeg = current.windDeg,
        conditionId = weatherCondition?.id ?: 0,
        conditionText = weatherCondition?.description ?: "",
        iconUrl = "https://openweathermap.org/img/wn/${weatherCondition?.icon}@2x.png",
        cityName = cityName,
        latitude = lat,
        longitude = lon
    )
}

fun HourlyDto.toHourlyForecast(): HourlyForecast {
    val weatherCondition = weather.firstOrNull()
    return HourlyForecast(
        timestamp = dt,
        temp = temp,
        pop = pop,
        iconUrl = "https://openweathermap.org/img/wn/${weatherCondition?.icon}@2x.png",
        conditionText = weatherCondition?.description ?: ""
    )
}

fun DailyDto.toDailyForecast(): DailyForecast {
    val weatherCondition = weather.firstOrNull()
    return DailyForecast(
        date = dt,
        minTemp = temp.min,
        maxTemp = temp.max,
        sunrise = sunrise,
        sunset = sunset,
        summary = summary ?: weatherCondition?.description ?: "",
        iconUrl = "https://openweathermap.org/img/wn/${weatherCondition?.icon}@2x.png",
        pop = pop
    )
}

fun AlertDto.toWeatherAlert(): WeatherAlert {
    val severity = tags?.firstOrNull() ?: "Moderate"
    return WeatherAlert(
        id = "${event}_$start",
        sender = senderName,
        event = event,
        start = start,
        end = end,
        description = description,
        severity = severity
    )
}
