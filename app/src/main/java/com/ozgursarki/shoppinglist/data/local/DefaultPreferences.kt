package com.ozgursarki.shoppinglist.data.local

import android.content.SharedPreferences

class DefaultPreferences(
    private val sharedPref: SharedPreferences
) {

    private val LIST_ID = "listID"
    private val DELETE_TOOLTIP = "delete"
    private val SAVE_TOOLTIP = "save"
    private val ADD_TOOLTIP = "add"
    private val HISTORY_TOOLTIP = "history"

    fun saveListID(listID: Long) {
        sharedPref.edit()
            .putLong(LIST_ID, listID)
            .apply()
    }

    fun getListID(): Long {
        return sharedPref.getLong(LIST_ID, -1)
    }

    fun saveDelete() {
        sharedPref.edit()
            .putBoolean(DELETE_TOOLTIP, true)
            .apply()
    }

    fun getDelete(): Boolean {
        return sharedPref.getBoolean(DELETE_TOOLTIP, false)
    }

    fun saveSave() {
        sharedPref.edit()
            .putBoolean(SAVE_TOOLTIP, true)
            .apply()
    }

    fun getSave(): Boolean {
        return sharedPref.getBoolean(SAVE_TOOLTIP, false)
    }

    fun saveAdd() {
        sharedPref.edit()
            .putBoolean(ADD_TOOLTIP, true)
            .apply()
    }

    fun getAdd(): Boolean {
        return sharedPref.getBoolean(ADD_TOOLTIP, false)
    }

    fun saveHistory() {
        sharedPref.edit()
            .putBoolean(HISTORY_TOOLTIP, true)
            .apply()
    }

    fun getHistory(): Boolean {
        return sharedPref.getBoolean(HISTORY_TOOLTIP, false)
    }


}