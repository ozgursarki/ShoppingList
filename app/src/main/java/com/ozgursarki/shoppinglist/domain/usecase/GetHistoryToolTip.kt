package com.ozgursarki.shoppinglist.domain.usecase

import com.ozgursarki.shoppinglist.data.local.ShoppingRepository
import javax.inject.Inject

class GetHistoryToolTip @Inject constructor(
    private val repository: ShoppingRepository
) {

    fun getHistoryToolTip(): Boolean {
        return repository.getHistory()
    }
}