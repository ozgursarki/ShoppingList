package com.ozgursarki.shoppinglist.domain.model

import androidx.room.Embedded
import androidx.room.Relation
import com.ozgursarki.shoppinglist.data.local.entities.ShoppingItemEntity
import com.ozgursarki.shoppinglist.data.local.entities.ShoppingListEntity

data class ShoppingListWithItems(
    val shoppingList: ShoppingList,
    val shoppingItemList: List<ShoppingItem>
)