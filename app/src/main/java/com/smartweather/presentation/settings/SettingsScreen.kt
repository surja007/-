@file:OptIn(ExperimentalMaterial3Api::class)

package com.smartweather.presentation.settings

import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit = {}
) {
    val temperatureUnit by viewModel.temperatureUnit.collectAsState()
    val windSpeedUnit by viewModel.windSpeedUnit.collectAsState()
    val refreshInterval by viewModel.refreshInterval.collectAsState()
    val notificationsEnabled by viewModel.notificationsEnabled.collectAsState()
    val severeAlertsOnly by viewModel.severeAlertsOnly.collectAsState()
    val wifiOnly by viewModel.wifiOnly.collectAsState()
    val themeMode by viewModel.themeMode.collectAsState()

    var showTempUnitDialog by remember { mutableStateOf(false) }
    var showWindUnitDialog by remember { mutableStateOf(false) }
    var showRefreshDialog by remember { mutableStateOf(false) }
    var showThemeDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Units Section
            item {
                SectionHeader("Units")
            }

            item {
                SettingsItem(
                    icon = Icons.Default.Thermostat,
                    title = "Temperature Unit",
                    subtitle = if (temperatureUnit == "metric") "Celsius (°C)" else "Fahrenheit (°F)",
                    onClick = { showTempUnitDialog = true }
                )
            }

            item {
                SettingsItem(
                    icon = Icons.Default.Air,
                    title = "Wind Speed Unit",
                    subtitle = windSpeedUnit,
                    onClick = { showWindUnitDialog = true }
                )
            }

            // Refresh Section
            item {
                Spacer(modifier = Modifier.height(16.dp))
                SectionHeader("Data & Sync")
            }

            item {
                SettingsItem(
                    icon = Icons.Default.Refresh,
                    title = "Refresh Interval",
                    subtitle = "$refreshInterval minutes",
                    onClick = { showRefreshDialog = true }
                )
            }

            item {
                SettingsSwitchItem(
                    icon = Icons.Default.WifiOff,
                    title = "Wi-Fi Only",
                    subtitle = "Update weather only on Wi-Fi",
                    checked = wifiOnly,
                    onCheckedChange = { viewModel.setWifiOnly(it) }
                )
            }

            // Notifications Section
            item {
                Spacer(modifier = Modifier.height(16.dp))
                SectionHeader("Notifications")
            }

            item {
                SettingsSwitchItem(
                    icon = Icons.Default.Notifications,
                    title = "Enable Notifications",
                    subtitle = "Receive weather alerts",
                    checked = notificationsEnabled,
                    onCheckedChange = { viewModel.setNotificationsEnabled(it) }
                )
            }

            item {
                SettingsSwitchItem(
                    icon = Icons.Default.Warning,
                    title = "Severe Alerts Only",
                    subtitle = "Only notify for severe weather",
                    checked = severeAlertsOnly,
                    onCheckedChange = { viewModel.setSevereAlertsOnly(it) },
                    enabled = notificationsEnabled
                )
            }

            // Appearance Section
            item {
                Spacer(modifier = Modifier.height(16.dp))
                SectionHeader("Appearance")
            }

            item {
                SettingsItem(
                    icon = Icons.Default.Palette,
                    title = "Theme",
                    subtitle = when (themeMode) {
                        "light" -> "Light"
                        "dark" -> "Dark"
                        else -> "System Default"
                    },
                    onClick = { showThemeDialog = true }
                )
            }

            // About Section
            item {
                Spacer(modifier = Modifier.height(16.dp))
                SectionHeader("About")
            }

            item {
                SettingsItem(
                    icon = Icons.Default.Info,
                    title = "App Version",
                    subtitle = "1.0.0",
                    onClick = {}
                )
            }

            item {
                SettingsItem(
                    icon = Icons.Default.PrivacyTip,
                    title = "Privacy Policy",
                    subtitle = "View our privacy policy",
                    onClick = {}
                )
            }
        }
    }

    // Dialogs
    if (showTempUnitDialog) {
        SelectionDialog(
            title = "Temperature Unit",
            options = listOf("Celsius (°C)" to "metric", "Fahrenheit (°F)" to "imperial"),
            selectedValue = temperatureUnit,
            onSelect = { viewModel.setTemperatureUnit(it) },
            onDismiss = { showTempUnitDialog = false }
        )
    }

    if (showWindUnitDialog) {
        SelectionDialog(
            title = "Wind Speed Unit",
            options = listOf("m/s" to "m/s", "km/h" to "km/h", "mph" to "mph"),
            selectedValue = windSpeedUnit,
            onSelect = { viewModel.setWindSpeedUnit(it) },
            onDismiss = { showWindUnitDialog = false }
        )
    }

    if (showRefreshDialog) {
        SelectionDialog(
            title = "Refresh Interval",
            options = listOf(
                "15 minutes" to "15",
                "30 minutes" to "30",
                "1 hour" to "60",
                "2 hours" to "120",
                "6 hours" to "360"
            ),
            selectedValue = refreshInterval.toString(),
            onSelect = { viewModel.setRefreshInterval(it.toInt()) },
            onDismiss = { showRefreshDialog = false }
        )
    }

    if (showThemeDialog) {
        SelectionDialog(
            title = "Theme",
            options = listOf(
                "System Default" to "system",
                "Light" to "light",
                "Dark" to "dark"
            ),
            selectedValue = themeMode,
            onSelect = { viewModel.setThemeMode(it) },
            onDismiss = { showThemeDialog = false }
        )
    }
}

@Composable
fun SectionHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun SettingsItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Icon(
                Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun SettingsSwitchItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean = true
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = if (enabled) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                enabled = enabled
            )
        }
    }
}

@Composable
fun SelectionDialog(
    title: String,
    options: List<Pair<String, String>>,
    selectedValue: String,
    onSelect: (String) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = {
            Column {
                options.forEach { (label, value) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSelect(value)
                                onDismiss()
                            }
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = value == selectedValue,
                            onClick = {
                                onSelect(value)
                                onDismiss()
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(label)
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
