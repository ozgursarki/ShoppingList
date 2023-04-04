package com.ozgursarki.shoppinglist.domain.usecase

import com.ozgursarki.shoppinglist.data.local.ShoppingRepository
import javax.inject.Inject

class GetAddToolTip @Inject constructor(
    private val repository: ShoppingRepository
) {

    fun getAddToolTip(): Boolean {
        return repository.getAdd()
    }
}