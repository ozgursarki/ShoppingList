package com.ozgursarki.shoppinglist.data.local

import com.ozgursarki.shoppinglist.data.local.entities.relations.ShoppingListWithItemsEntity
import com.ozgursarki.shoppinglist.data.mapper.toShoppingItemEntity
import com.ozgursarki.shoppinglist.data.mapper.toShoppingList
import com.ozgursarki.shoppinglist.data.mapper.toShoppingListEntity
import com.ozgursarki.shoppinglist.data.mapper.toShoppingListItems
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem
import com.ozgursarki.shoppinglist.domain.model.ShoppingList
import com.ozgursarki.shoppinglist.domain.model.ShoppingListWithItems
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShoppingRepository @Inject constructor(
    private val shoppingListDAO: ShoppingListDAO,
    private val shoppingItemDAO: ShoppingItemDAO,
    private val defaultPreferences: DefaultPreferences
){
    suspend fun insertShoppingList(shoppingList: ShoppingList) {
        shoppingListDAO.insertList(shoppingList.toShoppingListEntity())
    }

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItemDAO.insertItem(shoppingItem.toShoppingItemEntity())
    }

    fun getListWithItems(listID: Long) : Result<Flow<List<ShoppingListWithItemsEntity>>> {
        return try {
            val shoppingListWithItems = shoppingListDAO.getListWithItems(listID)
            Result.success(shoppingListWithItems)
        }catch (e: Exception) {
            Result.failure(e)
        }

    }

    suspend fun updateShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItemDAO.updateShoppingItem(shoppingItem.toShoppingItemEntity())
    }

    suspend fun deleteRelatedShoppingItem(listID: Long) {
        shoppingItemDAO.deleteRelatedShoppingItem(listID)
    }

    suspend fun getAllList(): List<ShoppingList> {
        return shoppingListDAO.getAllList().map {
            it.toShoppingList()
        }
    }

    suspend fun getListWithItemsWithoutFlow(listID: Long): List<ShoppingListWithItems> {
        return shoppingListDAO.getListWithItemsWithoutFlow(listID).map {
            it.toShoppingListItems()
        }
    }

    suspend fun deleteShoppingList(listID: Long) {
        shoppingListDAO.deleteShoppingList(listID)
    }

    fun saveListID(listID: Long) {
        defaultPreferences.saveListID(listID)
    }

    fun getListID(): Long {
        return defaultPreferences.getListID()
    }
}