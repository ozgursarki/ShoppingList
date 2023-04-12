package com.ozgursarki.shoppinglist.util

import android.content.Context
import android.content.res.Resources
import com.ozgursarki.shoppinglist.R
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

        fun getSystemLocale(): Locale {
            return if (Resources.getSystem().configuration.locales.get(0).language == "tr") {
                LOCALE_TR
            } else {
                LOCALE_EN
            }
        }

        fun parseDateText(
            context: Context,
            date: Calendar
        ): String {
            val dateFormat = SimpleDateFormat("dd-MM-yyyy", getSystemLocale())

            // Get today's date
            val today = Calendar.getInstance()

            // Add or subtract a day to compare with the given calendar date
            val tomorrow = Calendar.getInstance()
            tomorrow.add(Calendar.DAY_OF_YEAR, 1)

            val yesterday = Calendar.getInstance()
            yesterday.add(Calendar.DAY_OF_YEAR, -1)

            // Check if the given calendar date is today, tomorrow, or yesterday
            if (dateFormat.format(date.time) == dateFormat.format(today.time)) {
                return context.getString(R.string.today)
            } else if (dateFormat.format(date.time) == dateFormat.format(tomorrow.time)) {
                return context.getString(R.string.tomorrow)
            } else if (dateFormat.format(date.time) == dateFormat.format(yesterday.time)) {
                return context.getString(R.string.yesterday)
            }

            // If the date is not today, tomorrow, or yesterday, return it in the simple date format
            return dateFormat.format(date.time)

        }


    }
}