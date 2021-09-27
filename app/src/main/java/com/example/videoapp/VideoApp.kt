package com.example.videoapp

import android.app.Application
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.work.*
import com.example.videoapp.service.notification.NotificationHandler
import com.example.videoapp.service.notification.NotificationWorkMenage
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.*
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class VideoApp : Application(), LifecycleObserver {
    @Inject
    lateinit var notificationHandler: NotificationHandler


    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)

//    val retorno = CoroutineScope(Dispatchers.Main).async {
//        startWork()
//    }
//        CoroutineScope(Dispatchers.Main).launch {
//            retorno.await()
//        }


    }

   suspend fun startWork() {
       delay(2000)
        WorkManager.getInstance(applicationContext).let {
            val workManager = WorkManager.getInstance(applicationContext)

            val constraints = Constraints.Builder()
                .setRequiresCharging(false)
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                .setRequiresBatteryNotLow(true)
                .build()

            val periodicWorkRequest = PeriodicWorkRequestBuilder<NotificationWorkMenage>(
                1,
                TimeUnit.MINUTES
            ).setConstraints(constraints).build()

            workManager.enqueueUniquePeriodicWork(
                UUID.randomUUID().toString(),
                ExistingPeriodicWorkPolicy.KEEP,
                periodicWorkRequest
            )



        }
    }

    suspend  fun showNotification() {
        delay(3000)
        notificationHandler.createNotification("Se liga", "Novas imagens disponiveis \uD83D\uDE09")
            .let {
                val notificationManager = NotificationManagerCompat.from(applicationContext)
                notificationManager.notify(1, it)
            }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgrounded() {
        CoroutineScope(Dispatchers.Main).async{
//            showNotification()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onAppDestoy() {
        CoroutineScope(Dispatchers.Main).async{
            startWork()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForegrounded() {
        println("App in foreground")
    }
}