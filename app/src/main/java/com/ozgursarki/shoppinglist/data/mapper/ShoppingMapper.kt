package com.ozgursarki.shoppinglist.data.mapper

import com.ozgursarki.shoppinglist.data.local.entities.ShoppingItemEntity
import com.ozgursarki.shoppinglist.data.local.entities.ShoppingListEntity
import com.ozgursarki.shoppinglist.data.local.entities.relations.ShoppingListWithItemsEntity
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem
import com.ozgursarki.shoppinglist.domain.model.ShoppingList
import com.ozgursarki.shoppinglist.domain.model.ShoppingListWithItems

fun ShoppingList.toShoppingListEntity(): ShoppingListEntity {
    return ShoppingListEntity(listID, ratio)
}

fun ShoppingListEntity.toShoppingList(): ShoppingList {
    return ShoppingList(listID, ratio)
}

fun ShoppingItem.toShoppingItemEntity(): ShoppingItemEntity {
    return ShoppingItemEntity(
        itemID,
        name,
        count,
        date,
        type,
        isDone,
        listID
    )
}

fun ShoppingItemEntity.toShoppingItem(): ShoppingItem {
    return ShoppingItem(
        itemID,
        name,
        count,
        listID,
        date,
        type,
        isDone
    )
}

fun ShoppingListWithItemsEntity.toShoppingListItems(): ShoppingListWithItems {
    val shoppingItemList = shoppingItemEntityList.map {
        it.toShoppingItem()
    }
    return ShoppingListWithItems(
        shoppingListEntity.toShoppingList(),
        shoppingItemList
    )
}
