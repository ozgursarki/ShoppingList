package com.ozgursarki.shoppinglist.data.mapper

import com.ozgursarki.shoppinglist.data.local.entities.ShoppingItemEntity
import com.ozgursarki.shoppinglist.data.local.entities.ShoppingListEntity
import com.ozgursarki.shoppinglist.domain.ShoppingItem
import com.ozgursarki.shoppinglist.domain.ShoppingList

fun ShoppingList.toShoppingListEntity(): ShoppingListEntity {
    return ShoppingListEntity(listID)
}

fun ShoppingListEntity.toShoppingList(): ShoppingList {
    return ShoppingList(listID)
}

fun ShoppingItem.toShoppingItemEntity(): ShoppingItemEntity {
    return ShoppingItemEntity(
        itemID,
        name,
        count,
        listID
    )
}

fun ShoppingItemEntity.toShoppingItem(): ShoppingItem {
    return ShoppingItem(
        itemID,
        name,
        count,
        listID
    )
}