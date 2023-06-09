package com.ozgursarki.shoppinglist.data.local

import androidx.room.*
import com.ozgursarki.shoppinglist.data.local.entities.ShoppingListEntity
import com.ozgursarki.shoppinglist.data.local.entities.relations.ShoppingListWithItemsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
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

    @Query("UPDATE shoppingList SET ratio = :ratio WHERE listID = :listID")
    suspend fun updateShoppingList(ratio: Int, listID: Long)

    @Query("SELECT * FROM shoppingList WHERE ratio < 100")
    suspend fun getUnfinishedList(): List<ShoppingListEntity>

    @Query("SELECT * FROM shoppingList WHERE ratio = 100")
    suspend fun getFinishedList(): List<ShoppingListEntity>

    @Query("SELECT * FROM shoppingList WHERE listID / 86400000 = :dateMillis / 86400000")
    suspend fun getListsByDate(dateMillis: Long): List<ShoppingListEntity>
}
