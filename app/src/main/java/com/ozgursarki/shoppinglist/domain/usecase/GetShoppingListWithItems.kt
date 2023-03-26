package com.ozgursarki.shoppinglist.domain.usecase

import com.ozgursarki.shoppinglist.data.local.ShoppingRepository
import com.ozgursarki.shoppinglist.data.local.entities.relations.ShoppingListWithItemsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetShoppingListWithItems @Inject constructor(
    private val repository: ShoppingRepository
) {

    operator fun invoke(listID: Long): Result<Flow<List<ShoppingListWithItemsEntity>>> {
        return repository.getListWithItems(listID)
    }
}