package com.example.videoapp.service.notification

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorkMenage(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    override fun doWork(): Result {
        
    }
}