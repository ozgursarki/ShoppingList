package com.ozgursarki.shoppinglist.services

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import android.widget.Toast
import com.ozgursarki.shoppinglist.R

class MyWidgetProvider : AppWidgetProvider() {

    companion object {
        const val ITEM_CLICKED_ACTION = "com.example.mywidget.ITEM_CLICKED"
        const val EXTRA_ITEM_POSITION = "com.example.mywidget.EXTRA_ITEM_POSITION"
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            // Create a RemoteViews object
            val remoteViews = RemoteViews(context.packageName, R.layout.my_widget_layout)

            // Set up the RecyclerView using a RemoteViewsService
            val intent = Intent(context, MyRemoteViewsService::class.java)
            remoteViews.setRemoteAdapter(R.id.widget_recycler_view, intent)

            // Set up a PendingIntent template for each item in the RecyclerView
            val clickIntent = Intent(context, MyWidgetProvider::class.java)
            clickIntent.action = ITEM_CLICKED_ACTION
            val clickPendingIntent = PendingIntent.getBroadcast(context, 0, clickIntent, 0)
            remoteViews.setPendingIntentTemplate(R.id.widget_recycler_view, clickPendingIntent)

            // Update the widget
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        if (intent.action == ITEM_CLICKED_ACTION) {
            val position = intent.getIntExtra(EXTRA_ITEM_POSITION, -1)
            if (position != -1) {
                // Handle the item click
                Toast.makeText(context, "Item clicked: $position", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

class MyRemoteViewsService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return MyRemoteViewsFactory(applicationContext)
    }
}

class MyRemoteViewsFactory(val context: Context) : RemoteViewsService.RemoteViewsFactory {

    private val data: List<NotificationModel> = listOf()

    override fun onCreate() {
    }

    override fun onDataSetChanged() {
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getViewAt(position: Int): RemoteViews {
        TODO("Not yet implemented")
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }
}