package com.ozgursarki.shoppinglist.util

import com.ozgursarki.shoppinglist.data.local.entities.relations.ShoppingListWithItemsEntity
import com.ozgursarki.shoppinglist.data.mapper.toShoppingListItems
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem
import com.ozgursarki.shoppinglist.presentation.enum.ItemType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object UseCaseHelper {

    fun filterListByType(
        shoppingList: ArrayList<ShoppingItem>,
        filteredByList: ArrayList<Any>,
        type: ItemType
    ): ArrayList<Any> {
        shoppingList.filter {
                it.type == type.type
            }.forEach { filteredListEntity ->
                filteredByList.add(filteredListEntity)
            }
        return filteredByList
    }
}