package com.ozgursarki.shoppinglist.data.local

import android.content.SharedPreferences

class DefaultPreferences(
    private val sharedPref: SharedPreferences
) {

    private val LIST_ID = "listID"

    fun saveListID(listID: Long) {
        sharedPref.edit()
            .putLong(LIST_ID, listID)
            .apply()
    }

    fun getListID(): Long {
        return sharedPref.getLong(LIST_ID, -1)
    }


}