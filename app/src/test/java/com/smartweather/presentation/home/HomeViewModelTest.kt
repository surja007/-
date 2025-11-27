package com.smartweather.presentation.home

import app.cash.turbine.test
import com.smartweather.domain.model.*
import com.smartweather.domain.usecase.*
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase
    private lateinit var getAQIUseCase: GetAQIUseCase
    private lateinit var getWeatherAlertsUseCase: GetWeatherAlertsUseCase
    private lateinit var manageFavoritesUseCase: ManageFavoritesUseCase
    private lateinit var getHourlyForecastUseCase: com.smartweather.domain.usecase.GetHourlyForecastUseCase
    private lateinit var getDailyForecastUseCase: com.smartweather.domain.usecase.GetDailyForecastUseCase
    private lateinit var locationService: com.smartweather.data.location.LocationService
    private lateinit var viewModel: HomeViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        
        getCurrentWeatherUseCase = mockk()
        getAQIUseCase = mockk()
        getWeatherAlertsUseCase = mockk()
        manageFavoritesUseCase = mockk()
        getHourlyForecastUseCase = mockk()
        getDailyForecastUseCase = mockk()
        locationService = mockk()
        
        viewModel = HomeViewModel(
            getCurrentWeatherUseCase,
            getAQIUseCase,
            getWeatherAlertsUseCase,
            manageFavoritesUseCase,
            getHourlyForecastUseCase,
            getDailyForecastUseCase,
            locationService
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadWeatherData should emit Success state on successful data fetch`() = runTest {
        // Given
        val lat = 40.7128
        val lon = -74.0060
        val cityName = "New York"
        
        val weather = WeatherCurrent(
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

        val aqi = AQIInfo(
            aqi = 50,
            category = "Good",
            pollutants = mapOf("PM2.5" to 12.0),
            timestamp = 1234567890L,
            advice = "Air quality is satisfactory"
        )

        coEvery { getCurrentWeatherUseCase(lat, lon, cityName, false) } returns Result.success(weather)
        coEvery { getAQIUseCase(lat, lon) } returns Result.success(aqi)
        coEvery { getWeatherAlertsUseCase(lat, lon) } returns Result.success(emptyList())
        coEvery { getHourlyForecastUseCase(lat, lon) } returns Result.success(emptyList())
        coEvery { getDailyForecastUseCase(lat, lon) } returns Result.success(emptyList())

        // When
        viewModel.loadWeatherData(lat, lon, cityName)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertTrue(state is HomeUiState.Success)
        assertEquals(weather, (state as HomeUiState.Success).weather)
        assertEquals(aqi, state.aqi)
    }

    @Test
    fun `loadWeatherData should emit Error state on failure`() = runTest {
        // Given
        val lat = 40.7128
        val lon = -74.0060
        val cityName = "New York"
        val errorMessage = "Network error"

        coEvery { getCurrentWeatherUseCase(lat, lon, cityName, false) } returns 
            Result.failure(Exception(errorMessage))

        // When
        viewModel.loadWeatherData(lat, lon, cityName)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertTrue(state is HomeUiState.Error)
        assertEquals(errorMessage, (state as HomeUiState.Error).message)
    }
}
