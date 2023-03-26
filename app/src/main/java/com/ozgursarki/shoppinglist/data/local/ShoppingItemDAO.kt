package com.ozgursarki.shoppinglist.data.local

import androidx.room.*
import com.ozgursarki.shoppinglist.data.local.entities.ShoppingItemEntity

@Dao
interface ShoppingItemDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ShoppingItemEntity)

    @Update
    suspend fun updateShoppingItem(shoppingItemEntity: ShoppingItemEntity)

    @Query("DELETE FROM shoppingItem WHERE itemID = :itemID")
    suspend fun deleteRelatedShoppingItem(itemID: Long)

}