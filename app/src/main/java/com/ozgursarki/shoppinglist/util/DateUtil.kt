package com.ozgursarki.shoppinglist.util

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {

    companion object {
        val LOCALE = Locale("en", "EN")
        fun getDate(milliSeconds: Long): String {
            val formatter = SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale("en"))

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = milliSeconds
            return formatter.format(calendar.time)
        }

        fun getDateWithoutHour(milliSeconds: Long): String {
            val formatter = SimpleDateFormat("EEE, d MMM yyyy ", Locale("en"))

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = milliSeconds
            return formatter.format(calendar.time)
        }
    }
}