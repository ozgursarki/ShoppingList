package com.ozgursarki.shoppinglist.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.ozgursarki.shoppinglist.data.local.entities.ShoppingItemEntity
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem

@Dao
interface ShoppingItemDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ShoppingItemEntity)

    @Update
    suspend fun updateShoppingItem(shoppingItemEntity: ShoppingItemEntity)
}