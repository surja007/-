package com.smartweather.presentation.alerts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartweather.domain.model.WeatherAlert
import com.smartweather.domain.usecase.GetWeatherAlertsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlertsViewModel @Inject constructor(
    private val getWeatherAlertsUseCase: GetWeatherAlertsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AlertsUiState>(AlertsUiState.Loading)
    val uiState: StateFlow<AlertsUiState> = _uiState.asStateFlow()

    fun loadAlerts(lat: Double, lon: Double) {
        viewModelScope.launch {
            _uiState.value = AlertsUiState.Loading

            try {
                val result = getWeatherAlertsUseCase(lat, lon)
                if (result.isSuccess) {
                    val alerts = result.getOrThrow()
                    val activeAlerts = alerts.filter { it.isActive() }
                    val pastAlerts = alerts.filter { !it.isActive() }
                    
                    _uiState.value = AlertsUiState.Success(
                        activeAlerts = activeAlerts,
                        pastAlerts = pastAlerts
                    )
                } else {
                    _uiState.value = AlertsUiState.Error(
                        result.exceptionOrNull()?.message ?: "Unknown error"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = AlertsUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun refresh(lat: Double, lon: Double) {
        loadAlerts(lat, lon)
    }
}

sealed class AlertsUiState {
    object Loading : AlertsUiState()
    data class Success(
        val activeAlerts: List<WeatherAlert>,
        val pastAlerts: List<WeatherAlert>
    ) : AlertsUiState()
    data class Error(val message: String) : AlertsUiState()
}
