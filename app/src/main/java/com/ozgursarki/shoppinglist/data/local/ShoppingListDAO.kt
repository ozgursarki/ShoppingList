package com.ozgursarki.shoppinglist.data.local

import androidx.room.*
import com.ozgursarki.shoppinglist.data.local.entities.ShoppingListEntity
import com.ozgursarki.shoppinglist.data.local.entities.relations.ShoppingListWithItems
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(shoppingListEntity: ShoppingListEntity)

    @Transaction
    @Query("SELECT * FROM shoppingList WHERE listID = :listID")
    fun getListWithItems(listID: Long): Flow<List<ShoppingListWithItems>>
}

//@Query("SELECT * FROM NoteEntity WHERE note_location = :location")
//    suspend fun getNoteByLocation(location: String) : NoteEntity