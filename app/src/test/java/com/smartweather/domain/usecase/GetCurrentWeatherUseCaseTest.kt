package com.smartweather.domain.usecase

import com.smartweather.domain.model.WeatherCurrent
import com.smartweather.domain.repository.WeatherRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetCurrentWeatherUseCaseTest {

    private lateinit var repository: WeatherRepository
    private lateinit var useCase: GetCurrentWeatherUseCase

    @Before
    fun setup() {
        repository = mockk()
        useCase = GetCurrentWeatherUseCase(repository)
    }

    @Test
    fun `invoke should return weather data on success`() = runTest {
        // Given
        val lat = 40.7128
        val lon = -74.0060
        val cityName = "New York"
        val expectedWeather = WeatherCurrent(
            timestamp = 1234567890L,
            temp = 20.0,
            feelsLike = 18.0,
            humidity = 65,
            windSpeed = 5.0,
            windDeg = 180,
            conditionId = 800,
            conditionText = "Clear sky",
            iconUrl = "https://example.com/icon.png",
            cityName = cityName,
            latitude = lat,
            longitude = lon
        )

        coEvery {
            repository.getCurrentWeather(lat, lon, cityName, false)
        } returns Result.success(expectedWeather)

        // When
        val result = useCase(lat, lon, cityName)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(expectedWeather, result.getOrNull())
        coVerify { repository.getCurrentWeather(lat, lon, cityName, false) }
    }

    @Test
    fun `invoke should return error on failure`() = runTest {
        // Given
        val lat = 40.7128
        val lon = -74.0060
        val cityName = "New York"
        val exception = Exception("Network error")

        coEvery {
            repository.getCurrentWeather(lat, lon, cityName, false)
        } returns Result.failure(exception)

        // When
        val result = useCase(lat, lon, cityName)

        // Then
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `invoke with forceRefresh should pass flag to repository`() = runTest {
        // Given
        val lat = 40.7128
        val lon = -74.0060
        val cityName = "New York"
        val expectedWeather = WeatherCurrent(
            timestamp = 1234567890L,
            temp = 20.0,
            feelsLike = 18.0,
            humidity = 65,
            windSpeed = 5.0,
            windDeg = 180,
            conditionId = 800,
            conditionText = "Clear sky",
            iconUrl = "https://example.com/icon.png",
            cityName = cityName,
            latitude = lat,
            longitude = lon
        )

        coEvery {
            repository.getCurrentWeather(lat, lon, cityName, true)
        } returns Result.success(expectedWeather)

        // When
        val result = useCase(lat, lon, cityName, forceRefresh = true)

        // Then
        assertTrue(result.isSuccess)
        coVerify { repository.getCurrentWeather(lat, lon, cityName, true) }
    }
}
