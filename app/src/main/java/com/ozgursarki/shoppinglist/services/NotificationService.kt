package com.ozgursarki.shoppinglist.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.ozgursarki.shoppinglist.R
import com.ozgursarki.shoppinglist.data.local.ShoppingDatabase
import kotlinx.coroutines.*

class NotificationService: Service() {

    private lateinit var builder: NotificationCompat.Builder
    private lateinit var notificationManager: NotificationManager
    private lateinit var scope: CoroutineScope
    private var title: String = "My Notification Title"
    private lateinit var handler: Handler

    companion object {
        private const val CHANNEL_ID = "my_channel_id"
        private const val NOTIFICATION_ID = 1
    }


    override fun onCreate() {
        handler = Handler()
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel()
        builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Notification Title")
            .setContentText("Notification Text")
            .setSmallIcon(R.drawable.ic_shopping)

        startForeground(NOTIFICATION_ID, builder.build())

        scope = CoroutineScope(Dispatchers.IO)

    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        updateNotification()
        return START_NOT_STICKY
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Notification Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun updateNotification() {
        scope.launch {
            val unFinishedList = ShoppingDatabase.invoke(applicationContext).shoppingListDao().getUnfinishedList()
            var content : String = ""

            unFinishedList.forEach {
                content += "${it.ratio} \n"
            }
            builder.setContentText(content)
            notificationManager.notify(NOTIFICATION_ID, builder.build())
            handler.postDelayed({
                updateNotification()
            }, 3000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
        scope.cancel()
    }


}