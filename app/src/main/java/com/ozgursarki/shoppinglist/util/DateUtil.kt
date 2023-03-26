package com.ozgursarki.shoppinglist.util

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {

    companion object {
        val LOCALE = Locale("tr", "TR")
        fun getDateInTurkish(milliSeconds: Long): String {
            val formatter = SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale("tr"))

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = milliSeconds
            return formatter.format(calendar.time)
        }
    }
}