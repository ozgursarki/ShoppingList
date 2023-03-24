package com.ozgursarki.shoppinglist.domain.usecase

import com.ozgursarki.shoppinglist.data.local.ShoppingRepository
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem
import javax.inject.Inject

class InsertShoppingItem @Inject constructor(
    private val repository: ShoppingRepository
) {

    suspend operator fun invoke(shoppingItem: ShoppingItem) {
        repository.insertShoppingItem(shoppingItem)
    }
}