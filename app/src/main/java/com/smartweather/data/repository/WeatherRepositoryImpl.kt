package com.smartweather.data.repository

import com.smartweather.BuildConfig
import com.smartweather.data.local.dao.WeatherCacheDao
import com.smartweather.data.local.entity.WeatherCacheEntity
import com.smartweather.data.remote.WeatherApiService
import com.smartweather.data.remote.dto.*
import com.smartweather.domain.model.*
import com.smartweather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val apiService: WeatherApiService,
    private val cacheDao: WeatherCacheDao
) : WeatherRepository {

    private val cacheExpiryMs = 30 * 60 * 1000L // 30 minutes

    override suspend fun getCurrentWeather(
        lat: Double,
        lon: Double,
        cityName: String,
        forceRefresh: Boolean
    ): Result<WeatherCurrent> {
        return try {
            val locationKey = "$lat,$lon"
            
            // Check cache first
            if (!forceRefresh) {
                val cached = cacheDao.getWeatherCache(locationKey)
                if (cached != null && System.currentTimeMillis() - cached.cachedAt < cacheExpiryMs) {
                    return Result.success(cached.toDomain())
                }
            }

            // Fetch from API
            val response = apiService.getWeatherData(
                lat = lat,
                lon = lon,
                apiKey = BuildConfig.WEATHER_API_KEY
            )
            
            val weather = response.toWeatherCurrent(cityName)
            
            // Cache the result
            cacheDao.insertWeatherCache(
                WeatherCacheEntity.fromDomain(weather, locationKey)
            )
            
            Result.success(weather)
        } catch (e: Exception) {
            // Try to return cached data on error
            val locationKey = "$lat,$lon"
            val cached = cacheDao.getWeatherCache(locationKey)
            if (cached != null) {
                Result.success(cached.toDomain())
            } else {
                Result.failure(e)
            }
        }
    }

    override suspend fun getHourlyForecast(
        lat: Double,
        lon: Double
    ): Result<List<HourlyForecast>> = try {
        val response = apiService.getWeatherData(
            lat = lat,
            lon = lon,
            apiKey = BuildConfig.WEATHER_API_KEY
        )
        
        val hourly = response.hourly?.take(48)?.map { it.toHourlyForecast() } ?: emptyList()
        Result.success(hourly)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getDailyForecast(
        lat: Double,
        lon: Double
    ): Result<List<DailyForecast>> = try {
        val response = apiService.getWeatherData(
            lat = lat,
            lon = lon,
            apiKey = BuildConfig.WEATHER_API_KEY
        )
        
        val daily = response.daily?.map { it.toDailyForecast() } ?: emptyList()
        Result.success(daily)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getAQI(lat: Double, lon: Double): Result<AQIInfo> = try {
        val response = apiService.getAQI(
            lat = lat,
            lon = lon,
            apiKey = BuildConfig.WEATHER_API_KEY
        )
        
        val aqiInfo = response.list.firstOrNull()?.toAQIInfo()
        if (aqiInfo != null) {
            Result.success(aqiInfo)
        } else {
            Result.failure(Exception("No AQI data available"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getWeatherAlerts(
        lat: Double,
        lon: Double
    ): Result<List<WeatherAlert>> = try {
        val response = apiService.getWeatherData(
            lat = lat,
            lon = lon,
            apiKey = BuildConfig.WEATHER_API_KEY
        )
        
        val alerts = response.alerts?.map { it.toWeatherAlert() } ?: emptyList()
        Result.success(alerts)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun clearExpiredCache() {
        val expiryTime = System.currentTimeMillis() - cacheExpiryMs
        cacheDao.deleteExpiredCache(expiryTime)
    }
}
