@file:OptIn(ExperimentalMaterial3Api::class)

package com.smartweather.presentation.home

import androidx.compose.animation.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.smartweather.presentation.components.DailyForecastList
import com.smartweather.presentation.components.HourlyForecastCarousel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToFavorites: () -> Unit = {},
    onNavigateToAlerts: (Double, Double) -> Unit = { _, _ -> },
    onNavigateToSettings: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Weather") },
                actions = {
                    IconButton(onClick = { viewModel.refresh() }) {
                        Icon(Icons.Default.Refresh, "Refresh")
                    }
                    IconButton(onClick = onNavigateToFavorites) {
                        Icon(Icons.Default.Star, "Favorites")
                    }
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(Icons.Default.Settings, "Settings")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (val state = uiState) {
                is HomeUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is HomeUiState.Success -> {
                    WeatherContent(
                        weather = state.weather,
                        aqi = state.aqi,
                        alerts = state.alerts,
                        hourlyForecast = state.hourlyForecast,
                        dailyForecast = state.dailyForecast,
                        onToggleFavorite = { viewModel.toggleFavorite() },
                        onNavigateToAlerts = {
                            onNavigateToAlerts(state.weather.latitude, state.weather.longitude)
                        }
                    )
                }
                is HomeUiState.Error -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Error: ${state.message}",
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.refresh() }) {
                            Text("Retry")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherContent(
    weather: com.smartweather.domain.model.WeatherCurrent,
    aqi: com.smartweather.domain.model.AQIInfo?,
    alerts: List<com.smartweather.domain.model.WeatherAlert>,
    hourlyForecast: List<com.smartweather.domain.model.HourlyForecast>,
    dailyForecast: List<com.smartweather.domain.model.DailyForecast>,
    onToggleFavorite: () -> Unit,
    onNavigateToAlerts: () -> Unit
) {
    val scrollState = rememberScrollState()
    val weatherGradient = getWeatherGradient(weather.conditionId)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(weatherGradient))
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // City name and favorite button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = weather.cityName,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
            IconButton(onClick = onToggleFavorite) {
                Icon(
                    Icons.Default.Star,
                    contentDescription = "Favorite",
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Current time
        Text(
            text = SimpleDateFormat("EEEE, MMM d, h:mm a", Locale.getDefault())
                .format(Date(weather.timestamp * 1000)),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White.copy(alpha = 0.8f)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Temperature and icon
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "${weather.temp.toInt()}°",
                    style = MaterialTheme.typography.displayLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = weather.conditionText.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
            
            AsyncImage(
                model = weather.iconUrl,
                contentDescription = "Weather icon",
                modifier = Modifier.size(100.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Weather details card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.2f)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                WeatherDetailItem("Feels like", "${weather.feelsLike.toInt()}°")
                WeatherDetailItem("Humidity", "${weather.humidity}%")
                WeatherDetailItem("Wind", "${weather.windSpeed.toInt()} m/s")
            }
        }

        // AQI Card
        aqi?.let {
            Spacer(modifier = Modifier.height(16.dp))
            AQICard(aqi = it)
        }

        // Alerts
        if (alerts.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            AlertsCard(
                alertCount = alerts.size,
                onClick = onNavigateToAlerts
            )
        }

        // Hourly Forecast
        if (hourlyForecast.isNotEmpty()) {
            Spacer(modifier = Modifier.height(24.dp))
            HourlyForecastCarousel(hourlyForecasts = hourlyForecast)
        }

        // Daily Forecast
        if (dailyForecast.isNotEmpty()) {
            Spacer(modifier = Modifier.height(24.dp))
            DailyForecastList(dailyForecasts = dailyForecast)
        }

        // Bottom padding
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun WeatherDetailItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = Color.White.copy(alpha = 0.7f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun AQICard(aqi: com.smartweather.domain.model.AQIInfo) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = getAQIColor(aqi.aqi)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Air Quality",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = aqi.aqi.toString(),
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = aqi.category,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun AlertsCard(alertCount: Int, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.errorContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.Warning,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "$alertCount Active Alert${if (alertCount > 1) "s" else ""}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onErrorContainer
                )
            }
            Icon(
                Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onErrorContainer
            )
        }
    }
}

fun getWeatherGradient(conditionId: Int): List<Color> {
    return when (conditionId) {
        in 200..299 -> listOf(Color(0xFF616161), Color(0xFF37474F)) // Thunderstorm
        in 300..599 -> listOf(Color(0xFF546E7A), Color(0xFF78909C)) // Rain
        in 600..699 -> listOf(Color(0xFFB0BEC5), Color(0xFFECEFF1)) // Snow
        in 700..799 -> listOf(Color(0xFF90A4AE), Color(0xFFCFD8DC)) // Atmosphere
        800 -> listOf(Color(0xFF42A5F5), Color(0xFF64B5F6)) // Clear
        else -> listOf(Color(0xFF78909C), Color(0xFF90A4AE)) // Clouds
    }
}

fun getAQIColor(aqi: Int): Color {
    return when {
        aqi <= 50 -> Color(0xFF4CAF50)
        aqi <= 100 -> Color(0xFFFFEB3B)
        aqi <= 150 -> Color(0xFFFF9800)
        aqi <= 200 -> Color(0xFFF44336)
        aqi <= 300 -> Color(0xFF9C27B0)
        else -> Color(0xFF880E4F)
    }
}
