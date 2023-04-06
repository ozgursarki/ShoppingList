package com.ozgursarki.shoppinglist.domain.usecase

import com.ozgursarki.shoppinglist.data.local.ShoppingRepository
import javax.inject.Inject

class SaveAddToolTip @Inject constructor(
    private val repository: ShoppingRepository
) {

    fun saveAddToolTip() {
        return repository.saveAdd()
    }
}