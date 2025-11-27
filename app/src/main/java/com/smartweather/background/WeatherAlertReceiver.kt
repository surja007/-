package com.smartweather.background

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

/**
 * AlarmManager-based alternative to FCM for offline weather alerts
 * Checks weather alerts every 15-30 minutes without requiring internet connection
 * 
 * Note: BroadcastReceivers cannot use Hilt injection directly.
 * We use WorkManager instead for dependency injection support.
 */
class WeatherAlertReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // Trigger WorkManager to do the actual work with proper DI
        val workRequest = OneTimeWorkRequestBuilder<WeatherRefreshWorker>()
            .build()
        
        WorkManager.getInstance(context).enqueue(workRequest)
    }

    companion object {
        private const val ALARM_INTERVAL = 30 * 60 * 1000L // 30 minutes

        /**
         * Schedule periodic weather alert checks using AlarmManager
         * This is an offline alternative to FCM
         */
        fun scheduleAlertChecks(context: Context) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, WeatherAlertReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            // Cancel any existing alarms
            alarmManager.cancel(pendingIntent)

            // Schedule repeating alarm
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis() + ALARM_INTERVAL,
                    ALARM_INTERVAL,
                    pendingIntent
                )
            } else {
                alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis() + ALARM_INTERVAL,
                    ALARM_INTERVAL,
                    pendingIntent
                )
            }
        }

        /**
         * Cancel scheduled alert checks
         */
        fun cancelAlertChecks(context: Context) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, WeatherAlertReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            alarmManager.cancel(pendingIntent)
        }
    }
}
