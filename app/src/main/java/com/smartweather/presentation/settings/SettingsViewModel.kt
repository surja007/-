package com.smartweather.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartweather.data.local.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    val temperatureUnit: StateFlow<String> = preferencesManager.temperatureUnit
        .stateIn(viewModelScope, SharingStarted.Eagerly, "metric")

    val windSpeedUnit: StateFlow<String> = preferencesManager.windSpeedUnit
        .stateIn(viewModelScope, SharingStarted.Eagerly, "m/s")

    val refreshInterval: StateFlow<Int> = preferencesManager.refreshInterval
        .stateIn(viewModelScope, SharingStarted.Eagerly, 30)

    val notificationsEnabled: StateFlow<Boolean> = preferencesManager.notificationsEnabled
        .stateIn(viewModelScope, SharingStarted.Eagerly, true)

    val severeAlertsOnly: StateFlow<Boolean> = preferencesManager.severeAlertsOnly
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    val wifiOnly: StateFlow<Boolean> = preferencesManager.wifiOnly
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    val themeMode: StateFlow<String> = preferencesManager.themeMode
        .stateIn(viewModelScope, SharingStarted.Eagerly, "system")

    fun setTemperatureUnit(unit: String) {
        viewModelScope.launch {
            preferencesManager.setTemperatureUnit(unit)
        }
    }

    fun setWindSpeedUnit(unit: String) {
        viewModelScope.launch {
            preferencesManager.setWindSpeedUnit(unit)
        }
    }

    fun setRefreshInterval(minutes: Int) {
        viewModelScope.launch {
            preferencesManager.setRefreshInterval(minutes)
        }
    }

    fun setNotificationsEnabled(enabled: Boolean) {
        viewModelScope.launch {
            preferencesManager.setNotificationsEnabled(enabled)
        }
    }

    fun setSevereAlertsOnly(enabled: Boolean) {
        viewModelScope.launch {
            preferencesManager.setSevereAlertsOnly(enabled)
        }
    }

    fun setWifiOnly(enabled: Boolean) {
        viewModelScope.launch {
            preferencesManager.setWifiOnly(enabled)
        }
    }

    fun setThemeMode(mode: String) {
        viewModelScope.launch {
            preferencesManager.setThemeMode(mode)
        }
    }
}
