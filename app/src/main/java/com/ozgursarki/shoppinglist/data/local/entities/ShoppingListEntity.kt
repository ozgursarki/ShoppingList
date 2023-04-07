package com.ozgursarki.shoppinglist.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shoppingList")
data class ShoppingListEntity(
    @PrimaryKey(autoGenerate = false)
    val listID: Long,
    val ratio: Int
)
