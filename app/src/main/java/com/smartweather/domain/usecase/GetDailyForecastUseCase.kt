package com.smartweather.domain.usecase

import com.smartweather.domain.model.DailyForecast
import com.smartweather.domain.repository.WeatherRepository
import javax.inject.Inject

class GetDailyForecastUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(lat: Double, lon: Double): Result<List<DailyForecast>> {
        return repository.getDailyForecast(lat, lon)
    }
}
