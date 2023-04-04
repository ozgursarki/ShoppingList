package com.ozgursarki.shoppinglist.domain.usecase

import com.ozgursarki.shoppinglist.data.local.ShoppingRepository
import javax.inject.Inject

class GetDeleteToolTip @Inject constructor(
    private val repository: ShoppingRepository
) {

    fun getDeleteToopTip(): Boolean {
        return repository.getDelete()
    }
}