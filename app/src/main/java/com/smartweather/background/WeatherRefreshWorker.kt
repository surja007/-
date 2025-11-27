package com.smartweather.background

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.smartweather.MainActivity
import com.smartweather.R
import com.smartweather.WeatherApplication
import com.smartweather.data.local.dao.FavoriteCityDao
import com.smartweather.data.location.LocationService
import com.smartweather.domain.repository.WeatherRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class WeatherRefreshWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted params: WorkerParameters,
    private val weatherRepository: WeatherRepository,
    private val favoriteCityDao: FavoriteCityDao,
    private val locationService: LocationService
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            // Clear expired cache
            weatherRepository.clearExpiredCache()
            
            // Check alerts for current location
            checkAlertsForCurrentLocation()
            
            Result.success()
        } catch (e: Exception) {
            if (runAttemptCount < 3) {
                Result.retry()
            } else {
                Result.failure()
            }
        }
    }

    private suspend fun checkAlertsForCurrentLocation() {
        try {
            // Get current location
            val locationResult = locationService.getLastKnownLocation()
            if (locationResult.isFailure) return

            val location = locationResult.getOrThrow()
            
            // Check for weather alerts
            val alertsResult = weatherRepository.getWeatherAlerts(
                location.latitude,
                location.longitude
            )
            
            if (alertsResult.isSuccess) {
                val alerts = alertsResult.getOrThrow()
                val activeAlerts = alerts.filter { it.isActive() }
                
                // Show notification for severe alerts
                activeAlerts.forEach { alert ->
                    if (alert.severity.lowercase() in listOf("extreme", "severe")) {
                        showAlertNotification(alert.event, alert.description, alert.severity)
                    }
                }
            }
        } catch (e: Exception) {
            // Silently fail - don't crash the worker
        }
    }

    private fun showAlertNotification(title: String, description: String, severity: String) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val priority = when (severity.lowercase()) {
            "extreme" -> NotificationCompat.PRIORITY_MAX
            "severe" -> NotificationCompat.PRIORITY_HIGH
            else -> NotificationCompat.PRIORITY_DEFAULT
        }

        val notification = NotificationCompat.Builder(context, WeatherApplication.WEATHER_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("⚠️ $title")
            .setContentText(description)
            .setStyle(NotificationCompat.BigTextStyle().bigText(description))
            .setPriority(priority)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(System.currentTimeMillis().toInt(), notification)
    }
}
