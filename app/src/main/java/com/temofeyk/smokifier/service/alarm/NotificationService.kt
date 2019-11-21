package com.temofeyk.smokifier.service.alarm


import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import java.util.*
import android.app.NotificationChannel
import android.media.AudioAttributes
import com.temofeyk.android.smokifier.R
import com.temofeyk.smokifier.screens.main.MainActivity
import androidx.core.app.NotificationCompat


class NotificationService : IntentService("NotificationService") {

    @SuppressLint("NewApi")
    private fun createChannel() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library

            val context = this.applicationContext
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(CHANNEL_ID, getString(R.string.notification), importance)
            notificationChannel.enableVibration(true)
            notificationChannel.setShowBadge(true)
            notificationChannel.enableLights(true)

            val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE).build()
            notificationChannel.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), audioAttributes)
            notificationChannel.lightColor = R.color.colorLightIndicator
            notificationChannel.description = getString(R.string.notification_channel_description)
            notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            notificationManager.createNotificationChannel(notificationChannel)
        }

    }

    companion object {

        const val CHANNEL_ID = "smokifier.temofeyk.com.CHANNEL_ID"
        // NOTIFICATION_ID is a unique int for each notification that you must define
        const val NOTIFICATION_ID: Int = 1000
    }


    override fun onHandleIntent(intent: Intent?) {

        //Create Channel
        createChannel()


        var timestamp: Long = 0
        if (intent != null && intent.extras != null) {
            timestamp = intent.extras!!.getLong("timestamp")
        }




        if (timestamp > 0) {


            val context = this.applicationContext
            val notifyIntent = Intent(this, MainActivity::class.java)

            val title = "Курить!"
            val message = "Вам давно бы пора покурить."

            notifyIntent.putExtra("title", title)
            notifyIntent.putExtra("message", message)
            notifyIntent.putExtra("notification", true)

            notifyIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timestamp


            val pendingIntent = PendingIntent.getActivity(context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            val res = this.resources
            val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

            val mNotification = NotificationCompat.Builder(this, CHANNEL_ID)
            // Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentTitle(title)
                .setStyle(NotificationCompat.BigTextStyle()
                    .bigText(message))
                .setSound(uri)
                .setContentText(message).build()

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(NOTIFICATION_ID, mNotification)
        }


    }
}