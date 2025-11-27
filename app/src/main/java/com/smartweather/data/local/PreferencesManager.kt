package com.smartweather.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "weather_preferences")

@Singleton
class PreferencesManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dataStore = context.dataStore

    companion object {
        val TEMPERATURE_UNIT = stringPreferencesKey("temperature_unit")
        val WIND_SPEED_UNIT = stringPreferencesKey("wind_speed_unit")
        val REFRESH_INTERVAL = intPreferencesKey("refresh_interval")
        val NOTIFICATIONS_ENABLED = booleanPreferencesKey("notifications_enabled")
        val SEVERE_ALERTS_ONLY = booleanPreferencesKey("severe_alerts_only")
        val WIFI_ONLY = booleanPreferencesKey("wifi_only")
        val THEME_MODE = stringPreferencesKey("theme_mode")
    }

    val temperatureUnit: Flow<String> = dataStore.data.map { preferences ->
        preferences[TEMPERATURE_UNIT] ?: "metric"
    }

    val windSpeedUnit: Flow<String> = dataStore.data.map { preferences ->
        preferences[WIND_SPEED_UNIT] ?: "m/s"
    }

    val refreshInterval: Flow<Int> = dataStore.data.map { preferences ->
        preferences[REFRESH_INTERVAL] ?: 30
    }

    val notificationsEnabled: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[NOTIFICATIONS_ENABLED] ?: true
    }

    val severeAlertsOnly: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[SEVERE_ALERTS_ONLY] ?: false
    }

    val wifiOnly: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[WIFI_ONLY] ?: false
    }

    val themeMode: Flow<String> = dataStore.data.map { preferences ->
        preferences[THEME_MODE] ?: "system"
    }

    suspend fun setTemperatureUnit(unit: String) {
        dataStore.edit { preferences ->
            preferences[TEMPERATURE_UNIT] = unit
        }
    }

    suspend fun setWindSpeedUnit(unit: String) {
        dataStore.edit { preferences ->
            preferences[WIND_SPEED_UNIT] = unit
        }
    }

    suspend fun setRefreshInterval(minutes: Int) {
        dataStore.edit { preferences ->
            preferences[REFRESH_INTERVAL] = minutes
        }
    }

    suspend fun setNotificationsEnabled(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[NOTIFICATIONS_ENABLED] = enabled
        }
    }

    suspend fun setSevereAlertsOnly(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[SEVERE_ALERTS_ONLY] = enabled
        }
    }

    suspend fun setWifiOnly(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[WIFI_ONLY] = enabled
        }
    }

    suspend fun setThemeMode(mode: String) {
        dataStore.edit { preferences ->
            preferences[THEME_MODE] = mode
        }
    }
}
