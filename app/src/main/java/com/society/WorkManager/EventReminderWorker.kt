package com.society.WorkManager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.society.R
import java.util.concurrent.TimeUnit

class EventReminderWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        // Show the event reminder notification
        showEventReminderNotification()
        return Result.success()
    }

    private fun showEventReminderNotification() {
        val notificationBuilder = NotificationCompat.Builder(applicationContext, "event_reminder_channel")
            .setSmallIcon(R.drawable.app_logo)
            .setContentTitle("Event Reminder")
            .setContentText("Don't forget about the upcoming event!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notificationBuilder.build())
    }
}

// Schedule the reminder
fun scheduleEventReminder(context: Context, delay: Long) {
    val workRequest = OneTimeWorkRequestBuilder<EventReminderWorker>()
        .setInitialDelay(delay, TimeUnit.MILLISECONDS)
        .build()

    WorkManager.getInstance(context).enqueue(workRequest)
}

fun createEventReminderNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "Event Reminders"
        val descriptionText = "Notifications for event reminders"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel("event_reminder_channel", name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}