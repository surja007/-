package com.smartweather.domain.usecase

import com.smartweather.domain.model.AQIInfo
import com.smartweather.domain.repository.WeatherRepository
import javax.inject.Inject

class GetAQIUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(lat: Double, lon: Double): Result<AQIInfo> {
        return repository.getAQI(lat, lon)
    }
}
