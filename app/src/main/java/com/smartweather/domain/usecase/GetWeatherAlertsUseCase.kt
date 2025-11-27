package com.smartweather.domain.usecase

import com.smartweather.domain.model.WeatherAlert
import com.smartweather.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherAlertsUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(lat: Double, lon: Double): Result<List<WeatherAlert>> {
        return repository.getWeatherAlerts(lat, lon)
    }
}
