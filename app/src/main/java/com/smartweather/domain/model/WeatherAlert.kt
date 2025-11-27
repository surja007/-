package com.smartweather.domain.model

data class WeatherAlert(
    val id: String,
    val sender: String,
    val event: String,
    val start: Long,
    val end: Long,
    val description: String,
    val severity: String // "Extreme", "Severe", "Moderate", "Minor"
) {
    fun isActive(): Boolean {
        val now = System.currentTimeMillis() / 1000
        return now in start..end
    }
}
