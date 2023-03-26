package com.ozgursarki.shoppinglist.data.local

import androidx.room.*
import com.ozgursarki.shoppinglist.data.local.entities.ShoppingListEntity
import com.ozgursarki.shoppinglist.data.local.entities.relations.ShoppingListWithItemsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(shoppingListEntity: ShoppingListEntity)

    @Transaction
    @Query("SELECT * FROM shoppingList WHERE listID = :listID")
    fun getListWithItems(listID: Long): Flow<List<ShoppingListWithItemsEntity>>

    @Query("SELECT * FROM shoppingList")
    suspend fun getAllList(): List<ShoppingListEntity>

    @Transaction
    @Query("SELECT * FROM shoppingList WHERE listID = :listID")
    suspend fun getListWithItemsWithoutFlow(listID: Long): List<ShoppingListWithItemsEntity>

    @Query("DELETE FROM shoppingList WHERE listID = :listID")
    suspend fun deleteShoppingList(listID: Long)

}
