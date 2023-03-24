package com.ozgursarki.shoppinglist.domain.usecase

import com.ozgursarki.shoppinglist.data.local.ShoppingRepository
import com.ozgursarki.shoppinglist.data.local.entities.relations.ShoppingListWithItems
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetShoppingListWithItems @Inject constructor(
    private val repository: ShoppingRepository
) {

    operator fun invoke(listID: Long): Flow<List<ShoppingListWithItems>> {
        return repository.getListWithItems(listID)
    }
}