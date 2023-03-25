package com.ozgursarki.shoppinglist.domain.usecase

data class ShoppingUseCase(
    val insertShoppingItem: InsertShoppingItem,
    val insertShoppingList: InsertShoppingList,
    val getShoppingListWithItems: GetShoppingListWithItems,
    val updateShoppingItem: UpdateShoppingItem,
    val deleteRelatedShoppingItems: DeleteRelatedShoppingItems,
    val getAllShoppingList: GetAllShoppingList
)
