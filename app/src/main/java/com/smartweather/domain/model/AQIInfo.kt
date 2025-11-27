package com.smartweather.domain.model

data class AQIInfo(
    val aqi: Int,
    val category: String,
    val pollutants: Map<String, Double>,
    val timestamp: Long,
    val advice: String
) {
    companion object {
        fun getCategoryForAQI(aqi: Int): String = when {
            aqi <= 50 -> "Good"
            aqi <= 100 -> "Moderate"
            aqi <= 150 -> "Unhealthy for Sensitive Groups"
            aqi <= 200 -> "Unhealthy"
            aqi <= 300 -> "Very Unhealthy"
            else -> "Hazardous"
        }

        fun getAdviceForAQI(aqi: Int): String = when {
            aqi <= 50 -> "Air quality is satisfactory. Enjoy outdoor activities!"
            aqi <= 100 -> "Air quality is acceptable. Unusually sensitive people should consider limiting prolonged outdoor exertion."
            aqi <= 150 -> "Members of sensitive groups may experience health effects. Reduce prolonged outdoor exertion."
            aqi <= 200 -> "Everyone may begin to experience health effects. Avoid prolonged outdoor exertion."
            aqi <= 300 -> "Health alert: everyone may experience serious health effects. Avoid outdoor activities."
            else -> "Health warnings of emergency conditions. Stay indoors."
        }
    }
}
