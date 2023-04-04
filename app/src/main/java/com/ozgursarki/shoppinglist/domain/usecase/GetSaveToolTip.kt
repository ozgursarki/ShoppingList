package com.ozgursarki.shoppinglist.domain.usecase

import com.ozgursarki.shoppinglist.data.local.ShoppingRepository
import javax.inject.Inject

class GetSaveToolTip @Inject constructor(
    private val repository: ShoppingRepository
) {

    fun getSaveToolTip(): Boolean {
        return repository.getSave()
    }
}