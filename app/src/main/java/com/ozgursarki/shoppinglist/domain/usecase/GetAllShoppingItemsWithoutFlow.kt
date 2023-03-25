package com.ozgursarki.shoppinglist.domain.usecase

import com.ozgursarki.shoppinglist.data.local.ShoppingRepository
import com.ozgursarki.shoppinglist.domain.model.ShoppingListWithItems
import javax.inject.Inject

class GetAllShoppingItemsWithoutFlow @Inject constructor(
    private val repository: ShoppingRepository
) {
    suspend operator fun invoke(listID: Long): List<ShoppingListWithItems> {
        return repository.getListWithItemsWithoutFlow(listID)
    }
}