package com.ozgursarki.shoppinglist.domain.usecase

import com.ozgursarki.shoppinglist.data.local.ShoppingRepository
import javax.inject.Inject

class DeleteShoppingList @Inject constructor(
    private val shoppingRepository: ShoppingRepository
) {

    suspend operator fun invoke(listID: Long) {
        shoppingRepository.deleteShoppingList(listID)
    }
}