package com.ozgursarki.shoppinglist.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.ozgursarki.shoppinglist.R
import com.ozgursarki.shoppinglist.data.local.ShoppingDatabase
import com.ozgursarki.shoppinglist.util.DateUtil
import kotlinx.coroutines.*

class NotificationService: Service() {

    private lateinit var builder: NotificationCompat.Builder
    private lateinit var notificationManager: NotificationManager
    private lateinit var scope: CoroutineScope
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
            val finishedList = ShoppingDatabase.invoke(applicationContext).shoppingListDao().getFinishedList()

            val remoteViews = RemoteViews(applicationContext.packageName, R.layout.notification_layout)
            val unfinishedListSize = unFinishedList.filter {
                if (Resources.getSystem().configuration.locales.get(0).language == "tr") {
                    DateUtil.isToday(it.listID, "tr")
                }else {
                    DateUtil.isToday(it.listID, "en")
                }

            }.size

            val finishedListSize = finishedList.filter {
                if (Resources.getSystem().configuration.locales.get(0).language == "tr") {
                    DateUtil.isToday(it.listID, "tr")
                }else {
                    DateUtil.isToday(it.listID, "en")
                }

            }.size
            remoteViews.setTextViewText(R.id.unfinishedListTitle, getString(R.string.unfinished_list_title))
            remoteViews.setTextViewText(R.id.unfinishedListCount, unfinishedListSize.toString())
            remoteViews.setTextViewText(R.id.finishedListTitle, getString(R.string.finished_list_count))
            remoteViews.setTextViewText(R.id.finishedListCount, finishedListSize.toString())
            builder.setCustomContentView(remoteViews)
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