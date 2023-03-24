package com.ozgursarki.shoppinglist.domain.usecase

data class ShoppingUseCase(
    val insertShoppingItem: InsertShoppingItem,
    val insertShoppingList: InsertShoppingList,
    val getShoppingListWithItems: GetShoppingListWithItems
)
