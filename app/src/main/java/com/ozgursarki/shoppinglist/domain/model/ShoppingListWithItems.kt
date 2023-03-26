package com.ozgursarki.shoppinglist.domain.model


data class ShoppingListWithItems(
    val shoppingList: ShoppingList,
    val shoppingItemList: List<ShoppingItem>
)