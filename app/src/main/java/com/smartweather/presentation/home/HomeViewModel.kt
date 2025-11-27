package com.smartweather.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartweather.data.location.LocationService
import com.smartweather.domain.model.*
import com.smartweather.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getAQIUseCase: GetAQIUseCase,
    private val getWeatherAlertsUseCase: GetWeatherAlertsUseCase,
    private val manageFavoritesUseCase: ManageFavoritesUseCase,
    private val getHourlyForecastUseCase: GetHourlyForecastUseCase,
    private val getDailyForecastUseCase: GetDailyForecastUseCase,
    private val locationService: LocationService
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _currentLocation = MutableStateFlow<Pair<Double, Double>?>(null)

    init {
        loadCurrentLocationWeather()
    }

    fun loadCurrentLocationWeather() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            
            val locationResult = locationService.getCurrentLocation()
            if (locationResult.isSuccess) {
                val location = locationResult.getOrThrow()
                loadWeatherData(location.latitude, location.longitude, location.cityName)
            } else {
                // Try last known location
                val lastLocationResult = locationService.getLastKnownLocation()
                if (lastLocationResult.isSuccess) {
                    val location = lastLocationResult.getOrThrow()
                    loadWeatherData(location.latitude, location.longitude, location.cityName)
                } else {
                    _uiState.value = HomeUiState.Error(
                        "Unable to get location. Please enable location services."
                    )
                }
            }
        }
    }

    fun loadWeatherData(lat: Double, lon: Double, cityName: String, forceRefresh: Boolean = false) {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            _currentLocation.value = Pair(lat, lon)

            try {
                val weatherResult = getCurrentWeatherUseCase(lat, lon, cityName, forceRefresh)
                val aqiResult = getAQIUseCase(lat, lon)
                val alertsResult = getWeatherAlertsUseCase(lat, lon)
                val hourlyResult = getHourlyForecastUseCase(lat, lon)
                val dailyResult = getDailyForecastUseCase(lat, lon)

                if (weatherResult.isSuccess) {
                    _uiState.value = HomeUiState.Success(
                        weather = weatherResult.getOrThrow(),
                        aqi = aqiResult.getOrNull(),
                        alerts = alertsResult.getOrNull() ?: emptyList(),
                        hourlyForecast = hourlyResult.getOrNull() ?: emptyList(),
                        dailyForecast = dailyResult.getOrNull() ?: emptyList()
                    )
                } else {
                    _uiState.value = HomeUiState.Error(
                        weatherResult.exceptionOrNull()?.message ?: "Unknown error"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun refresh() {
        _currentLocation.value?.let { (lat, lon) ->
            val currentState = _uiState.value
            if (currentState is HomeUiState.Success) {
                loadWeatherData(lat, lon, currentState.weather.cityName, forceRefresh = true)
            }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            val currentState = _uiState.value
            if (currentState is HomeUiState.Success) {
                val weather = currentState.weather
                val cityId = "${weather.latitude},${weather.longitude}"
                
                if (manageFavoritesUseCase.isFavorite(cityId)) {
                    manageFavoritesUseCase.removeFavorite(cityId)
                } else {
                    val favoriteCity = FavoriteCity(
                        id = cityId,
                        name = weather.cityName,
                        latitude = weather.latitude,
                        longitude = weather.longitude,
                        order = 0,
                        addedAt = System.currentTimeMillis()
                    )
                    manageFavoritesUseCase.addFavorite(favoriteCity)
                }
            }
        }
    }
}

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(
        val weather: WeatherCurrent,
        val aqi: AQIInfo?,
        val alerts: List<WeatherAlert>,
        val hourlyForecast: List<HourlyForecast> = emptyList(),
        val dailyForecast: List<DailyForecast> = emptyList()
    ) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}
