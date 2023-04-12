package com.ozgursarki.shoppinglist.domain.usecase

import com.ozgursarki.shoppinglist.data.local.ShoppingRepository
import com.ozgursarki.shoppinglist.domain.model.ShoppingList
import javax.inject.Inject

class GetShoppingListByDate @Inject constructor(
    private val repository: ShoppingRepository
) {

    suspend operator fun invoke(milliseconds: Long): List<ShoppingList> {
        return repository.getListsByDate(milliseconds)
    }
}