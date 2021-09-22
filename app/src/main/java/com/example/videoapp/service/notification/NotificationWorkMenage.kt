package com.example.videoapp.service.notification

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorkMenage(appContext: Context, workerParameters: WorkerParameters) :
    Worker(appContext, workerParameters) {

    private val notificationHandler: NotificationHandler = NotificationHandler(appContext)

    override fun doWork(): Result {

        notificationHandler.createNotification(
            "Se liga", "Novas imagens disponiveis \uD83D\uDE09."
        ).let {
            NotificationManagerCompat.from(applicationContext)
                .notify((0..999).random(), it)
        }
        return Result.success()
    }
}