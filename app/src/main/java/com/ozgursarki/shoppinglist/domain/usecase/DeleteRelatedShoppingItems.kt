package com.ozgursarki.shoppinglist.domain.usecase

import com.ozgursarki.shoppinglist.data.local.ShoppingRepository
import javax.inject.Inject

class DeleteRelatedShoppingItems  @Inject constructor(
    private val repository: ShoppingRepository
){

    suspend operator fun invoke(listID: Long) {
        repository.deleteShoppingListItems(listID)
    }
}