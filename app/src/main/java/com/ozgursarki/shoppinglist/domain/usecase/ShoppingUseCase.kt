package com.ozgursarki.shoppinglist.domain.usecase

data class ShoppingUseCase(
    val insertShoppingItem: InsertShoppingItem,
    val insertShoppingList: InsertShoppingList,
    val getShoppingListWithItems: GetShoppingListWithItems,
    val updateShoppingItem: UpdateShoppingItem,
    val deleteRelatedShoppingItem: DeleteRelatedShoppingItem,
    val getAllShoppingList: GetAllShoppingList,
    val getAllShoppingItemsWithoutFlow: GetAllShoppingItemsWithoutFlow,
    val deleteShoppingList: DeleteShoppingList,
    val getListID: GetListID,
    val saveListID: SaveListID
)
