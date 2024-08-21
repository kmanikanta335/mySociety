package com.society.FCM

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.society.R

class MySocietyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        // Handle FCM messages here.
        remoteMessage.notification?.let {
            showNotification(it.title, it.body)
        }
    }

    private fun showNotification(title: String?, body: String?) {
        // Build and show a notification
        val notificationBuilder = NotificationCompat.Builder(this, "announcement_channel")
            .setSmallIcon(R.drawable.app_logo)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // Send the new token to your server
        Log.d("FCM", "New token: $token")
    }
}

fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "Announcements"
        val descriptionText = "Notifications for new announcements"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel("announcement_channel", name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

fun showNotification(context: Context, title: String?, body: String?) {
    val channelId = "announcement_channel"
    val notificationId = 1

    // Create the notification channel if necessary
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "Announcements"
        val descriptionText = "Notifications for new announcements"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(channelId, name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    // Build the notification
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.app_logo) // Replace with your app's icon
        .setContentTitle(title)
        .setContentText(body)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setAutoCancel(true)

    // Show the notification
    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notify(notificationId, builder.build())
    }
}