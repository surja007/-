package com.smartweather

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.*
import com.smartweather.background.WeatherRefreshWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class WeatherApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
        scheduleWeatherRefresh()
        scheduleOfflineAlerts()
    }

    private fun scheduleOfflineAlerts() {
        // Schedule AlarmManager-based alerts (offline alternative to FCM)
        com.smartweather.background.WeatherAlertReceiver.scheduleAlertChecks(this)
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val weatherChannel = NotificationChannel(
                WEATHER_CHANNEL_ID,
                "Weather Alerts",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Severe weather alerts and notifications"
            }

            val updateChannel = NotificationChannel(
                UPDATE_CHANNEL_ID,
                "Weather Updates",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Background weather updates"
            }

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(weatherChannel)
            notificationManager.createNotificationChannel(updateChannel)
        }
    }

    private fun scheduleWeatherRefresh() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val refreshRequest = PeriodicWorkRequestBuilder<WeatherRefreshWorker>(
            30, TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .setBackoffCriteria(
                BackoffPolicy.EXPONENTIAL,
                WorkRequest.MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS
            )
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "weather_refresh",
            ExistingPeriodicWorkPolicy.KEEP,
            refreshRequest
        )
    }

    companion object {
        const val WEATHER_CHANNEL_ID = "weather_alerts"
        const val UPDATE_CHANNEL_ID = "weather_updates"
    }
}
