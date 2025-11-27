package com.smartweather.domain.usecase

import com.smartweather.domain.model.WeatherCurrent
import com.smartweather.domain.repository.WeatherRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(
        lat: Double,
        lon: Double,
        cityName: String,
        forceRefresh: Boolean = false
    ): Result<WeatherCurrent> {
        return repository.getCurrentWeather(lat, lon, cityName, forceRefresh)
    }
}
