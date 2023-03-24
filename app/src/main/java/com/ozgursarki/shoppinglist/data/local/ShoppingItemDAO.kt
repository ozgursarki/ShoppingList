package com.ozgursarki.shoppinglist.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.ozgursarki.shoppinglist.data.local.entities.ShoppingItemEntity

@Dao
interface ShoppingItemDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ShoppingItemEntity)
}