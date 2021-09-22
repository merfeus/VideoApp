package com.example.videoapp

import android.app.Application
import androidx.work.*
import com.example.videoapp.service.notification.NotificationWorkMenage
import dagger.hilt.android.HiltAndroidApp
import java.util.*
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class VideoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startWork()
    }

    private fun startWork() {
        WorkManager.getInstance(applicationContext).let {
            val workManager = WorkManager.getInstance(applicationContext)

            val constraints = Constraints.Builder()
                .setRequiresCharging(false)
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                .setRequiresBatteryNotLow(true)
                .build()

            val periodicWorkRequest = PeriodicWorkRequestBuilder<NotificationWorkMenage>(
                20,
                TimeUnit.MINUTES
            ).setConstraints(constraints).build()

            workManager.enqueueUniquePeriodicWork(
                UUID.randomUUID().toString(),
                ExistingPeriodicWorkPolicy.KEEP,
                periodicWorkRequest
            )

        }
    }
}