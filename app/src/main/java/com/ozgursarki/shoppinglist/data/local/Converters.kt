package com.ozgursarki.shoppinglist.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.ozgursarki.shoppinglist.data.local.entities.ShoppingItemEntity
import com.ozgursarki.shoppinglist.data.local.entities.ShoppingListEntity

object Converters {

    @TypeConverter
    fun fromStringToShoppingList(value: String?): ShoppingListEntity {
        return Gson().fromJson(value, ShoppingListEntity::class.java)
    }

    @TypeConverter
    fun fromShoppingListToString(shoppingListEntity: ShoppingListEntity): String? {
        return Gson().toJson(shoppingListEntity)
    }

    @TypeConverter
    fun fromStringToItem(value: String?): ShoppingItemEntity {
        return Gson().fromJson(value, ShoppingItemEntity::class.java)
    }

    @TypeConverter
    fun fromItemToString(item: ShoppingItemEntity): String? {
        return Gson().toJson(item)
    }
}