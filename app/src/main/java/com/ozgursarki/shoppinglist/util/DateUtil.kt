package com.ozgursarki.shoppinglist.util

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {

    companion object {
        val LOCALE_EN = Locale("en", "EN")
        val LOCALE_TR = Locale("tr", "TR")
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


        fun getDateInTurkish(milliSeconds: Long): String {
            val formatter = SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale("tr"))

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = milliSeconds
            return formatter.format(calendar.time)
        }

        fun getDateInTurkishWithoutHour(milliSeconds: Long): String {
            val formatter = SimpleDateFormat("EEE, d MMM yyyy ", Locale("tr"))

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = milliSeconds
            return formatter.format(calendar.time)
        }

        fun isToday(millis: Long, language: String): Boolean {
            val calendar = if (language == "en") {
                Calendar.getInstance(LOCALE_EN)
            }else {
                Calendar.getInstance(LOCALE_TR)
            }

            calendar.timeInMillis = millis
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            val today = Calendar.getInstance()
            return dayOfMonth == today.get(Calendar.DAY_OF_MONTH)
                    && month == today.get(Calendar.MONTH)
                    && year == today.get(Calendar.YEAR)
        }


    }
}