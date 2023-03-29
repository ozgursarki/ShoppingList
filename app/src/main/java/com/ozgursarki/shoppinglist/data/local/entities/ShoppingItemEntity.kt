package com.ozgursarki.shoppinglist.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shoppingItem")
data class ShoppingItemEntity(
    @PrimaryKey(autoGenerate = true)
    val itemID: Long = 0,
    val name: String,
    val count: Int,
    val date: String,
    @ColumnInfo(name = "listID") val listID: Long
)
