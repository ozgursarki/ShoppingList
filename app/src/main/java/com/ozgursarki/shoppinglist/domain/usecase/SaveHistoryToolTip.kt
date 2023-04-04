package com.ozgursarki.shoppinglist.domain.usecase

import com.ozgursarki.shoppinglist.data.local.ShoppingRepository
import javax.inject.Inject

class SaveHistoryToolTip @Inject constructor(
    private val repository: ShoppingRepository
) {

    fun saveHistoryToolTip() {
        return repository.saveHistory()
    }
}