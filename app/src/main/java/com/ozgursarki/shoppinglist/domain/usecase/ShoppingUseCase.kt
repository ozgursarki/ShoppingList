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
    val saveListID: SaveListID,
    val filterListByItemType: FilterListByItemType,
    val deleteRelatedShoppingItems: DeleteRelatedShoppingItems,
    val getAddToolTip: GetAddToolTip,
    val getDeleteToolTip: GetDeleteToolTip,
    val getHistoryToolTip: GetHistoryToolTip,
    val getSaveToolTip: GetSaveToolTip,
    val saveAddToolTip: SaveAddToolTip,
    val saveDeleteToolTip: SaveDeleteToolTip,
    val saveHistoryToolTip: SaveHistoryToolTip,
    val saveSaveToolTip: SaveSaveToolTip,
    val updateShoppingList: UpdateShoppingList,
    val getShoppingListByDate: GetShoppingListByDate
)
