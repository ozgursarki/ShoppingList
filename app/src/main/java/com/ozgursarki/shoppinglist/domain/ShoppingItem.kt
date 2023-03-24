package com.ozgursarki.shoppinglist.domain

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class ShoppingItem(
    val itemID: Long,
    val name: String,
    val count: Int,
    val listID: Long
)
