package com.ozgursarki.shoppinglist.data.local

import com.ozgursarki.shoppinglist.data.local.entities.relations.ShoppingListWithItemsEntity
import com.ozgursarki.shoppinglist.data.mapper.toShoppingItemEntity
import com.ozgursarki.shoppinglist.data.mapper.toShoppingListEntity
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem
import com.ozgursarki.shoppinglist.domain.model.ShoppingList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShoppingRepository @Inject constructor(
    private val shoppingListDAO: ShoppingListDAO,
    private val shoppingItemDAO: ShoppingItemDAO
){
    suspend fun insertShoppingList(shoppingList: ShoppingList) {
        shoppingListDAO.insertList(shoppingList.toShoppingListEntity())
    }

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItemDAO.insertItem(shoppingItem.toShoppingItemEntity())
    }

    fun getListWithItems(listID: Long) : Flow<List<ShoppingListWithItemsEntity>> {
        return shoppingListDAO.getListWithItems(listID)
    }
}