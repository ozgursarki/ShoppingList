package com.ozgursarki.shoppinglist.domain.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class ShoppingItem(
    val itemID: Long = 0,
    val name: String,
    var count: Int,
    val listID: Long
)
