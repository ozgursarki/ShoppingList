package com.ozgursarki.shoppinglist.util

object ListHelper {

    fun <T> convertToList(list: List<T>): ArrayList<T> {
        return ArrayList(list)
    }
}