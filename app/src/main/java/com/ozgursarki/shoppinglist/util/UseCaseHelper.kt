package com.ozgursarki.shoppinglist.util

import com.ozgursarki.shoppinglist.data.local.entities.relations.ShoppingListWithItemsEntity
import com.ozgursarki.shoppinglist.data.mapper.toShoppingListItems
import com.ozgursarki.shoppinglist.presentation.enum.ItemType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object UseCaseHelper {

    fun filterListByType(
        list: Flow<List<ShoppingListWithItemsEntity>>,
        filteredByList: ArrayList<ShoppingListWithItemsEntity>,
        type: ItemType
    ): ArrayList<ShoppingListWithItemsEntity> {
        list.map { shoppingList ->
            shoppingList.filter {
                it.toShoppingListItems().shoppingItemList[0].type == type.type
            }.forEach { filteredListEntity ->
                filteredByList.add(filteredListEntity)
            }
        }
        return filteredByList
    }
}