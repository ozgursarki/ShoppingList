package com.ozgursarki.shoppinglist.domain.usecase

import com.ozgursarki.shoppinglist.data.local.ShoppingRepository
import javax.inject.Inject

class UpdateShoppingList @Inject constructor(
    private val repository: ShoppingRepository
) {
    suspend fun updateShoppingList(listID: Long, ratio: Int) {
        repository.updateShoppingList(listID, ratio)
    }
}