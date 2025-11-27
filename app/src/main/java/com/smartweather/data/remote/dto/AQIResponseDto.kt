package com.smartweather.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.smartweather.domain.model.AQIInfo

@JsonClass(generateAdapter = true)
data class AQIResponseDto(
    @Json(name = "list") val list: List<AQIDataDto>
)

@JsonClass(generateAdapter = true)
data class AQIDataDto(
    @Json(name = "dt") val dt: Long,
    @Json(name = "main") val main: AQIMainDto,
    @Json(name = "components") val components: AQIComponentsDto
)

@JsonClass(generateAdapter = true)
data class AQIMainDto(
    @Json(name = "aqi") val aqi: Int
)

@JsonClass(generateAdapter = true)
data class AQIComponentsDto(
    @Json(name = "co") val co: Double?,
    @Json(name = "no") val no: Double?,
    @Json(name = "no2") val no2: Double?,
    @Json(name = "o3") val o3: Double?,
    @Json(name = "so2") val so2: Double?,
    @Json(name = "pm2_5") val pm25: Double?,
    @Json(name = "pm10") val pm10: Double?,
    @Json(name = "nh3") val nh3: Double?
)

fun AQIDataDto.toAQIInfo(): AQIInfo {
    val pollutants = mutableMapOf<String, Double>()
    components.pm25?.let { pollutants["PM2.5"] = it }
    components.pm10?.let { pollutants["PM10"] = it }
    components.o3?.let { pollutants["O3"] = it }
    components.no2?.let { pollutants["NO2"] = it }
    components.co?.let { pollutants["CO"] = it }
    components.so2?.let { pollutants["SO2"] = it }

    val category = AQIInfo.getCategoryForAQI(main.aqi)
    val advice = AQIInfo.getAdviceForAQI(main.aqi)

    return AQIInfo(
        aqi = main.aqi,
        category = category,
        pollutants = pollutants,
        timestamp = dt,
        advice = advice
    )
}
