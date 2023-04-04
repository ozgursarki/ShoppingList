package com.ozgursarki.shoppinglist.domain.usecase

import com.ozgursarki.shoppinglist.data.local.ShoppingRepository
import javax.inject.Inject

class SaveDeleteToolTip @Inject constructor(
    private val repository: ShoppingRepository
) {

    fun saveDeleteToolTip() {
        return repository.saveDelete()
    }
}