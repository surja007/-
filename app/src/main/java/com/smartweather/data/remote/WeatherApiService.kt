package com.smartweather.data.remote

import com.smartweather.data.remote.dto.AQIResponseDto
import com.smartweather.data.remote.dto.WeatherResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    
    @GET("data/3.0/onecall")
    suspend fun getWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
        @Query("exclude") exclude: String = "minutely"
    ): WeatherResponseDto

    @GET("data/2.5/air_pollution")
    suspend fun getAQI(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String
    ): AQIResponseDto

    @GET("data/2.5/air_pollution/forecast")
    suspend fun getAQIForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String
    ): AQIResponseDto
}
